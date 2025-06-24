package com.raksmey.command_pattern.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raksmey.command_pattern.domain.Status;
import com.raksmey.command_pattern.dto.WorkflowReqDto;
import com.raksmey.command_pattern.entity.WorkflowEntity;
import com.raksmey.command_pattern.event.WorkflowApprovedEvent;
import com.raksmey.command_pattern.mapper.WorkflowEntityDtoMapperImpl;
import com.raksmey.command_pattern.repository.WorkflowRepository;
import com.raksmey.command_pattern.service.WorkflowService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class WorkflowServiceImpl implements WorkflowService {

    private final WorkflowRepository workflowRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final ObjectMapper objectMapper;
    private final WorkflowEntityDtoMapperImpl workflowEntityDtoMapper;
    private final Logger logger = LoggerFactory.getLogger(WorkflowServiceImpl.class);


    @Override
    public void submit(WorkflowReqDto workflowReqDto) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(workflowReqDto.getPayload());
        WorkflowEntity workflowEntity = new WorkflowEntity();
        workflowEntity.setModuleType(workflowReqDto.getModuleType());
        workflowEntity.setPayload(json);
        workflowEntity.setCreatedBy(workflowReqDto.getCreatedBy());
        workflowEntity.setStatus(Status.PENDING);
        workflowEntity.setCheckerId(workflowReqDto.getCheckerId());
        workflowEntity.setMakerId(workflowReqDto.getMakerId());
        workflowRepository.save(workflowEntity);
    }


    @Override
    public void approve(Long id, String reviewer) {
        WorkflowEntity req = workflowRepository.findByIdAndDeletedFalse(id).orElseThrow();
        logger.info("approve entity: {}", req);
        if(req.getStatus() != Status.PENDING) throw new IllegalStateException("Already processed!");
        req.setStatus(Status.APPROVED);
        req.setCreatedBy(reviewer);
        applicationEventPublisher.publishEvent(new WorkflowApprovedEvent(workflowEntityDtoMapper.mapFrom(req)));
    }
}

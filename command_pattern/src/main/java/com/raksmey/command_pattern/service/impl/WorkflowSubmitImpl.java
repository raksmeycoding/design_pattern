package com.raksmey.command_pattern.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raksmey.command_pattern.domain.Status;
import com.raksmey.command_pattern.dto.WorkflowReqDto;
import com.raksmey.command_pattern.entity.WorkflowEntity;
import com.raksmey.command_pattern.repository.WorkflowRepository;
import com.raksmey.command_pattern.service.WorkflowSubmit;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class WorkflowSubmitImpl implements WorkflowSubmit {

    private final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(WorkflowSubmitImpl.class);
    private final WorkflowRepository workflowRepository;

    @Override
    public void submitNoReturn(WorkflowReqDto workflowReqDto)  {
        String json = null;
        try {
            json = objectMapper.writeValueAsString(workflowReqDto.getTrxData());
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        WorkflowEntity workflowEntity = new WorkflowEntity();
        workflowEntity.setMakerId(workflowReqDto.getMakerId());
        workflowEntity.setCheckerId(workflowReqDto.getCheckerId());
        workflowEntity.setUniqueKeyTrx(workflowReqDto.getUniqueKeyTrx());
        workflowEntity.setModuleType(workflowReqDto.getModuleType());
        workflowEntity.setTrxData(json);
        workflowEntity.setStatus(Status.PENDING);
        workflowEntity.setRemark(workflowReqDto.getRemark());
        workflowEntity.setCreatedAt(workflowReqDto.getCreatedAt());
        workflowEntity.setCreatedBy(workflowReqDto.getCreatedBy());
        workflowEntity.setUpdatedAt(workflowReqDto.getUpdatedAt());
        workflowEntity.setUpdatedBy(workflowReqDto.getUpdatedBy());
        workflowEntity.setUpdatedAt(workflowReqDto.getUpdatedAt());
        workflowEntity.setUpdatedBy(workflowReqDto.getUpdatedBy());
        workflowEntity.setDeleted(false);
        workflowRepository.save(workflowEntity);
//        applicationEventPublisher.publishEvent(new WorkflowStatusChangedEvent(workflowEntityDtoMapper.mapFrom(workflowEntity)));
    }

    @Override
    public int submitWithReturn(WorkflowReqDto workflowReqDto) {
        return 0;
    }
}

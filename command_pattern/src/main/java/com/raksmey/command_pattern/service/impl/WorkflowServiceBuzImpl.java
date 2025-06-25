package com.raksmey.command_pattern.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raksmey.command_pattern.domain.Status;
import com.raksmey.command_pattern.dto.WorkflowDto;
import com.raksmey.command_pattern.entity.WorkflowEntity;
import com.raksmey.command_pattern.event.WorkflowApprovedEvent;
import com.raksmey.command_pattern.event.WorkflowRejectedEvent;
import com.raksmey.command_pattern.mapper.WorkflowEntityDtoMapperImpl;
import com.raksmey.command_pattern.repository.WorkflowRepository;
import com.raksmey.command_pattern.service.WorkFlowServiceBuz;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class WorkflowServiceBuzImpl implements WorkFlowServiceBuz {

    private final WorkflowRepository workflowRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final ObjectMapper objectMapper;
    private final WorkflowEntityDtoMapperImpl workflowEntityDtoMapper;
    private final Logger logger = LoggerFactory.getLogger(WorkflowServiceBuzImpl.class);



    @Override
    public void approve(Long id) {
        WorkflowEntity req = workflowRepository.findByIdAndIsDeletedFalse(id).orElseThrow();
        logger.info("approve entity: {}", req);
        if(req.getStatus() != Status.PENDING) throw new IllegalStateException("Already processed!");
        req.setStatus(Status.APPROVED);
        applicationEventPublisher.publishEvent(new WorkflowApprovedEvent(workflowEntityDtoMapper.mapFrom(req)));
    }

    public void reject(Long id) {
        WorkflowEntity req = workflowRepository.findByIdAndIsDeletedFalse(id).orElseThrow();
        if (req.getStatus() != Status.PENDING) throw new IllegalStateException("Already processed");
        req.setStatus(Status.REJECTED);
        workflowRepository.save(req);
        applicationEventPublisher.publishEvent(new WorkflowRejectedEvent(workflowEntityDtoMapper.mapFrom(req)));
    }

    @Override
    public void modify(Long id) {
        WorkflowEntity req = workflowRepository.findByIdAndIsDeletedFalse(id).orElseThrow();
        if(req.getStatus() != Status.REJECTED) throw new IllegalStateException("Already processed");
        req.setStatus(Status.PENDING);
        workflowRepository.save(req);
    }

    @Override
    public List<WorkflowDto> getWorkflows() {
        return workflowRepository.findAllByIsDeletedFalse().stream().map(workflowEntityDtoMapper::mapFrom).toList();
    }

    @Override
    public Page<WorkflowDto> getWorkflowsPage(Pageable pageable) {
        return workflowRepository.findAllByIsDeletedFalse(pageable).map(workflowEntityDtoMapper::mapFrom);
    }

    @Override
    @Transactional(
            propagation = Propagation.REQUIRED,  // Default, can be omitted
            isolation = Isolation.READ_COMMITTED, // Recommended for this case
            timeout = 5,                         // Optional: prevent long locks
            rollbackFor = Exception.class        // Rollback on any exception
    )
    public void softDelete(Long id) {
        Optional<WorkflowEntity> optionalWorkflow = workflowRepository.findByIdAndIsDeletedFalse(id);
        if(optionalWorkflow.isEmpty()) {
            logger.info("no entity found, ID: {}", id);
            return;
        }
        WorkflowEntity workflowEntity = optionalWorkflow.get();
        workflowEntity.setDeleted(true);
        workflowRepository.save(workflowEntity);
    }

    //    public void softDelete(Long id) {
//        WorkflowRequest req = repo.findByIdAndDeletedFalse(id).orElseThrow();
//        req.setDeleted(true);
//        repo.save(req);
//    }

//    public List<WorkflowRequest> getMyPending(String username) {
//        return repo.findBySubmittedByAndDeletedFalse(username).stream()
//                .filter(r -> r.getStatus() == Status.PENDING)
//                .collect(Collectors.toList());
//    }

//    public WorkflowRequest getById(Long id) {
//        return repo.findByIdAndDeletedFalse(id).orElseThrow();
//    }
}

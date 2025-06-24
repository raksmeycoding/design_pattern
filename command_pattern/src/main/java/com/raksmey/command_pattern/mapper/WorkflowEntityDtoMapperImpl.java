package com.raksmey.command_pattern.mapper;

import com.raksmey.command_pattern.dto.WorkflowDto;
import com.raksmey.command_pattern.entity.WorkflowEntity;
import org.springframework.stereotype.Service;

@Service
public class WorkflowEntityDtoMapperImpl implements MyMapper<WorkflowEntity, WorkflowDto>{

    @Override
    public WorkflowDto mapFrom(WorkflowEntity workflowEntity) {
        WorkflowDto workflowDto = new WorkflowDto();
        workflowDto.setId(workflowEntity.getId());
        workflowDto.setModuleType(workflowEntity.getModuleType());
        workflowDto.setPayload(workflowEntity.getPayload());
        workflowDto.setStatus(workflowEntity.getStatus());
        workflowDto.setCreatedBy(workflowEntity.getCreatedBy());
        workflowDto.setCreatedAt(workflowEntity.getCreatedAt());
        workflowDto.setReviewedAt(workflowEntity.getReviewedAt());
        workflowDto.setDeleted(workflowEntity.isDeleted());
        return workflowDto;
    }

    @Override
    public WorkflowEntity mapTo(WorkflowDto workflowDto) {
        WorkflowEntity workflowEntity = new WorkflowEntity();
        workflowEntity.setModuleType(workflowDto.getModuleType());
        workflowEntity.setPayload(workflowDto.getPayload());
        workflowEntity.setStatus(workflowDto.getStatus());
        workflowEntity.setCreatedBy(workflowDto.getCreatedBy());
        workflowEntity.setCreatedAt(workflowDto.getCreatedAt());
        workflowEntity.setReviewedAt(workflowDto.getReviewedAt());
        workflowEntity.setDeleted(workflowDto.isDeleted());
        return workflowEntity;
    }
}

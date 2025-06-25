package com.raksmey.command_pattern.mapper;

import com.raksmey.command_pattern.domain.Status;
import com.raksmey.command_pattern.dto.WorkflowDto;
import com.raksmey.command_pattern.entity.WorkflowEntity;
import org.springframework.stereotype.Service;

@Service
public class WorkflowEntityDtoMapperImpl implements MyMapper<WorkflowEntity, WorkflowDto>{

    @Override
    public WorkflowDto mapFrom(WorkflowEntity workflowEntity) {
        WorkflowDto workflowDto = new WorkflowDto();
        workflowDto.setMakerId(workflowEntity.getMakerId());
        workflowDto.setCheckerId(workflowEntity.getCheckerId());
        workflowDto.setUniqueKeyTrx(workflowEntity.getUniqueKeyTrx());
        workflowDto.setModuleType(workflowEntity.getModuleType());
        workflowDto.setTrxData(workflowEntity.getTrxData());
        workflowDto.setStatus(workflowEntity.getStatus());
        workflowDto.setRemark(workflowEntity.getRemark());
        workflowDto.setCreatedAt(workflowEntity.getCreatedAt());
        workflowDto.setCreatedBy(workflowEntity.getCreatedBy());
        workflowDto.setUpdatedAt(workflowEntity.getUpdatedAt());
        workflowDto.setUpdatedBy(workflowEntity.getUpdatedBy());
        workflowDto.setUpdatedAt(workflowEntity.getUpdatedAt());
        workflowDto.setUpdatedBy(workflowEntity.getUpdatedBy());
        workflowDto.setDelete(workflowEntity.isDeleted());
        return workflowDto;
    }

    @Override
    public WorkflowEntity mapTo(WorkflowDto workflowDto) {
        WorkflowEntity workflowEntity = new WorkflowEntity();
        workflowEntity.setMakerId(workflowDto.getMakerId());
        workflowEntity.setCheckerId(workflowDto.getCheckerId());
        workflowEntity.setUniqueKeyTrx(workflowDto.getUniqueKeyTrx());
        workflowEntity.setModuleType(workflowDto.getModuleType());
        workflowEntity.setTrxData(workflowDto.getTrxData());
        workflowEntity.setStatus(workflowDto.getStatus());
        workflowEntity.setRemark(workflowDto.getRemark());
        workflowEntity.setCreatedAt(workflowDto.getCreatedAt());
        workflowEntity.setCreatedBy(workflowDto.getCreatedBy());
        workflowEntity.setUpdatedAt(workflowDto.getUpdatedAt());
        workflowEntity.setUpdatedBy(workflowDto.getUpdatedBy());
        workflowEntity.setUpdatedAt(workflowDto.getUpdatedAt());
        workflowEntity.setUpdatedBy(workflowDto.getUpdatedBy());
        workflowEntity.setDeleted(workflowEntity.isDeleted());
        return workflowEntity;
    }
}

package com.raksmey.command_pattern.service;

import com.raksmey.command_pattern.dto.WorkflowDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WorkFlowServiceBuz extends WorkflowService{
    void approve(Long id);

    void reject(Long id);

    void modify(Long id);

    List<WorkflowDto> getWorkflows();

    Page<WorkflowDto> getWorkflowsPage(Pageable pageable);

    void softDelete(Long id);
}

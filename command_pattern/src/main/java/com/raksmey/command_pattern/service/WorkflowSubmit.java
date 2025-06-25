package com.raksmey.command_pattern.service;

import com.raksmey.command_pattern.dto.WorkflowReqDto;

public interface WorkflowSubmit extends WorkflowService {
    void submitNoReturn(WorkflowReqDto workflowReqDto);
    int submitWithReturn(WorkflowReqDto workflowReqDto);
}

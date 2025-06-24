package com.raksmey.command_pattern.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.raksmey.command_pattern.dto.WorkflowDto;
import com.raksmey.command_pattern.dto.WorkflowReqDto;

import java.util.Map;

public interface  WorkflowService {

    void submit(WorkflowReqDto workflowReqDto) throws JsonProcessingException;

    void approve(Long id, String reviewer);
}

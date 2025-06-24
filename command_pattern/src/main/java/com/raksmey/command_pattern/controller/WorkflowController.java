package com.raksmey.command_pattern.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.raksmey.command_pattern.dto.WorkflowReqDto;
import com.raksmey.command_pattern.service.WorkflowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2.0/workflow")
public class WorkflowController {

    private final WorkflowService workflowService;

    public WorkflowController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    @PostMapping
    public ResponseEntity<?> submit(@RequestBody WorkflowReqDto workflowReqDto) throws JsonProcessingException {
        workflowService.submit(workflowReqDto);
        return ResponseEntity.ok("Submitted for approval");
    }

    @PostMapping("/approve/{id}")
    public ResponseEntity<?> approve(@PathVariable Long id) {
        workflowService.approve(id, "adminUser");
        return ResponseEntity.ok("Approved");
    }

}

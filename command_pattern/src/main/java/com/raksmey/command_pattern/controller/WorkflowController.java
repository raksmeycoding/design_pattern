package com.raksmey.command_pattern.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.raksmey.command_pattern.dto.WorkflowDto;
import com.raksmey.command_pattern.dto.WorkflowReqDto;
import com.raksmey.command_pattern.service.WorkFlowServiceBuz;
import com.raksmey.command_pattern.service.WorkflowService;
import com.raksmey.command_pattern.service.WorkflowSubmit;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2.0/workflow")
public class WorkflowController {

    private final WorkFlowServiceBuz workFlowServiceBuz;
    private final WorkflowSubmit workflowService;


    @PostMapping("/add")
    public ResponseEntity<?> submit(@RequestBody WorkflowReqDto workflowReqDto) throws JsonProcessingException {
        workflowService.submitNoReturn(workflowReqDto);
        return ResponseEntity.ok("Submitted for approval");
    }

    @PostMapping("/approve/{id}")
    public ResponseEntity<?> approve(@PathVariable Long id) {
        workFlowServiceBuz.approve(id);
        return ResponseEntity.ok("Approved");
    }


    @PostMapping("/reject/{id}")
    public ResponseEntity<?> reject(@PathVariable Long id) {
        workFlowServiceBuz.reject(id);
        return ResponseEntity.ok("Rejected");
    }

    @PostMapping("/modify/{id}")
    public ResponseEntity<?> modify(@PathVariable Long id) throws JsonProcessingException {
        workFlowServiceBuz.modify(id);
        return ResponseEntity.ok("Modified");
    }

//    @GetMapping
//    public ResponseEntity<?> getAllWorkflows() {
//        return ResponseEntity.ok(workFlowServiceBuz.getWorkflows());
//    }

    @GetMapping
    public ResponseEntity<Page<WorkflowDto>> getAllWorkflowsPageable( @RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(workFlowServiceBuz.getWorkflowsPage(PageRequest.of(page, size)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        workFlowServiceBuz.softDelete(id);
        return ResponseEntity.ok("Deleted");
    }
//
//    @GetMapping("/myTransaction")
//    public ResponseEntity<?> myTx() {
//        return ResponseEntity.ok(service.getMyPending("currentUser"));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> get(@PathVariable Long id) {
//        return ResponseEntity.ok(service.getById(id));
//    }

}

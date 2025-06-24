package com.raksmey.command_pattern.event;

import com.raksmey.command_pattern.dto.WorkflowDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class WorkflowApprovedEvent {
    private final WorkflowDto workflowRequestDto;

}

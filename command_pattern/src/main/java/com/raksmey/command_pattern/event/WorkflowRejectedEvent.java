package com.raksmey.command_pattern.event;

import com.raksmey.command_pattern.dto.WorkflowDto;

public record WorkflowRejectedEvent (WorkflowDto workflowRequestDto) {}
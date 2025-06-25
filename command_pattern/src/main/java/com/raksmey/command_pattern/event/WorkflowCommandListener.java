package com.raksmey.command_pattern.event;

public interface WorkflowCommandListener {

    void approved(WorkflowApprovedEvent event) throws Exception;

    void pending(WorkflowPendingEvent event) throws Exception;

    void rejected(WorkflowRejectedEvent event) throws Exception;
}

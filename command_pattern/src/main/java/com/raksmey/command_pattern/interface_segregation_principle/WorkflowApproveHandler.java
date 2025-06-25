package com.raksmey.command_pattern.interface_segregation_principle;

public interface WorkflowApproveHandler<PAYLOAD> extends WorkflowHandlerMarker<PAYLOAD>{
    void approved(PAYLOAD payload);
}

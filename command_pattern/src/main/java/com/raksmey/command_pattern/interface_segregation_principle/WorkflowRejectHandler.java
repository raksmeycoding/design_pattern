package com.raksmey.command_pattern.interface_segregation_principle;

public interface WorkflowRejectHandler<PAYLOAD> extends WorkflowHandlerMarker<PAYLOAD>{
    void rejected(PAYLOAD payload);
}

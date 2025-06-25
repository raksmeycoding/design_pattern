package com.raksmey.command_pattern.interface_segregation_principle;

public interface WorkflowPendingHandler<PAYLOAD> extends WorkflowHandlerMarker<PAYLOAD>{
    void pending(PAYLOAD payload);
}

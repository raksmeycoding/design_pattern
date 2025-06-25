package com.raksmey.command_pattern.command;

public interface WorkflowCommand<T> {
    void approved(T dto) throws Exception;
    void rejected(T dto) throws Exception;
    void pending(T dto) throws Exception;
}

package com.raksmey.command_pattern.command;

public interface WorkflowCommand<T> {
    void execute(T dto) throws Exception;
}

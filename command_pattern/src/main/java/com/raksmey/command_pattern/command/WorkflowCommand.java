package com.raksmey.command_pattern.command;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface WorkflowCommand<T> {
    void execute(T dto) throws Exception;
}

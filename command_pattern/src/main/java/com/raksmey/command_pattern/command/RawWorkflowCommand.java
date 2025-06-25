package com.raksmey.command_pattern.command;

public interface RawWorkflowCommand {
    void approved(String payload) throws Exception;
    void rejected(String payload) throws Exception;
    void pending(String payload) throws Exception;
}

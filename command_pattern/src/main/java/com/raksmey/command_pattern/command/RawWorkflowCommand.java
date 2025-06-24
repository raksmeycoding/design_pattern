package com.raksmey.command_pattern.command;

public interface RawWorkflowCommand {
    void execute(String rawJsonPayload) throws Exception;
}

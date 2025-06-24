package com.raksmey.command_pattern.command;

import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonCommandAdapter<T> implements RawWorkflowCommand {

    private final Class<T> dtoType;
    private final WorkflowCommand<T> target;
    private final ObjectMapper mapper;

    public JsonCommandAdapter(Class<T> dtoType, WorkflowCommand<T> target, ObjectMapper mapper) {
        this.dtoType = dtoType;
        this.target = target;
        this.mapper = mapper;
    }

    @Override
    public void execute(String rawJsonPayload) throws Exception {
        T dto = mapper.readValue(rawJsonPayload, dtoType);
        target.execute(dto);
    }
}

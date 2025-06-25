package com.raksmey.command_pattern.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raksmey.command_pattern.interface_segregation_principle.WorkflowApproveHandler;
import com.raksmey.command_pattern.interface_segregation_principle.WorkflowPendingHandler;
import com.raksmey.command_pattern.interface_segregation_principle.WorkflowRejectHandler;


public class JsonCommandAdapter<T> implements RawWorkflowCommand {

    private final Class<T> dtoType;
    private final Object target;
    private final ObjectMapper mapper;

    public JsonCommandAdapter(Class<T> dtoType, Object target, ObjectMapper mapper) {
        this.dtoType = dtoType;
        this.target = target;
        this.mapper = mapper;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void approved(String payload) throws Exception {
        T dto = mapper.readValue(payload, dtoType);
        if (target instanceof WorkflowApproveHandler<?>) {
            ((WorkflowApproveHandler<T>) target).approved(dto);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void rejected(String payload) throws Exception {
        T dto = mapper.readValue(payload, dtoType);
        if (target instanceof WorkflowRejectHandler<?>) {
            ((WorkflowRejectHandler<T>) target).rejected(dto);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void pending(String payload) throws Exception {
        T dto = mapper.readValue(payload, dtoType);
        if (target instanceof WorkflowPendingHandler<?>) {
            ((WorkflowPendingHandler<T>) target).pending(dto);
        }
    }
}

package com.raksmey.command_pattern.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raksmey.command_pattern.annotation.ModuleMethodHandler;
import com.raksmey.command_pattern.command.JsonCommandAdapter;
import com.raksmey.command_pattern.command.RawWorkflowCommand;
import com.raksmey.command_pattern.command.WorkflowCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class WorkflowCommandRegistry implements SmartInitializingSingleton {

    private final List<WorkflowCommand<?>> commands;
    private final ObjectMapper objectMapper;

    private final Map<String, RawWorkflowCommand> registry = new HashMap<>();

    @SuppressWarnings("unchecked")
    @Override
    public void afterSingletonsInstantiated() {
        for (WorkflowCommand<?> command : commands) {
            ModuleMethodHandler handler = command.getClass().getAnnotation(ModuleMethodHandler.class);
            if (handler != null) {
                String key = handler.value();
                Class<Object> dtoType = (Class<Object>) extractDtoType(command);
                registry.put(key, new JsonCommandAdapter<>(dtoType, (WorkflowCommand<Object>) command, objectMapper));
            }
        }
    }


    private Class<?> extractDtoType(WorkflowCommand<?> command) {
        ParameterizedType type = (ParameterizedType) command.getClass().getGenericInterfaces()[0];
        return (Class<?>) type.getActualTypeArguments()[0];
    }

    public RawWorkflowCommand getCommand(String type) {
        return registry.get(type);
    }
}

//package com.raksmey.command_pattern.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.raksmey.command_pattern.command.JsonCommandAdapter;
//import com.raksmey.command_pattern.command.RawWorkflowCommand;
//import com.raksmey.command_pattern.command.WorkflowCommand;
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.SmartInitializingSingleton;
//import org.springframework.context.ApplicationContext;
//import org.springframework.core.GenericTypeResolver;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
//@Component
//@RequiredArgsConstructor
//public class WorkflowCommandAutoRegister implements SmartInitializingSingleton {
//
//    private final List<WorkflowCommand<?>> workflowCommands;
//    private final ObjectMapper objectMapper;
//    private final Map<String, RawWorkflowCommand> registry = new HashMap<>();
//    private static final Logger logger = LoggerFactory.getLogger(WorkflowCommandAutoRegister.class);
//
//
//    public RawWorkflowCommand getCommand(String type) {
//        return registry.get(type);
//    }
//
//    @Override
//    public void afterSingletonsInstantiated() {
//
//        for (WorkflowCommand<?> command : workflowCommands) {
//            CommandHandler annotation = command.getClass().getAnnotation(CommandHandler.class);
//            if (annotation != null) {
//                String commandKey = annotation.value();
//                Class<?> dtoType = extractDtoType(command);
//                RawWorkflowCommand adapter = new JsonCommandAdapter(dtoType, command, objectMapper);
//                registry.put(commandKey, adapter);
//            }
//        }
//
//    }
//}

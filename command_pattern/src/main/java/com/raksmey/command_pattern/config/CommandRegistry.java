//package com.raksmey.command_pattern.config;
//
//
//import com.raksmey.command_pattern.command.RawWorkflowCommand;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Component
//public class CommandRegistry {
//
//    private final Map<String, RawWorkflowCommand> commands = new ConcurrentHashMap<>();
//
//    public void register(String type, RawWorkflowCommand command) {
//        commands.put(type, command);
//    }
//
//    public RawWorkflowCommand getCommand(String type) {
//        return commands.get(type);
//    }
//}

package com.raksmey.command_pattern.event;


import com.raksmey.command_pattern.command.RawWorkflowCommand;
import com.raksmey.command_pattern.config.WorkflowCommandRegistry;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkflowEventListener {

    private final ApplicationContext applicationContext;
    private final WorkflowCommandRegistry commandRegistry;
    private static final Logger logger = LoggerFactory.getLogger(WorkflowEventListener.class);

    @EventListener
    public void handle(WorkflowApprovedEvent event) throws Exception {
        logger.info("workflow approved listener activated. event object: {}", event);
        String type = event.workflowRequestDto().getModuleType();
        logger.info("bean type: {}", type);
        String payload = event.workflowRequestDto().getPayload();
        logger.info("payload: {}", payload);
//        RawWorkflowCommand command = applicationContext.getBean(type, RawWorkflowCommand.class);
        RawWorkflowCommand command = commandRegistry.getCommand(type);
        logger.info("command: {}", command);
        command.execute(payload);
    }
}

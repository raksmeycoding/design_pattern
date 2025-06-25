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
public class WorkflowEventListener implements WorkflowCommandListener{

    private final ApplicationContext applicationContext;
    private final WorkflowCommandRegistry commandRegistry;
    private static final Logger logger = LoggerFactory.getLogger(WorkflowEventListener.class);


    @EventListener
    @Override
    public void approved(WorkflowApprovedEvent event) throws Exception {
        logger.info("workflow approved listener activated. event object: {}", event);
        String type = event.workflowRequestDto().getModuleType();
        logger.info("module : {}", type);
        String payload = event.workflowRequestDto().getTrxData();
        logger.info("payload: {}", payload);
//        RawWorkflowCommand command = applicationContext.getBean(type, RawWorkflowCommand.class);
        RawWorkflowCommand command = commandRegistry.getCommand(type);
        logger.info("command: {}", command);
        command.approved(payload);
    }

    @EventListener
    @Override
    public void pending(WorkflowPendingEvent event) throws Exception {
        logger.info("workflow pending listener activated. event object: {}", event);
        String type = event.workflowRequestDto().getModuleType();
        String payload = event.workflowRequestDto().getTrxData();
        RawWorkflowCommand command = commandRegistry.getCommand(type);
        command.pending(payload);
    }

    @Override
    @EventListener
    public void rejected(WorkflowRejectedEvent event) throws Exception {
        logger.info("workflow rejected listener activated. event object: {}", event);
        String type = event.workflowRequestDto().getModuleType();
        String payload = event.workflowRequestDto().getTrxData();
        RawWorkflowCommand command = commandRegistry.getCommand(type);
        command.rejected(payload);
    }
}

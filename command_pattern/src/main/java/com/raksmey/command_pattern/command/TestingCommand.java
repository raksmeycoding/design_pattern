package com.raksmey.command_pattern.command;

import com.raksmey.command_pattern.annotation.ModuleMethodHandler;
import com.raksmey.command_pattern.domain.WorkflowType;
import com.raksmey.command_pattern.dto.TestingDto;
import com.raksmey.command_pattern.interface_segregation_principle.WorkflowApproveHandler;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
@ModuleMethodHandler(WorkflowType.TEST_USER_CREATE)
@RequiredArgsConstructor
public class TestingCommand implements WorkflowApproveHandler<TestingDto> {
    private static final Logger logger = LoggerFactory.getLogger(TestingCommand.class);
    @Override
    public void approved(TestingDto testingDto) {
        logger.info("Approved payload: {}", testingDto);
    }

}

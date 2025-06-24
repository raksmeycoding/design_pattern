package com.raksmey.command_pattern.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raksmey.command_pattern.command.CreateUserCommand;
import com.raksmey.command_pattern.command.JsonCommandAdapter;
import com.raksmey.command_pattern.command.RawWorkflowCommand;
import com.raksmey.command_pattern.command.UpdateUserCommand;
import com.raksmey.command_pattern.domain.CommandType;
import com.raksmey.command_pattern.dto.UserDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkflowCommandConfig {

    @Bean(CommandType.CREATE_REGISTER_USER)
    public RawWorkflowCommand userCreateAdapter(CreateUserCommand command, ObjectMapper mapper) {
        return new JsonCommandAdapter<>(UserDto.class, command, mapper);
    }

    // Example for customer module
    @Bean(CommandType.UPDATE_USER)
    public RawWorkflowCommand customerCreateAdapter(UpdateUserCommand command, ObjectMapper mapper) {
        return new JsonCommandAdapter<>(UserDto.class, command, mapper);
    }
}


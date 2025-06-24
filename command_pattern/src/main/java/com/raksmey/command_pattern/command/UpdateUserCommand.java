package com.raksmey.command_pattern.command;


import com.raksmey.command_pattern.dto.UserDto;
import com.raksmey.command_pattern.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserCommand implements WorkflowCommand<UserDto>{

    private final UserRepository userRepository;

    @Override
    public void execute(UserDto payload) {

    }
}

package com.raksmey.command_pattern.command;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.raksmey.command_pattern.annotation.ModuleMethodHandler;
import com.raksmey.command_pattern.domain.WorkflowType;
import com.raksmey.command_pattern.dto.UserDto;
import com.raksmey.command_pattern.entity.UserEntity;
import com.raksmey.command_pattern.interface_segregation_principle.WorkflowApproveHandler;
import com.raksmey.command_pattern.interface_segregation_principle.WorkflowRejectHandler;
import com.raksmey.command_pattern.repository.UserRepository;
import com.raksmey.command_pattern.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@ModuleMethodHandler(WorkflowType.CREATE_REGISTER_USER)
@RequiredArgsConstructor
public class CreateUserCommand implements WorkflowApproveHandler<UserDto>, WorkflowRejectHandler<UserDto> {

    private static final Logger logger = LoggerFactory.getLogger(CreateUserCommand.class);

    private final UserService userService;

    private final UserRepository userRepository;

    private final ObjectMapper objectMapper;

    @Override
    public void approved(UserDto payload) {
        logger.info("approved: {}", payload);

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(payload, userEntity);
        logger.info("user entity: {}", userEntity);

        userRepository.save(userEntity);
    }

    @Override
    public void rejected(UserDto userDto) {
        logger.info("rejected: {}", userDto);
    }


    //    @Override
//    public void rejected(UserDto dto) throws Exception {
//        logger.info("rejected payload: {}", dto);
//
//    }

//    @Override
//    public void pending(UserDto dto) throws Exception {
//        logger.info("pending payload: {}", dto);
//    }

    //    @Override
//    public void execute(String payload) {
//        try {
//            logger.info("create user command payload: {}", payload);
//
//            // Fix: Use readValue() instead of convertValue()
//            UserDto userDto = objectMapper.readValue(payload, UserDto.class);
//
//            logger.info("create user userDto: {}", userDto);
//
//            UserEntity userEntity = new UserEntity();
//            BeanUtils.copyProperties(userDto, userEntity);
//
//            logger.info("create user entity: {}", userEntity);
//            userRepository.save(userEntity);
//
//        } catch (JsonProcessingException e) {
//            throw new IllegalArgumentException("Invalid JSON payload", e);
//        }
//    }

}

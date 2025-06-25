package com.raksmey.command_pattern;

import com.raksmey.command_pattern.command.CreateCategoryWorkflowCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommandPatternApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommandPatternApplication.class, args);
    }

}

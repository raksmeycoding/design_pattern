package com.raksmey.command_pattern.dto;

import com.raksmey.command_pattern.domain.WorkflowType;
import com.raksmey.command_pattern.domain.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;


@Data
@AllArgsConstructor
public class WorkflowReqDto {


    @Schema(
            description = "ID of the user who created the request",
            example = "123",
            defaultValue = "123"
    )
    private Long makerId;

    @Schema(
            description = "ID of the user who approves/rejects",
            example = "456",
            defaultValue = "456"
    )
    private Long checkerId;


    @Schema(
            description = "Type of module",
            example = WorkflowType.CREATE_REGISTER_USER,
            defaultValue = WorkflowType.CREATE_REGISTER_USER
    )
    private String moduleType;


    @Schema(
            description = "Payload data in JSON format",
            example = "{\"firstName\":\"Alic\",\"lastName\":\"Thorouggood\",\"email\":\"athorouggood0@homestead.com\",\"gender\":\"Male\"}",
            // defaultValue = "{\"firstName\":\"Alic\",\"lastName\":\"Thorouggood\",\"email\":\"athorouggood0@homestead.com\",\"gender\":\"Male\"}"
            defaultValue = "{\"firstName\":\"Alic\",\"lastName\":\"Thorouggood\"," +
                    "\"email\":\"athorouggood0@homestead.com\",\"gender\":\"Male\"," +
                    "\"address\":{\"street\":\"123 Main St\",\"city\":\"Anytown\",\"zip\":\"12345\"}}"
    )
    private Map<String, Object> payload;


    @Schema(
            description = "Workflow status",
            example = "PENDING",
            defaultValue = "PENDING"
    )
    private Status status;

    @Schema(
            description = "Creator of the request",
            example = "admin",
            defaultValue = "system"
    )
    private String createdBy;



}

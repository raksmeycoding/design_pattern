package com.raksmey.command_pattern.dto;

import com.raksmey.command_pattern.domain.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowDto  {

    private Long id;

    private Long makerId;

    private Long checkerId;

    private String moduleType;

    private String payload;

    private Status status;

    private String createdBy;

    private LocalDateTime createdAt;

    private LocalDateTime reviewedAt;

    private boolean deleted;


}

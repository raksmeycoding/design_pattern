package com.raksmey.command_pattern.entity;


import com.raksmey.command_pattern.domain.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "WORKFLOW")
public class WorkflowEntity {
    @Id
    @GeneratedValue
    private Long id;

    private Long makerId;

    private Long checkerId;

    private String moduleType;

    @Lob
    private String payload;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String createdBy;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime reviewedAt;

    private boolean deleted = false;
}

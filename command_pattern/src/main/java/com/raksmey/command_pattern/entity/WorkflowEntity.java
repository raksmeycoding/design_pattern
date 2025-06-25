package com.raksmey.command_pattern.entity;


import com.raksmey.command_pattern.domain.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "WORKFLOW")
public class WorkflowEntity {
    @Id
    @GeneratedValue
    private Long id;

    private Long makerId;

    private Long checkerId;

    private String uniqueKeyTrx;

    private String moduleType;

    @Lob
    private String trxData;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String remark;

    private Timestamp createdAt;

    private String createdBy;

    private Timestamp updatedAt;

    private String updatedBy;

    private Timestamp deletedAt;

    private String deletedBy;

    private boolean isDeleted;
}

package com.raksmey.command_pattern.dto;

import com.raksmey.command_pattern.domain.Status;
import com.raksmey.command_pattern.domain.WorkflowType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowDto  {

    private Long id;

    private Long makerId;

    private Long checkerId;

    private String moduleType;

    private String UniqueKeyTrx;

    private String trxData;

    private Status status;

    private String remark;

    private Timestamp createdAt;

    private String createdBy;

    private Timestamp updatedAt;

    private String updatedBy;

    private Timestamp deletedAt;

    private String deletedBy;

    private boolean isDelete;

}

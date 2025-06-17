package com.ooredoo.report_builder.dto.response;

import com.ooredoo.report_builder.entity.RoleAction.ActionType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoleActionResponse {
    private Integer id;
    private String name;
    private String description;
    private ActionType actionType;
    private String resourceType;
    private AnimatorRoleSummaryDTO role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
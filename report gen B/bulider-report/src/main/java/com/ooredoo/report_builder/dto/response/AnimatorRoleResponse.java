package com.ooredoo.report_builder.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class AnimatorRoleResponse {
    private Integer id;
    private String name;
    private String description;
    private Set<RoleActionSummaryDTO> actions;
    private Set<AnimatorSummaryDTO> animators;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

@Data
class RoleActionSummaryDTO {
    private Integer id;
    private String name;
    private String actionType;
}

@Data
class AnimatorSummaryDTO {
    private Integer id;
    private String pin;
}
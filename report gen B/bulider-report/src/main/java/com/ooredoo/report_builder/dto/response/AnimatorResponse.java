package com.ooredoo.report_builder.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class AnimatorResponse {
    private Integer id;
    private String pin;
    private String description;
    private AnimatorRoleSummaryDTO role;
    private POSSummaryDTO pos;
    private Set<UserSummaryDTO> users;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

@Data
class AnimatorRoleSummaryDTO {
    private Integer id;
    private String name;
}
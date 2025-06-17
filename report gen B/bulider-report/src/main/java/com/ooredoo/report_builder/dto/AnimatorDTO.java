package com.ooredoo.report_builder.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class AnimatorDTO {
    private Integer id;
    
    @NotBlank(message = "PIN is required")
    private String pin;
    
    private String description;
    
    @NotNull(message = "Role ID is required")
    private Integer roleId;
    
    private Integer posId;
    
    private Set<Integer> userIds;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
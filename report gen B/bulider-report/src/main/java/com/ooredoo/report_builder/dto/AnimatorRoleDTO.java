package com.ooredoo.report_builder.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class AnimatorRoleDTO {
    private Integer id;
    
    @NotBlank(message = "Role name is required")
    private String name;
    
    private String description;
    
    private Set<Integer> actionIds;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
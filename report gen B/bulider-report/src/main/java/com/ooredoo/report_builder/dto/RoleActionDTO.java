package com.ooredoo.report_builder.dto;

import com.ooredoo.report_builder.entity.RoleAction.ActionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoleActionDTO {
    private Integer id;
    
    @NotBlank(message = "Action name is required")
    private String name;
    
    private String description;
    
    @NotNull(message = "Action type is required")
    private ActionType actionType;
    
    @NotBlank(message = "Resource type is required")
    private String resourceType;
    
    @NotNull(message = "Role ID is required")
    private Integer roleId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
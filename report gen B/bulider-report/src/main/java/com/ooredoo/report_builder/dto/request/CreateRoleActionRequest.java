package com.ooredoo.report_builder.dto.request;

import com.ooredoo.report_builder.entity.RoleAction.ActionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateRoleActionRequest {
    @NotBlank(message = "Action name is required")
    private String name;
    
    private String description;
    
    @NotNull(message = "Action type is required")
    private ActionType actionType;
    
    @NotBlank(message = "Resource type is required")
    private String resourceType;
    
    @NotNull(message = "Role ID is required")
    private Integer roleId;
}
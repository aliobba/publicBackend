package com.ooredoo.report_builder.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class CreateAnimatorRoleRequest {
    @NotBlank(message = "Role name is required")
    private String name;
    
    private String description;
    
    private Set<Integer> actionIds;
}
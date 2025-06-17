package com.ooredoo.report_builder.dto.request;

import lombok.Data;

import java.util.Set;

@Data
public class UpdateAnimatorRoleRequest {
    private String name;
    private String description;
    private Set<Integer> actionIds;
}
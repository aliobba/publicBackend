package com.ooredoo.report_builder.dto.request;

import com.ooredoo.report_builder.entity.RoleAction.ActionType;
import lombok.Data;

@Data
public class UpdateRoleActionRequest {
    private String name;
    private String description;
    private ActionType actionType;
    private String resourceType;
    private Integer roleId;
}
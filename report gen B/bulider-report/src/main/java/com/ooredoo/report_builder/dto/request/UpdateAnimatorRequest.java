package com.ooredoo.report_builder.dto.request;

import lombok.Data;

import java.util.Set;

@Data
public class UpdateAnimatorRequest {
    private String pin;
    private String description;
    private Integer roleId;
    private Integer posId;
    private Set<Integer> userIds;
}
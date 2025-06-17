package com.ooredoo.report_builder.dto.request;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

@Data
public class UpdatePOSRequest {
    @NotNull
    private Integer id;
    private String name;
    private String description;
    private String address;
    private Integer managerId;
    private Set<Integer> userIds;
    private Integer enterpriseId;
    private Integer regionId;
}
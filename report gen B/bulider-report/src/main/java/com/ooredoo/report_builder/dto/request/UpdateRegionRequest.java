package com.ooredoo.report_builder.dto.request;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

@Data
public class UpdateRegionRequest {
    @NotNull
    private Integer id;
    private String name;
    private String description;
    private Integer regionHeadId;
    private Set<Integer> userIds;
    private Integer zoneId;
    private Set<Integer> pointsOfSaleIds;
}
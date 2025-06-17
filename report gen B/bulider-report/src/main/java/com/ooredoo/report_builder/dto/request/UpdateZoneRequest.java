package com.ooredoo.report_builder.dto.request;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

@Data
public class UpdateZoneRequest {
    @NotNull
    private Integer id;
    private String name;
    private String description;
    private Integer zoneHeadId;
    private Set<Integer> userIds;
    private Integer sectorId;
    private Set<Integer> regionIds;
}
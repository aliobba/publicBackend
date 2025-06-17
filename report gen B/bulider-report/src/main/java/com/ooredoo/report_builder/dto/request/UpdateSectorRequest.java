package com.ooredoo.report_builder.dto.request;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

@Data
public class UpdateSectorRequest {
    @NotNull
    private Integer id;
    private String name;
    private String description;
    private Integer sectorHeadId;
    private Set<Integer> userIds;
    private Integer enterpriseId;
    private Set<Integer> zoneIds;
}
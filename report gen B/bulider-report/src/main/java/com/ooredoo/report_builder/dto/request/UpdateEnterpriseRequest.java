package com.ooredoo.report_builder.dto.request;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

@Data
public class UpdateEnterpriseRequest {
    @NotNull
    private Integer id;
    private String name;
    private String description;
    private Integer enterpriseAdminId;
    private Set<Integer> userIds;
    private Set<Integer> pointsOfSaleIds;
    private Set<Integer> sectorIds;
}
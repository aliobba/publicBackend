package com.ooredoo.report_builder.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class RegionResponse {
    private Integer id;
    private String name;
    private String description;
    private UserSummaryDTO regionHead;
    private Set<UserSummaryDTO> users;
    private ZoneSummaryDTO zone;
    private Set<POSSummaryDTO> pointsOfSale;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
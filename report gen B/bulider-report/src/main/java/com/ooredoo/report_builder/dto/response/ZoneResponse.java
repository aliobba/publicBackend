package com.ooredoo.report_builder.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class ZoneResponse {
    private Integer id;
    private String name;
    private String description;
    private UserSummaryDTO zoneHead;
    private Set<UserSummaryDTO> users;
    private SectorSummaryDTO sector;
    private Set<RegionSummaryDTO> regions;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
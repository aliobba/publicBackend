package com.ooredoo.report_builder.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class SectorResponse {
    private Integer id;
    private String name;
    private String description;
    private UserSummaryDTO sectorHead;
    private Set<UserSummaryDTO> users;
    private EnterpriseSummaryDTO enterprise;
    private Set<ZoneSummaryDTO> zones;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

@Data
class ZoneSummaryDTO {
    private Integer id;
    private String name;
}
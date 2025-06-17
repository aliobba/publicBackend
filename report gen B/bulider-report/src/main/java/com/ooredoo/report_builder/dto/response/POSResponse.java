package com.ooredoo.report_builder.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class POSResponse {
    private Integer id;
    private String name;
    private String description;
    private String location;
    private UserSummaryDTO manager;
    private Set<UserSummaryDTO> users;
    private EnterpriseSummaryDTO enterprise;
    private RegionSummaryDTO region;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

@Data
class EnterpriseSummaryDTO {
    private Integer id;
    private String name;
}

@Data
class RegionSummaryDTO {
    private Integer id;
    private String name;
}
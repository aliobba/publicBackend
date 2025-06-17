package com.ooredoo.report_builder.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class EnterpriseResponse {
    private Integer id;
    private String name;
    private String description;
    private String logo;
    private String primaryColor;
    private String secondaryColor;
    private UserSummaryDTO enterpriseAdmin;
    private Set<UserSummaryDTO> users;
    private Set<POSSummaryDTO> pointsOfSale;
    private Set<SectorSummaryDTO> sectors;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}


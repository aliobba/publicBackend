package com.ooredoo.report_builder.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ZoneDTO {
    private Integer id;
    
    @NotBlank(message = "Zone name is required")
    private String name;
    
    private String description;
    private Integer zoneHeadId;
    
    @NotNull(message = "Sector ID is required")
    private Integer sectorId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
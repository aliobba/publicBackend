package com.ooredoo.report_builder.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegionDTO {
    private Integer id;
    
    @NotBlank(message = "Region name is required")
    private String name;
    
    private String description;
    private Integer regionHeadId;
    
    @NotNull(message = "Zone ID is required")
    private Integer zoneId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
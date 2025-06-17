package com.ooredoo.report_builder.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SectorDTO {
    private Integer id;
    
    @NotBlank(message = "Sector name is required")
    private String name;
    
    private String description;
    private Integer sectorHeadId;
    
    @NotNull(message = "Enterprise ID is required")
    private Integer enterpriseId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
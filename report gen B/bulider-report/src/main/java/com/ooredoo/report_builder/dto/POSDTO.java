package com.ooredoo.report_builder.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class POSDTO {
    private Integer id;
    
    @NotBlank(message = "POS name is required")
    private String name;
    
    private String description;
    
    @NotBlank(message = "Location is required")
    private String location;
    
    private Integer managerId;
    
    @NotNull(message = "Enterprise ID is required")
    private Integer enterpriseId;
    
    private Integer regionId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
package com.ooredoo.report_builder.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreatePOSRequest {
    @NotBlank(message = "POS name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;
    
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;
    
    @NotBlank(message = "Location is required")
    @Size(max = 200, message = "Location cannot exceed 200 characters")
    private String location;
    
    private Integer managerId;
    
    @NotNull(message = "Enterprise ID is required")
    private Integer enterpriseId;
    
    private Integer regionId;
}
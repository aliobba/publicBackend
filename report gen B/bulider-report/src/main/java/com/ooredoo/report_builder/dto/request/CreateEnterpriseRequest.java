package com.ooredoo.report_builder.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateEnterpriseRequest {
    @NotBlank(message = "Enterprise name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;
    
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;
    
    private String logo;
    private String primaryColor;
    private String secondaryColor;
    private Integer enterpriseAdminId;
}
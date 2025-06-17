package com.ooredoo.report_builder.dto;

import com.ooredoo.report_builder.user.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EnterpriseDTO {
    private Integer id;
    
    @NotBlank(message = "Enterprise name is required")
    private String name;
    
    private String description;
    private String logo;
    private String primaryColor;
    private String secondaryColor;
    private User enterpriseAdmin;
    private Integer enterpriseAdminId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
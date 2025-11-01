package com.ooredoo.report_builder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PinVerificationResponse {
    private boolean valid;
    private String message;
    private EnterpriseThemeData enterpriseTheme;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EnterpriseThemeData {
        private Integer enterpriseId;
        private String enterpriseName;
        private String primaryColor;
        private String secondaryColor;
        private String logoUrl;
    }
}

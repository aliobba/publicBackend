package com.ooredoo.report_builder.dto.response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleActionResponseDTO {
    private Integer id;
    private String actionKey;
    private String description;
    private String endpointPattern;
    private String roleName;

}

package com.ooredoo.report_builder.dto.request;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleActionRequest {

    private String actionKey;
    private String description;
    private String endpointPattern;

}

package com.ooredoo.report_builder.dto.request;


import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnterpriseRequestDTO {

    private String name;
    private String logoUrl;
    private String primaryColor;
    private String secondaryColor;
    private Integer managerId;        // User managing enterprise
    private Set<Integer> userIds;     // Users directly in enterprise
}

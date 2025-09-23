package com.ooredoo.report_builder.dto.response;


import lombok.*;

import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class EnterpriseResponseDTO {

    private Integer id;
    private String name;
    private String logoUrl;
    private String primaryColor;
    private String secondaryColor;
    private Integer managerId;
    private Set<Integer> userIds;
    private Set<Integer> sectorIds;
}

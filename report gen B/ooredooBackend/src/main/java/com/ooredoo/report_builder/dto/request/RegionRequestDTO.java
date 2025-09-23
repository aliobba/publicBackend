package com.ooredoo.report_builder.dto.request;


import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegionRequestDTO {
    private String name;
    private Integer zoneId;
    private Integer managerId;
    private Set<Integer> userIds;
}

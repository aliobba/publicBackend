package com.ooredoo.report_builder.dto.response;

import lombok.*;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegionResponseDTO {

    private Integer id;
    private String name;
    private Integer zoneId;
    private Integer managerId;
    private Set<Integer> userIds;
}

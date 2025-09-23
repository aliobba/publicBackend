package com.ooredoo.report_builder.dto.response;

import lombok.*;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SectorResponseDTO {

    private Integer id;
    private String name;
    private Integer enterpriseId;
    private Integer managerId;
    private Set<Integer> userIds;
    private Set<Integer> zoneIds;
}

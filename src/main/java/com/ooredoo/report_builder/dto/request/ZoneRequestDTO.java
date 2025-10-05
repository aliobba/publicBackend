package com.ooredoo.report_builder.dto.request;


import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZoneRequestDTO {
    private String name;
    private Integer sectorId;
    private Integer managerId;
    private Set<Integer> userIds;
}

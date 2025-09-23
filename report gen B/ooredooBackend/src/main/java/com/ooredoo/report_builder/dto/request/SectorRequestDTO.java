package com.ooredoo.report_builder.dto.request;


import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SectorRequestDTO {

    private String name;
    private Integer enterpriseId;
    private Integer managerId;
    private Set<Integer> userIds;
}

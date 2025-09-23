package com.ooredoo.report_builder.dto.request;


import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleRequest {

    private String name;
    private Set<Integer> roleActionIds;

}

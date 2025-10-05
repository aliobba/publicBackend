package com.ooredoo.report_builder.dto.response;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleResponse {

    private Integer id;
    private String name;
    private Set<String> roleActions;

}

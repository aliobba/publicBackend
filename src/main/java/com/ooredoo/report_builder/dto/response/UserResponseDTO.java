package com.ooredoo.report_builder.dto.response;

import lombok.*;

import java.util.List;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {

    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private boolean enabled;
    private boolean accountLocked;
    private List<Integer> roleIds;// role names
    private Set<Integer> animatorIds;  // animator names
}

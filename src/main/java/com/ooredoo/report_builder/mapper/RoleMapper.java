package com.ooredoo.report_builder.mapper;

import com.ooredoo.report_builder.dto.request.RoleRequest;
import com.ooredoo.report_builder.dto.response.RoleResponse;
import com.ooredoo.report_builder.user.Role;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {RoleActionMapper.class})
public interface RoleMapper {
    /*@Mapping(target = "roleActions", expression = "java(role.getRoleActions().stream().map(RoleAction::getActionName).collect(Collectors.toSet()))")
    RoleResponse toDto(Role role);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "actions", ignore = true)
    Role toEntity(RoleRequest dto);
*/
}
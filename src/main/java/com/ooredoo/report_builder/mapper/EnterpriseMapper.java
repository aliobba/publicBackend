package com.ooredoo.report_builder.mapper;

import com.ooredoo.report_builder.dto.request.EnterpriseRequestDTO;
import com.ooredoo.report_builder.dto.response.EnterpriseResponseDTO;
import com.ooredoo.report_builder.entity.Enterprise;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {})
public interface EnterpriseMapper {
/*

    @Mapping(target = "managerId", source = "manager.id")
    @Mapping(target = "userIds", expression = "java(enterprise.getUsersInEnterprise().stream().map(User::getId).collect(Collectors.toSet()))")
    @Mapping(target = "sectorIds", expression = "java(enterprise.getSectors().stream().map(Sector::getId).collect(Collectors.toSet()))")
    EnterpriseResponseDTO toDto(Enterprise enterprise);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usersInEnterprise", ignore = true)
    @Mapping(target = "manager", ignore = true)
    @Mapping(target = "sectors", ignore = true)
    Enterprise toEntity(EnterpriseRequestDTO dto);
*/
}
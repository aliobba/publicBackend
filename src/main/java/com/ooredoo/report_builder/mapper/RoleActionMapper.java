package com.ooredoo.report_builder.mapper;

import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoleActionMapper {

/*
    @Mapping(target = "roleName", source = "role.name")
    RoleActionResponseDTO toDto(RoleAction action);

    @Mapping(target = "role", ignore = true) // linked in service
    RoleAction toEntity(RoleActionRequest dto);*/
}
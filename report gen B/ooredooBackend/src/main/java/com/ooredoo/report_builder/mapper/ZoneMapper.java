package com.ooredoo.report_builder.mapper;

import com.ooredoo.report_builder.dto.request.ZoneRequestDTO;
import com.ooredoo.report_builder.dto.response.ZoneResponseDTO;
import com.ooredoo.report_builder.entity.Zone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ZoneMapper {
/*
    @Mapping(target = "sectorId", source = "sector.id")
    @Mapping(target = "managerId", source = "manager.id")
    @Mapping(target = "userIds", expression = "java(zone.getUsersInZone().stream().map(User::getId).collect(Collectors.toSet()))")
    @Mapping(target = "regionIds", expression = "java(zone.getRegions().stream().map(Region::getId).collect(Collectors.toSet()))")
    ZoneResponseDTO toDto(Zone zone);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sector", ignore = true)
    @Mapping(target = "manager", ignore = true)
    @Mapping(target = "usersInZone", ignore = true)
    @Mapping(target = "regions", ignore = true)
    Zone toEntity(ZoneRequestDTO dto);*/
}
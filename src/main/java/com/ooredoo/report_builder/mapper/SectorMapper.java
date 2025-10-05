package com.ooredoo.report_builder.mapper;

import com.ooredoo.report_builder.dto.request.SectorRequestDTO;
import com.ooredoo.report_builder.dto.response.SectorResponseDTO;
import com.ooredoo.report_builder.entity.Sector;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SectorMapper {
/*
    @Mapping(target = "enterpriseId", source = "enterprise.id")
    @Mapping(target = "managerId", source = "manager.id")
    @Mapping(target = "userIds", expression = "java(sector.getUsersInSector().stream().map(User::getId).collect(Collectors.toSet()))")
    @Mapping(target = "zoneIds", expression = "java(sector.getZones().stream().map(Zone::getId).collect(Collectors.toSet()))")
    SectorResponseDTO toDto(Sector sector);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enterprise", ignore = true)
    @Mapping(target = "manager", ignore = true)
    @Mapping(target = "usersInSector", ignore = true)
    @Mapping(target = "zones", ignore = true)
    Sector toEntity(SectorRequestDTO dto);*/
}
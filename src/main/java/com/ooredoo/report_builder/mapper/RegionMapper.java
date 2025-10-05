package com.ooredoo.report_builder.mapper;

import com.ooredoo.report_builder.dto.request.RegionRequestDTO;
import com.ooredoo.report_builder.dto.response.RegionResponseDTO;
import com.ooredoo.report_builder.entity.Region;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegionMapper {
/*
    @Mapping(target = "zoneId", source = "zone.id")
    @Mapping(target = "managerId", source = "manager.id")
    @Mapping(target = "userIds", expression = "java(region.getUsersInRegion().stream().map(User::getId).collect(Collectors.toSet()))")
    RegionResponseDTO toDto(Region region);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "zone", ignore = true)
    @Mapping(target = "manager", ignore = true)
    @Mapping(target = "usersInRegion", ignore = true)
    Region toEntity(RegionRequestDTO dto);*/
}

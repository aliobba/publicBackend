package com.ooredoo.report_builder.mapper;

import com.ooredoo.report_builder.controller.user.UserResponse;
import com.ooredoo.report_builder.user.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    @Mapping(target = "enterpriseName", source = "enterprise.name")
    @Mapping(target = "sectorName", source = "sector.name")
    @Mapping(target = "zoneName", source = "zone.name")
    @Mapping(target = "regionName", source = "region.name")
    @Mapping(target = "posName", source = "pos.name")
    @Mapping(target = "roles", expression = "java(user.getRoles().stream().map(role -> role.getName().substring(5)).collect(java.util.stream.Collectors.toList()))")
    UserResponse toResponse(User user);
    
    List<UserResponse> toResponseList(List<User> users);
}
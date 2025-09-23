package com.ooredoo.report_builder.mapper;

import com.ooredoo.report_builder.controller.user.UserResponse;
import com.ooredoo.report_builder.dto.request.UserRequest;
import com.ooredoo.report_builder.dto.response.UserResponseDTO;
import com.ooredoo.report_builder.user.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {


    /*@Mapping(target = "roleIds", expression = "java(user.getRoles().stream().map(Role::getId).toList())")
    @Mapping(target = "animatorIds", expression = "java(user.getAnimators().stream().map(Animator::getId).collect(Collectors.toSet()))")
    */
    UserResponseDTO toDto(User user);

    // From Request to Entity (Roles and Animators set manually in Service)
    /*@Mapping(target = "roles", ignore = true)
    User toEntity(UserRequest dto);*/
}
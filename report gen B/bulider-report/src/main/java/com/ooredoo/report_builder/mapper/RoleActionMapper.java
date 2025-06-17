package com.ooredoo.report_builder.mapper;

import com.ooredoo.report_builder.dto.RoleActionDTO;
import com.ooredoo.report_builder.dto.request.CreateRoleActionRequest;
import com.ooredoo.report_builder.dto.request.UpdateRoleActionRequest;
import com.ooredoo.report_builder.dto.response.RoleActionResponse;
import com.ooredoo.report_builder.entity.RoleAction;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoleActionMapper {
    
    // RoleAction mappings
    RoleActionDTO toDTO(RoleAction action);
    RoleAction toEntity(RoleActionDTO dto);
    RoleAction toEntity(CreateRoleActionRequest request);
    RoleActionResponse toResponse(RoleAction action);
    
    @Mapping(target = "id", ignore = true)
    void updateEntityFromRequest(UpdateRoleActionRequest request, @MappingTarget RoleAction action);
}
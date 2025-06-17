package com.ooredoo.report_builder.mapper;

import com.ooredoo.report_builder.dto.AnimatorRoleDTO;
import com.ooredoo.report_builder.dto.request.CreateAnimatorRoleRequest;
import com.ooredoo.report_builder.dto.request.UpdateAnimatorRoleRequest;
import com.ooredoo.report_builder.dto.response.AnimatorRoleResponse;
import com.ooredoo.report_builder.entity.AnimatorRole;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AnimatorRoleMapper {
    
    // AnimatorRole mappings
    AnimatorRoleDTO toDTO(AnimatorRole role);
    AnimatorRole toEntity(AnimatorRoleDTO dto);
    AnimatorRole toEntity(CreateAnimatorRoleRequest request);
    AnimatorRoleResponse toResponse(AnimatorRole role);
    
    @Mapping(target = "id", ignore = true)
    void updateEntityFromRequest(UpdateAnimatorRoleRequest request, @MappingTarget AnimatorRole role);
}
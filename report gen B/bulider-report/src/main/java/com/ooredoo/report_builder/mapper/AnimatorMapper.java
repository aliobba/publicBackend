package com.ooredoo.report_builder.mapper;

import com.ooredoo.report_builder.dto.AnimatorDTO;
import com.ooredoo.report_builder.dto.request.CreateAnimatorRequest;
import com.ooredoo.report_builder.dto.request.UpdateAnimatorRequest;
import com.ooredoo.report_builder.dto.response.AnimatorResponse;
import com.ooredoo.report_builder.entity.Animator;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AnimatorMapper {
    
    // Animator mappings
    AnimatorDTO toDTO(Animator animator);
    Animator toEntity(AnimatorDTO dto);
    Animator toEntity(CreateAnimatorRequest request);
    AnimatorResponse toResponse(Animator animator);
    
    @Mapping(target = "id", ignore = true)
    void updateEntityFromRequest(UpdateAnimatorRequest request, @MappingTarget Animator animator);
}
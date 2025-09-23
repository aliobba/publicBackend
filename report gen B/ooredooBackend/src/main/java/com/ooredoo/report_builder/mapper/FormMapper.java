package com.ooredoo.report_builder.mapper;


import com.ooredoo.report_builder.entity.Form;
import com.ooredoo.report_builder.dto.FormDTO;
import com.ooredoo.report_builder.dto.FormRequestDTO;
import com.ooredoo.report_builder.dto.FormResponseDTO;
import com.ooredoo.report_builder.repo.UserRepository;
import com.ooredoo.report_builder.user.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = FormComponentMapper.class)
public interface FormMapper {

    @Mapping(target = "creatorId", expression = "java(form.getCreator() != null ? form.getCreator().getId() : null)")
    @Mapping(target = "components", expression = "java(form.getComponents().stream().collect(java.util.stream.Collectors.toList()))")
    @Mapping(target = "assignedUserIds", expression = "java(form.getAssignedUsers().stream().map(com.ooredoo.report_builder.user.User::getId).collect(java.util.stream.Collectors.toSet()))")
    FormResponseDTO toFormResponseDTO(Form form);

    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "components", ignore = true)
    @Mapping(target = "assignedUsers", ignore = true)
    Form toEntity(FormRequestDTO dto);

    @AfterMapping
    default void setCreator(@MappingTarget Form form, FormDTO formDTO, @Context UserRepository userRepository) {
        if (formDTO.getCreatorId() != null) {
            User creator = userRepository.findById(formDTO.getCreatorId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            form.setCreator(creator);
        }
    }
}



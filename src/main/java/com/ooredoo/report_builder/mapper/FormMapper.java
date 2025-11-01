package com.ooredoo.report_builder.mapper;

import com.ooredoo.report_builder.dto.*;
import com.ooredoo.report_builder.entity.*;
import com.ooredoo.report_builder.user.User;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FormMapper {

    private final FormComponentMapper formComponentMapper;


    public FormMapper(FormComponentMapper formComponentMapper) {
        this.formComponentMapper = formComponentMapper;
    }

    public Form toEntity(FormRequestDTO dto) {
        if (dto == null) return null;

        Form form = new Form();
        form.setName_Form(dto.getName());
        form.setDescription(dto.getDescription());
        return form;
    }

    public FormResponseDTO toFormResponseDTO(Form entity) {
        if (entity == null) return null;

        FormResponseDTO dto = new FormResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName_Form());
        dto.setDescription(entity.getDescription());
        dto.setCreatedAt(entity.getCreation_Date());
        dto.setUpdatedAt(entity.getUpdatedAt_Date());
        dto.setCreatorId(entity.getCreator() != null ? entity.getCreator().getId() : null);

        // Map active assignments to component DTOs
        if (entity.getComponentAssignments() != null) {
            List<FormComponentDTO> componentDTOs = entity.getComponentAssignments().stream()
                    .filter(FormComponentAssignment::getIsActive)
                    .sorted(Comparator.comparing(FormComponentAssignment::getOrderIndex))
                    .map(assignment -> formComponentMapper.toFormComponentDTO(assignment.getComponent(), assignment))
                    .toList();

            dto.setComponents(componentDTOs.stream().map(formComponentMapper::toFormComponent).collect(Collectors.toList()));
        }

        // Map assigned user IDs
        if (entity.getAssignedUsers() != null) {
            Set<Integer> userIds = entity.getAssignedUsers().stream()
                    .map(User::getId)
                    .collect(Collectors.toSet());
            dto.setAssignedUserIds(userIds);
        }

        return dto;
    }


    private ComponentPropertyDTO toComponentPropertyDTO(ComponentProperty property) {
        if (property == null) return null;

        ComponentPropertyDTO dto = new ComponentPropertyDTO();
        dto.setId(property.getId());
        dto.setPropertyName(property.getPropertyName());
        dto.setPropertyValue(property.getPropertyValue());
        dto.setComponentId(property.getComponent() != null ? property.getComponent().getId() : null);
        return dto;
    }

    private ElementOptionDTO toElementOptionDTO(ElementOption option) {
        if (option == null) return null;

        ElementOptionDTO dto = new ElementOptionDTO();
        dto.setId(option.getId());
        dto.setLabel(option.getLabel());
        dto.setValue(option.getValue());
        dto.setDisplayOrder(option.getDisplayOrder());
        dto.setComponentId(option.getComponent() != null ? option.getComponent().getId() : null);
        return dto;
    }
}

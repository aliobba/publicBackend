package com.ooredoo.report_builder.mapper;

import com.ooredoo.report_builder.dto.ComponentPropertyDTO;
import com.ooredoo.report_builder.dto.ElementOptionDTO;
import com.ooredoo.report_builder.dto.FormComponentDTO;
import com.ooredoo.report_builder.entity.ComponentProperty;
import com.ooredoo.report_builder.entity.ElementOption;
import com.ooredoo.report_builder.entity.FormComponent;
import com.ooredoo.report_builder.entity.FormComponentAssignment;
import com.ooredoo.report_builder.enums.ComponentType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class FormComponentMapper {

    public FormComponent toFormComponent(FormComponentDTO dto) {
        if (dto == null) return null;

        FormComponent component = new FormComponent();
        component.setElementType(ComponentType.fromValue(dto.getElementType().getValue()));
        component.setLabel(dto.getLabel());
        component.setRequired(dto.getRequired() != null ? dto.getRequired() : false);
        component.setOrderIndex(dto.getOrderIndex() != null ? dto.getOrderIndex() : 0);
        component.setIsGlobal(dto.getIsGlobal() != null ? dto.getIsGlobal() : false);
        return component;
    }

    public FormComponentDTO toFormComponentDTO(FormComponent entity, FormComponentAssignment assignment) {
        if (entity == null) return null;

        FormComponentDTO dto = new FormComponentDTO();
        dto.setId(entity.getId());
        dto.setElementType(entity.getElementType());
        dto.setLabel(entity.getLabel());
        dto.setRequired(entity.getRequired());
        dto.setOrderIndex(assignment.getOrderIndex());
        dto.setGlobal(entity.getIsGlobal());
        dto.setCreatedBy(entity.getCreatedBy() != null ? entity.getCreatedBy() : null);
        dto.setFormId(assignment.getForm().getId());
        dto.setActive(assignment.getIsActive());
        dto.setCreatedAt(entity.getCreatedAt());

        // Convert properties
        if (entity.getProperties() != null) {
            List<ComponentPropertyDTO> propertyDTOs = entity.getProperties().stream()
                    .map(this::toComponentPropertyDTO)
                    .collect(Collectors.toList());
            dto.setProperties(propertyDTOs);
        }

        // Convert options
        if (entity.getOptions() != null) {
            List<ElementOptionDTO> optionDTOs = entity.getOptions().stream()
                    .map(this::toElementOptionDTO)
                    .collect(Collectors.toList());
            dto.setOptions(optionDTOs);
        }

        return dto;
    }
    public FormComponentDTO toFormComponentDTO(FormComponent entity) {
        if (entity == null) return null;

        FormComponentDTO dto = new FormComponentDTO();
        dto.setId(entity.getId());
        dto.setElementType(entity.getElementType());
        dto.setLabel(entity.getLabel());
        dto.setRequired(entity.getRequired());
        dto.setOrderIndex(entity.getOrderIndex());
        dto.setIsGlobal(entity.getIsGlobal());
        dto.setCreatedBy(entity.getCreatedBy() != null ? entity.getCreatedBy() : null);
        dto.setCreatedAt(entity.getCreatedAt());

        // Convert properties
        if (entity.getProperties() != null) {
            List<ComponentPropertyDTO> propertyDTOs = entity.getProperties().stream()
                    .map(this::toComponentPropertyDTO)
                    .collect(Collectors.toList());
            dto.setProperties(propertyDTOs);
        }

        // Convert options
        if (entity.getOptions() != null) {
            List<ElementOptionDTO> optionDTOs = entity.getOptions().stream()
                    .map(this::toElementOptionDTO)
                    .collect(Collectors.toList());
            dto.setOptions(optionDTOs);
        }

        return dto;
    }

    private ComponentPropertyDTO toComponentPropertyDTO(ComponentProperty property) {
        ComponentPropertyDTO dto = new ComponentPropertyDTO();
        dto.setId(property.getId());
        dto.setPropertyName(property.getPropertyName());
        dto.setPropertyValue(property.getPropertyValue());
        dto.setComponentId(property.getComponent() != null ? property.getComponent().getId() : null);
        return dto;
    }

    private ElementOptionDTO toElementOptionDTO(ElementOption option) {
        ElementOptionDTO dto = new ElementOptionDTO();
        dto.setId(option.getId());
        dto.setLabel(option.getLabel());
        dto.setValue(option.getValue());
        dto.setDisplayOrder(option.getDisplayOrder());
        dto.setComponentId(option.getComponent() != null ? option.getComponent().getId() : null);
        return dto;
    }
}

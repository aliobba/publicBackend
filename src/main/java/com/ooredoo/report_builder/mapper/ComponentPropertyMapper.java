package com.ooredoo.report_builder.mapper;


import com.ooredoo.report_builder.dto.ComponentPropertyDTO;
import com.ooredoo.report_builder.entity.ComponentProperty;
import org.springframework.stereotype.Component;

@Component
public class ComponentPropertyMapper {

    public ComponentProperty toComponentProperty(ComponentPropertyDTO dto) {
        if (dto == null) return null;

        ComponentProperty property = new ComponentProperty();
        property.setPropertyName(dto.getPropertyName());
        property.setPropertyValue(dto.getPropertyValue());
        return property;
    }

    public ComponentPropertyDTO toComponentPropertyDTO(ComponentProperty entity) {
        if (entity == null) return null;

        ComponentPropertyDTO dto = new ComponentPropertyDTO();
        dto.setId(entity.getId());
        dto.setPropertyName(entity.getPropertyName());
        dto.setPropertyValue(entity.getPropertyValue());
        dto.setComponentId(entity.getComponent() != null ? entity.getComponent().getId() : null);
        return dto;
    }
}

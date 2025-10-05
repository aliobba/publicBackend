package com.ooredoo.report_builder.mapper;


import com.ooredoo.report_builder.dto.ElementOptionDTO;
import com.ooredoo.report_builder.entity.ElementOption;
import org.springframework.stereotype.Component;

@Component
public class ElementOptionMapper {

    public ElementOption toElementOption(ElementOptionDTO dto) {
        if (dto == null) return null;

        ElementOption option = new ElementOption();
        option.setLabel(dto.getLabel());
        option.setValue(dto.getValue());
        option.setDisplayOrder(dto.getDisplayOrder() != null ? dto.getDisplayOrder() : 0);
        return option;
    }

    public ElementOptionDTO toElementOptionDTO(ElementOption entity) {
        if (entity == null) return null;

        ElementOptionDTO dto = new ElementOptionDTO();
        dto.setId(entity.getId());
        dto.setLabel(entity.getLabel());
        dto.setValue(entity.getValue());
        dto.setDisplayOrder(entity.getDisplayOrder());
        dto.setComponentId(entity.getComponent() != null ? entity.getComponent().getId() : null);
        return dto;
    }
}

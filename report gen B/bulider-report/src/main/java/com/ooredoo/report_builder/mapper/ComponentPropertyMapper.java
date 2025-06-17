package com.ooredoo.report_builder.mapper;

import com.ooredoo.report_builder.entity.ComponentProperty;
import com.ooredoo.report_builder.entity.FormComponent;
import com.ooredoo.report_builder.dto.ComponentPropertyDTO;
import com.ooredoo.report_builder.repo.FormComponentRepository;

import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ComponentPropertyMapper {

    @Mapping(target = "componentId", expression = "java(property.getComponent() != null ? property.getComponent().getId() : null)")
    ComponentPropertyDTO toComponentPropertyDTO(ComponentProperty property);

    @Mapping(target = "component", ignore = true)
    ComponentProperty toComponentProperty(ComponentPropertyDTO componentPropertyDTO);

    @AfterMapping
    default void setComponent(@MappingTarget ComponentProperty componentProperty,
                              ComponentPropertyDTO componentPropertyDTO,
                              @Context FormComponentRepository formComponentRepository) {
        if (componentPropertyDTO.getComponentId() != null) {
            FormComponent component = formComponentRepository.findById(componentPropertyDTO.getComponentId())
                    .orElseThrow(() -> new RuntimeException("FormComponent not found"));
            componentProperty.setComponent(component);
        }
    }
}

package com.ooredoo.report_builder.mapper;

import com.ooredoo.report_builder.entity.Form;
import com.ooredoo.report_builder.entity.FormComponent;
import com.ooredoo.report_builder.dto.FormComponentDTO;
import com.ooredoo.report_builder.repo.FormRepository;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = { ComponentPropertyMapper.class, ElementOptionMapper.class })
public interface FormComponentMapper {

    @Mapping(target = "formId", expression = "java(formComponent.getForm() != null ? formComponent.getForm().getId() : null)")
    FormComponentDTO toFormComponentDTO(FormComponent formComponent);

    @Mapping(target = "form", ignore = true)
    FormComponent toFormComponent(FormComponentDTO formComponentDTO);

    @AfterMapping
    default void setForm(@MappingTarget FormComponent formComponent, FormComponentDTO formComponentDTO, @Context FormRepository formRepository) {
        if (formComponentDTO.getFormId() != null) {
            Form form = formRepository.findById(formComponentDTO.getFormId())
                    .orElseThrow(() -> new RuntimeException("Form not found"));
            formComponent.setForm(form);
        }
    }
}

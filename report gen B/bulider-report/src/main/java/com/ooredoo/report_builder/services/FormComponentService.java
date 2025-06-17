package com.ooredoo.report_builder.services;


import com.ooredoo.report_builder.entity.Form;
import com.ooredoo.report_builder.entity.FormComponent;
import com.ooredoo.report_builder.dto.FormComponentDTO;
import com.ooredoo.report_builder.mapper.FormComponentMapper;
import com.ooredoo.report_builder.repo.FormComponentRepository;
import com.ooredoo.report_builder.repo.FormRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FormComponentService {

    private final FormComponentRepository formComponentRepository;
    private final FormRepository formRepository;
    private final FormComponentMapper formComponentMapper;

    public FormComponentService(FormComponentRepository formComponentRepository, FormRepository formRepository, FormComponentMapper formComponentMapper) {
        this.formComponentRepository = formComponentRepository;
        this.formRepository = formRepository;
        this.formComponentMapper = formComponentMapper;
    }

    // Get all components
    public List<FormComponentDTO> getAllComponents() {
        List<FormComponent> components = formComponentRepository.findAll();
        return components.stream()
                .map(formComponentMapper::toFormComponentDTO)
                .collect(Collectors.toList());
    }

    // Get component by ID
    public Optional<FormComponentDTO> getComponentById(Integer id) {
        return formComponentRepository.findById(id)
                .map(formComponentMapper::toFormComponentDTO);
    }

    // Create a new component
    @Transactional
    public FormComponentDTO createComponent(FormComponentDTO componentDTO, Integer formId) {
        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new RuntimeException("Form not found"));

        // Use mapper to convert DTO to entity
        FormComponent component = formComponentMapper.toFormComponent(componentDTO);
        component.setForm(form);

        // Get the highest current order index and add 1
        Integer maxOrderIndex = formComponentRepository.findByFormIdOrderByOrderIndexAsc(formId)
                .stream()
                .map(FormComponent::getOrderIndex)
                .max(Integer::compareTo)
                .orElse(0);

        component.setOrderIndex(maxOrderIndex + 1);
        FormComponent savedComponent = formComponentRepository.save(component);
        return formComponentMapper.toFormComponentDTO(savedComponent);
    }

    // Update an existing component
    @Transactional
    public FormComponentDTO updateComponent(Integer id, FormComponentDTO componentDTO) {
        FormComponent component = formComponentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Component not found"));

        component.setElementType(componentDTO.getElementType());
        component.setLabel(componentDTO.getLabel());
        component.setRequired(componentDTO.getRequired());

        FormComponent updatedComponent = formComponentRepository.save(component);
        return formComponentMapper.toFormComponentDTO(updatedComponent);
    }

    // Delete a component
    @Transactional
    public void deleteComponent(Integer id) {
        formComponentRepository.deleteById(id);
    }
    
    // Manual mapping method removed in favor of using the mapper directly

    // Reorder components
    @Transactional
    public void reorderComponents(Integer formId, List<Integer> componentIds) {
        for (int i = 0; i < componentIds.size(); i++) {
            FormComponent component = formComponentRepository.findById(componentIds.get(i))
                    .orElseThrow(() -> new RuntimeException("Component not found"));
            component.setOrderIndex(i + 1);
            formComponentRepository.save(component);
        }
    }

}

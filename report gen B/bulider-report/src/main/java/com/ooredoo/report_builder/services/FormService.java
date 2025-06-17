package com.ooredoo.report_builder.services;


import com.ooredoo.report_builder.dto.*;
import com.ooredoo.report_builder.entity.ComponentProperty;
import com.ooredoo.report_builder.entity.ElementOption;
import com.ooredoo.report_builder.entity.Form;
import com.ooredoo.report_builder.entity.FormComponent;
import com.ooredoo.report_builder.mapper.ComponentPropertyMapper;
import com.ooredoo.report_builder.mapper.ElementOptionMapper;
import com.ooredoo.report_builder.mapper.FormComponentMapper;
import com.ooredoo.report_builder.mapper.FormMapper;
import com.ooredoo.report_builder.handler.ResourceNotFoundException;
import com.ooredoo.report_builder.repo.*;
import com.ooredoo.report_builder.user.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class FormService {

    private final FormRepository formRepository;
    private final FormComponentRepository componentRepository;
    private final ComponentPropertyRepository propertyRepository;
    private final ElementOptionRepository optionRepository;
    private final UserRepository userRepository;
    private final FormMapper formMapper;
    private final FormComponentMapper componentMapper;
    private final ComponentPropertyMapper propertyMapper;
    private final ElementOptionMapper optionMapper;

    public FormService(FormRepository formRepository, FormComponentRepository componentRepository, ComponentPropertyRepository propertyRepository, ElementOptionRepository optionRepository, UserRepository userRepository, FormMapper formMapper, FormComponentMapper componentMapper, ComponentPropertyMapper propertyMapper, ElementOptionMapper optionMapper) {
        this.formRepository = formRepository;
        this.componentRepository = componentRepository;
        this.propertyRepository = propertyRepository;
        this.optionRepository = optionRepository;
        this.userRepository = userRepository;
        this.formMapper = formMapper;
        this.componentMapper = componentMapper;
        this.propertyMapper = propertyMapper;
        this.optionMapper = optionMapper;
    }

    // --- Form Section ---
    public FormResponseDTO createForm(FormRequestDTO request, User creator) {
        // Use mapper to convert DTO to entity
        Form form = formMapper.toEntity(request);
        form.setCreator(creator);
        
        return formMapper.toFormResponseDTO(formRepository.save(form));
    }

    public FormResponseDTO updateForm(Integer formId, FormRequestDTO request) {
        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found"));

        form.setName(request.getName());
        form.setDescription(request.getDescription());

        return formMapper.toFormResponseDTO(formRepository.save(form));
    }

    public void deleteForm(Integer formId) {
        formRepository.deleteById(formId);
    }

    public List<FormResponseDTO> getAllForms() {
        return formRepository.findAll().stream()
                .map(formMapper::toFormResponseDTO)
                .collect(Collectors.toList());
    }

    public List<FormResponseDTO> getFormsCreatedBy(User user) {
        return formRepository.findByCreator(user).stream()
                .map(formMapper::toFormResponseDTO)
                .collect(Collectors.toList());
    }

    public FormResponseDTO getFormById(Integer formId) {
        return formRepository.findById(formId)
                .map(formMapper::toFormResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found"));
    }

    public void assignFormToUsers(Integer formId, List<Integer> userIds) {
        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found"));

        List<User> users = userRepository.findAllById(userIds);
        form.getAssignedUsers().addAll(users);
        formRepository.save(form);
    }

    public void unassignFormFromUser(Integer formId, Integer userId) {
        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        form.getAssignedUsers().remove(user);
        formRepository.save(form);
    }

    // --- Component Section ---
    public FormComponentDTO addComponent(Integer formId, FormComponentDTO componentDTO) {
        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found"));

        FormComponent component = componentMapper.toFormComponent(componentDTO);
        component.setForm(form);

        return componentMapper.toFormComponentDTO(componentRepository.save(component));
    }

    public FormComponentDTO updateComponent(Integer componentId, FormComponentDTO componentDTO) {
        FormComponent component = componentRepository.findById(componentId)
                .orElseThrow(() -> new ResourceNotFoundException("Component not found"));

        component.setLabel(componentDTO.getLabel());
        component.setElementType(componentDTO.getElementType());
        component.setRequired(componentDTO.getRequired());


        return componentMapper.toFormComponentDTO(componentRepository.save(component));
    }

    public void deleteComponent(Integer componentId) {
        componentRepository.deleteById(componentId);
    }

    // --- ComponentProperty Section ---
    public ComponentPropertyDTO addComponentProperty(Integer componentId, ComponentPropertyDTO propertyDTO) {
        FormComponent component = componentRepository.findById(componentId)
                .orElseThrow(() -> new ResourceNotFoundException("Component not found"));

        ComponentProperty property = propertyMapper.toComponentProperty(propertyDTO);
        property.setComponent(component);

        return propertyMapper.toComponentPropertyDTO(propertyRepository.save(property));
    }

    public ComponentPropertyDTO updateComponentProperty(Integer propertyId, String value) {
        ComponentProperty property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));

        property.setPropertyValue(value);
        return propertyMapper.toComponentPropertyDTO(propertyRepository.save(property));
    }

    public void deleteComponentProperty(Integer propertyId) {
        propertyRepository.deleteById(propertyId);
    }

    // --- ElementOption Section ---
    public ElementOptionDTO addElementOption(Integer componentId, ElementOptionDTO optionDTO) {
        FormComponent component = componentRepository.findById(componentId)
                .orElseThrow(() -> new ResourceNotFoundException("Component not found"));

        ElementOption option = optionMapper.toElementOption(optionDTO);
        option.setComponent(component);

        return optionMapper.toElementOptionDTO(optionRepository.save(option));
    }

    public ElementOptionDTO updateElementOption(Integer optionId, String label, String value) {
        ElementOption option = optionRepository.findById(optionId)
                .orElseThrow(() -> new ResourceNotFoundException("Option not found"));

        option.setLabel(label);
        option.setValue(value);

        return optionMapper.toElementOptionDTO(optionRepository.save(option));
    }

    public void deleteElementOption(Integer optionId) {
        optionRepository.deleteById(optionId);
    }
    
    // Manual mapping method removed in favor of using the mapper directly
}
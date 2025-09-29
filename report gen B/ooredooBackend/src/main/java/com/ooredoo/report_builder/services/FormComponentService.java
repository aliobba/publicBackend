package com.ooredoo.report_builder.services;


import com.ooredoo.report_builder.dto.ComponentTemplateDTO;
import com.ooredoo.report_builder.dto.ElementOptionDTO;
import com.ooredoo.report_builder.dto.FormComponentDTO;
import com.ooredoo.report_builder.entity.*;
import com.ooredoo.report_builder.enums.ComponentType;
import com.ooredoo.report_builder.handler.ResourceNotFoundException;
import com.ooredoo.report_builder.mapper.FormComponentMapper;
import com.ooredoo.report_builder.repo.*;
import com.ooredoo.report_builder.user.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class FormComponentService {

    private final FormComponentRepository formComponentRepository;
    private final FormRepository formRepository;
    private final FormComponentAssignmentRepository assignmentRepository;
    private final ComponentDefaultsService defaultsService;
    private final ComponentPropertyRepository propertyRepository;
    private final ElementOptionRepository optionRepository;
    private final FormComponentMapper formComponentMapper;
    private final UserRepository userRepository;
    private final FormSubmissionService submissionService;

    public FormComponentService(
            FormComponentRepository formComponentRepository,
            FormRepository formRepository,
            FormComponentAssignmentRepository assignmentRepository,
            ComponentDefaultsService defaultsService,
            ComponentPropertyRepository propertyRepository,
            ElementOptionRepository optionRepository,
            FormComponentMapper formComponentMapper,
            UserRepository userRepository,
            FormSubmissionService submissionService) {

        this.formComponentRepository = formComponentRepository;
        this.formRepository = formRepository;
        this.assignmentRepository = assignmentRepository;
        this.defaultsService = defaultsService;
        this.propertyRepository = propertyRepository;
        this.optionRepository = optionRepository;
        this.formComponentMapper = formComponentMapper;
        this.userRepository = userRepository;
        this.submissionService = submissionService;
    }

    // === COMPONENT CREATION WITH DEFAULTS ===

    @Transactional
    public FormComponentDTO createComponentWithDefaults(FormComponentDTO componentDTO, Integer formId, User creator) {
        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found with id: " + formId));
        componentDTO.setFormId(formId);
        ComponentType componentType = ComponentType.fromValue(componentDTO.getElementType().getValue());

        // Create the component
        FormComponent component = new FormComponent();
        component.setElementType(componentType);
        component.setLabel(componentDTO.getLabel());
        component.setRequired(componentDTO.getRequired());
        component.setIsGlobal(componentDTO.getIsGlobal());
        component.setCreatedBy(creator);
        component.getForms().add(form);

        FormComponent savedComponent = formComponentRepository.save(component);

        // Add default properties if requested
        if (savedComponent.getProperties().isEmpty()||savedComponent.getOptions().isEmpty()) {
            if (savedComponent.getElementType()
                    .getValue()
                    .equals(ComponentType.CHECKBOX) || savedComponent.getElementType()
                    .getValue()
                    .equals(ComponentType.RADIO) || savedComponent.getElementType()
                    .getValue()
                    .equals(ComponentType.DROPDOWN) ){
                addDefaultOptions(savedComponent, componentType);
            } else if (savedComponent.getElementType().equals(ComponentType.FILE_UPLOAD)) {

            }
            addDefaultProperties(savedComponent, componentType);
        }

        // Assign to form
        Integer maxOrderIndex = getMaxOrderIndexForForm(formId);
        FormComponentAssignment assignment = new FormComponentAssignment();
        assignment.setForm(form);
        assignment.setComponent(savedComponent);
        assignment.setOrderIndex(maxOrderIndex + 1);
        assignmentRepository.save(assignment);

        return formComponentMapper.toFormComponentDTO(savedComponent);
    }

    private void addDefaultProperties(FormComponent component, ComponentType componentType) {
        Map<String, String> defaultProperties = defaultsService.getDefaultProperties(componentType);

        for (Map.Entry<String, String> entry : defaultProperties.entrySet()) {
            ComponentProperty property = new ComponentProperty();
            property.setPropertyName(entry.getKey());
            property.setPropertyValue(entry.getValue());
            property.setComponent(component);
            propertyRepository.save(property);
        }
    }

    private void addDefaultOptions(FormComponent component, ComponentType componentType) {
        if (defaultsService.isOptionBasedComponent(componentType)) {
            List<ElementOptionDTO> defaultOptions = defaultsService.getDefaultOptions(componentType);

            for (ElementOptionDTO optionDTO : defaultOptions) {
                ElementOption option = new ElementOption();
                option.setLabel(optionDTO.getLabel());
                option.setValue(optionDTO.getValue());
                option.setDisplayOrder(optionDTO.getDisplayOrder());
                option.setComponent(component);
                optionRepository.save(option);
            }
        }
    }

    // === COMPONENT ASSIGNMENT MANAGEMENT ===

    @Transactional
    public void assignComponentToForm(Integer componentId, Integer formId, Integer orderIndex) {
        FormComponent component = formComponentRepository.findById(componentId)
                .orElseThrow(() -> new ResourceNotFoundException("Component not found with id: " + componentId));

        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found with id: " + formId));

        Integer finalOrderIndex = orderIndex != null ? orderIndex : getMaxOrderIndexForForm(formId) + 1;
        FormComponentAssignment assignment = new FormComponentAssignment();
        assignment.setForm(form);
        assignment.setComponent(component);
        assignment.setOrderIndex(finalOrderIndex);
        assignmentRepository.save(assignment);
    }

    @Transactional
    public void unassignComponentFromForm(Integer componentId, Integer formId) {
        FormComponentAssignment assignment = assignmentRepository
                .findByFormIdAndComponentIdAndIsActive(formId, componentId, true)
                .orElseThrow(() -> new ResourceNotFoundException("Component assignment not found"));

        // Clean up submission values for this specific assignment
        submissionService.cleanupSubmissionValuesForRemovedAssignment(assignment.getId());

        // Soft delete: mark assignment as inactive
        assignment.unassign();
        assignmentRepository.save(assignment);
    }

    @Transactional
    public void reorderFormComponents(Integer formId, List<Integer> componentIds) {
        List<FormComponentAssignment> assignments = assignmentRepository.findByFormIdAndIsActiveOrderByOrderIndex(formId, true);

        Map<Integer, FormComponentAssignment> assignmentMap = assignments.stream()
                .collect(Collectors.toMap(a -> a.getComponent().getId(), a -> a));

        for (int i = 0; i < componentIds.size(); i++) {
            Integer componentId = componentIds.get(i);
            FormComponentAssignment assignment = assignmentMap.get(componentId);

            if (assignment != null) {
                assignment.setOrderIndex(i + 1);
                assignmentRepository.save(assignment);
            }
        }
    }

    // === COMPONENT CRUD OPERATIONS ===

    @Transactional
    public List<FormComponentDTO> getFormComponents(Integer formId) {
        List<FormComponentAssignment> assignments = assignmentRepository
                .findByFormIdAndIsActiveOrderByOrderIndex(formId, true);

        return assignments.stream()
                .map(assignment -> formComponentMapper.toFormComponentDTO(assignment.getComponent()))
                .collect(Collectors.toList());
    }

    @Transactional
    public FormComponentDTO getComponentById(Integer componentId) {
        FormComponent component = formComponentRepository.findById(componentId)
                .orElseThrow(() -> new ResourceNotFoundException("Component not found with id: " + componentId));

        return formComponentMapper.toFormComponentDTO(component);
    }

    @Transactional
    public FormComponentDTO updateComponent(Integer componentId, FormComponentDTO componentDTO) {
        FormComponent component = formComponentRepository.findById(componentId)
                .orElseThrow(() -> new ResourceNotFoundException("Component not found with id: " + componentId));

        component.setLabel(componentDTO.getLabel());
        component.setRequired(componentDTO.getRequired());

        if (!component.getElementType().getValue().equals(componentDTO.getElementType())) {
            ComponentType newType = ComponentType.fromValue(componentDTO.getElementType().getValue());
            handleComponentTypeChange(component, newType);
        }

        FormComponent updatedComponent = formComponentRepository.save(component);
        return formComponentMapper.toFormComponentDTO(updatedComponent);
    }

    @Transactional
    public void deleteComponent(Integer componentId) {
        FormComponent component = formComponentRepository.findById(componentId)
                .orElseThrow(() -> new ResourceNotFoundException("Component not found with id: " + componentId));

        // Get all active assignments for this component
        List<FormComponentAssignment> activeAssignments = assignmentRepository
                .findByComponentIdAndIsActive(componentId, true);

        // Clean up submission values for each assignment
        for (FormComponentAssignment assignment : activeAssignments) {
            submissionService.cleanupSubmissionValuesForRemovedAssignment(assignment.getId());
        }

        // Deactivate all assignments
        assignmentRepository.deactivateAllAssignmentsForComponent(componentId);

        // If not global component or no active assignments, hard delete
        if (!component.getIsGlobal() || !hasActiveForms(componentId)) {
            formComponentRepository.delete(component);
        }
    }

    // === COMPONENT TEMPLATES ===

    @Transactional
    public List<ComponentTemplateDTO> getAvailableComponentTemplates() {
        List<ComponentTemplateDTO> templates = new ArrayList<>();

        for (ComponentType type : ComponentType.values()) {
            ComponentTemplateDTO template = new ComponentTemplateDTO();
            template.setElementType(type.getValue());
            template.setDisplayName(type.getDisplayName());
            template.setDescription(getComponentDescription(type));
            template.setIconClass(getComponentIcon(type));
            template.setDefaultProperties(defaultsService.getDefaultProperties(type));
            template.setDefaultOptions(defaultsService.getDefaultOptions(type));
            template.setRequiresOptions(defaultsService.isOptionBasedComponent(type));
            template.setSupportsFileUpload(defaultsService.requiresFileHandling(type));

            templates.add(template);
        }

        return templates;
    }

    private String getComponentDescription(ComponentType type) {
        switch (type) {
            case TEXT:
                return "Single line text input field";
            case EMAIL:
                return "Email address input with validation";
            case NUMBER:
                return "Numeric input with min/max validation";
            case TEXTAREA:
                return "Multi-line text input area";
            case DROPDOWN:
                return "Dropdown selection list";
            case RADIO:
                return "Single selection from multiple options";
            case CHECKBOX:
                return "Multiple selection from options";
            case DATE:
                return "Date picker with calendar";
            case FILE_UPLOAD:
                return "File upload with drag and drop";
            default:
                return "Form component";
        }
    }

    private String getComponentIcon(ComponentType type) {
        switch (type) {
            case TEXT:
                return "fa-font";
            case EMAIL:
                return "fa-envelope";
            case NUMBER:
                return "fa-calculator";
            case TEXTAREA:
                return "fa-align-left";
            case DROPDOWN:
                return "fa-list";
            case RADIO:
                return "fa-dot-circle";
            case CHECKBOX:
                return "fa-check-square";
            case DATE:
                return "fa-calendar";
            case FILE_UPLOAD:
                return "fa-upload";
            default:
                return "fa-square";
        }
    }

    // === GLOBAL COMPONENT MANAGEMENT ===

    @Transactional
    public List<FormComponentDTO> getGlobalComponents(User creator) {
        List<FormComponent> globalComponents = formComponentRepository.findByIsGlobalAndCreatedBy(true, creator);
        return globalComponents.stream()
                .map(formComponentMapper::toFormComponentDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<FormComponentDTO> getReusableComponents(User user) {
        List<FormComponent> reusableComponents = formComponentRepository.findReusableComponentsForUser(user.getId());
        return reusableComponents.stream()
                .map(formComponentMapper::toFormComponentDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public FormComponentDTO cloneComponent(Integer componentId, Integer targetFormId, User creator) {
        FormComponent originalComponent = formComponentRepository.findById(componentId)
                .orElseThrow(() -> new ResourceNotFoundException("Component not found with id: " + componentId));

        Form targetForm = formRepository.findById(targetFormId)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found with id: " + targetFormId));

        // Create a clone
        FormComponent clonedComponent = new FormComponent();
        clonedComponent.setElementType(originalComponent.getElementType());
        clonedComponent.setLabel(originalComponent.getLabel() + " (Copy)");
        clonedComponent.setRequired(originalComponent.getRequired());
        clonedComponent.setIsGlobal(false);
        clonedComponent.setCreatedBy(creator);

        FormComponent savedClone = formComponentRepository.save(clonedComponent);

        // Clone properties
        for (ComponentProperty originalProperty : originalComponent.getProperties()) {
            ComponentProperty clonedProperty = new ComponentProperty();
            clonedProperty.setPropertyName(originalProperty.getPropertyName());
            clonedProperty.setPropertyValue(originalProperty.getPropertyValue());
            clonedProperty.setComponent(savedClone);
            propertyRepository.save(clonedProperty);
        }

        // Clone options
        for (ElementOption originalOption : originalComponent.getOptions()) {
            ElementOption clonedOption = new ElementOption();
            clonedOption.setLabel(originalOption.getLabel());
            clonedOption.setValue(originalOption.getValue());
            clonedOption.setDisplayOrder(originalOption.getDisplayOrder());
            clonedOption.setComponent(savedClone);
            optionRepository.save(clonedOption);
        }

        // Assign to target form
        Integer maxOrderIndex = getMaxOrderIndexForForm(targetFormId);
        FormComponentAssignment assignment = new FormComponentAssignment(targetForm, savedClone, maxOrderIndex + 1);
        assignmentRepository.save(assignment);

        return formComponentMapper.toFormComponentDTO(savedClone);
    }

    // === HELPER METHODS ===

    private Integer getMaxOrderIndexForForm(Integer formId) {
        return assignmentRepository.findMaxOrderIndexByFormId(formId).orElse(0);
    }

    private boolean hasActiveForms(Integer componentId) {
        return assignmentRepository.countByComponentIdAndIsActive(componentId, true) > 0;
    }

    private void handleComponentTypeChange(FormComponent component, ComponentType newType) {
        component.setElementType(newType);
        propertyRepository.deleteByComponentId(component.getId());
        optionRepository.deleteByComponentId(component.getId());
        addDefaultProperties(component, newType);
        addDefaultOptions(component, newType);
    }
}

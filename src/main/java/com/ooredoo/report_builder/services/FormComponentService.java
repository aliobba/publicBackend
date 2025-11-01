package com.ooredoo.report_builder.services;


import com.ooredoo.report_builder.dto.ComponentPropertyDTO;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.Set;
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

    // === GENERIC COMPONENT MANAGEMENT ===

    @Transactional
    public FormComponent findOrCreateGenericComponent(ComponentType componentType, User creator) {
        // First try to find an existing generic component of this type
        Optional<FormComponent> existingComponent = formComponentRepository.findFirstGenericComponentByType(componentType);
        
        if (existingComponent.isPresent()) {
            return existingComponent.get();
        }
        
        // If no generic component exists, create one
        FormComponent genericComponent = new FormComponent();
        genericComponent.setElementType(componentType);
        genericComponent.setComponentName(componentType.getDisplayName() + " Component");
        genericComponent.setIsGlobal(true);
        genericComponent.setCreatedBy(creator);
        genericComponent.setCreation_Date(LocalDateTime.now());
        genericComponent.setUpdatedAt_Date(LocalDateTime.now());
        
        FormComponent savedComponent = formComponentRepository.save(genericComponent);
        
        // Add default properties and options
        addDefaultProperties(savedComponent, componentType);
        if (isOptionBasedComponent(componentType)) {
            addDefaultOptions(savedComponent, componentType);
        }
        
        return savedComponent;
    }

    @Transactional
    public FormComponentDTO createComponentWithDefaults(FormComponentDTO componentDTO, Integer formId, User creator) {

        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found with id: " + formId));
        
        ComponentType componentType = ComponentType.fromValue(componentDTO.getElementType().getValue());

        // Find or create generic component
        FormComponent genericComponent = findOrCreateGenericComponent(componentType, creator);

        // Create form-specific assignment
        Integer maxOrderIndex = getMaxOrderIndexForForm(formId);
        FormComponentAssignment assignment = new FormComponentAssignment();
        assignment.setForm(form);
        assignment.setComponent(genericComponent);
        assignment.setOrderIndex(maxOrderIndex + 1);
        assignment.setLabel(componentDTO.getLabel() != null && !componentDTO.getLabel().trim().isEmpty() 
            ? componentDTO.getLabel() 
            : genericComponent.getComponentName());
        assignment.setRequired(componentDTO.getRequired() != null ? componentDTO.getRequired() : false);
        assignment.setPlaceholder(componentDTO.getPlaceholder());
        assignment.setIsActive(true);
        assignment.setAssigned_Date(LocalDateTime.now());
        
        FormComponentAssignment savedAssignment = assignmentRepository.save(assignment);

        return formComponentMapper.toFormComponentDTO(genericComponent, savedAssignment);
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
    public void assignComponentToForm(Integer componentId, Integer formId, Integer orderIndex, String label, Boolean required, String placeholder) {
        FormComponent component = formComponentRepository.findById(componentId)
                .orElseThrow(() -> new ResourceNotFoundException("Component not found with id: " + componentId));

        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found with id: " + formId));

        Integer finalOrderIndex = orderIndex != null ? orderIndex : getMaxOrderIndexForForm(formId) + 1;
        FormComponentAssignment assignment = new FormComponentAssignment();
        assignment.setForm(form);
        assignment.setComponent(component);
        assignment.setOrderIndex(finalOrderIndex);
        assignment.setLabel(label != null ? label : (component.getComponentName() != null ? component.getComponentName() : "Unnamed Component"));
        assignment.setRequired(required != null ? required : false);
        assignment.setPlaceholder(placeholder);
        assignment.setIsActive(true);
        assignment.setAssigned_Date(LocalDateTime.now());
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
        assignment.getUnassigned_Date();
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

        if (assignments.isEmpty()) {
            return new ArrayList<>();
        }

        // Extract component IDs
        List<Integer> componentIds = assignments.stream()
                .map(a -> a.getComponent().getId())
                .distinct()
                .collect(Collectors.toList());

        // Batch fetch properties and options to avoid lazy loading issues
        assignmentRepository.fetchPropertiesForComponents(componentIds);
        assignmentRepository.fetchOptionsForComponents(componentIds);

        return assignments.stream()
                .map(assignment -> formComponentMapper.toFormComponentDTO(assignment.getComponent(), assignment))
                .collect(Collectors.toList());
    }

    @Transactional
    public FormComponentDTO getComponentById(Integer componentId) {
        FormComponent component = formComponentRepository.findById(componentId)
                .orElseThrow(() -> new ResourceNotFoundException("Component not found with id: " + componentId));

        // Batch fetch properties and options to avoid lazy loading issues
        assignmentRepository.fetchPropertiesForComponents(List.of(componentId));
        assignmentRepository.fetchOptionsForComponents(List.of(componentId));

        return formComponentMapper.toFormComponentDTO(component);
    }

    @Transactional
    public FormComponentDTO updateComponent(Integer componentId, FormComponentDTO componentDTO) {
        FormComponent component = formComponentRepository.findById(componentId)
                .orElseThrow(() -> new ResourceNotFoundException("Component not found with id: " + componentId));

        // Update basic fields - only update component name if it's not empty
        if (componentDTO.getLabel() != null && !componentDTO.getLabel().trim().isEmpty()) {
            component.setComponentName(componentDTO.getLabel());
        }

        // Handle component type change
        if (!component.getElementType().getValue().equals(componentDTO.getElementType())) {
            ComponentType newType = ComponentType.fromValue(componentDTO.getElementType().getValue());
            handleComponentTypeChange(component, newType);
        }

        // Update properties
        updateComponentProperties(component, componentDTO.getProperties());

        // Update options
        updateComponentOptions(component, componentDTO.getOptions());

        // Check options after update
        List<ElementOption> optionsAfterUpdate = optionRepository.findByComponentIdOrderByDisplayOrderAsc(component.getId());
        System.out.println("Options after update: " + optionsAfterUpdate.stream().map(opt -> opt.getId() + ":" + opt.getLabel()).collect(Collectors.toList()));

        FormComponent updatedComponent = formComponentRepository.save(component);
        
        // Check options after save
        List<ElementOption> optionsAfterSave = optionRepository.findByComponentIdOrderByDisplayOrderAsc(component.getId());
        System.out.println("Options after save: " + optionsAfterSave.stream().map(opt -> opt.getId() + ":" + opt.getLabel()).collect(Collectors.toList()));
        
        return formComponentMapper.toFormComponentDTO(updatedComponent);
    }

    @Transactional
    public FormComponentDTO updateComponentAssignment(Integer assignmentId, FormComponentDTO componentDTO) {
        FormComponentAssignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with id: " + assignmentId));

        // Update assignment-specific fields
        if (componentDTO.getLabel() != null && !componentDTO.getLabel().trim().isEmpty()) {
            assignment.setLabel(componentDTO.getLabel());
        }
        assignment.setRequired(componentDTO.getRequired() != null ? componentDTO.getRequired() : false);
        assignment.setPlaceholder(componentDTO.getPlaceholder());

        // Update the component properties and options
        FormComponent component = assignment.getComponent();
        updateComponentProperties(component, componentDTO.getProperties());
        updateComponentOptions(component, componentDTO.getOptions());

        FormComponentAssignment savedAssignment = assignmentRepository.save(assignment);
        return formComponentMapper.toFormComponentDTO(component, savedAssignment);
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
        clonedComponent.setComponentName(originalComponent.getComponentName() + " (Copy)");
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
        FormComponentAssignment assignment = new FormComponentAssignment(targetForm, savedClone, maxOrderIndex + 1, originalComponent.getComponentName() + " (Copy)", false, null);
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

    private boolean isOptionBasedComponent(ComponentType componentType) {
        return componentType == ComponentType.DROPDOWN ||
                componentType == ComponentType.RADIO ||
                componentType == ComponentType.CHECKBOX;
    }

    private void updateComponentProperties(FormComponent component, List<ComponentPropertyDTO> propertyDTOs) {
        if (propertyDTOs == null) return;

        // Clear existing properties
        propertyRepository.deleteByComponentId(component.getId());

        // Add new properties
        for (ComponentPropertyDTO propertyDTO : propertyDTOs) {
            ComponentProperty property = new ComponentProperty();
            property.setPropertyName(propertyDTO.getPropertyName());
            property.setPropertyValue(propertyDTO.getPropertyValue());
            property.setComponent(component);
            propertyRepository.save(property);
        }
    }

    private void updateComponentOptions(FormComponent component, List<ElementOptionDTO> optionDTOs) {
        System.out.println("=== UPDATE COMPONENT OPTIONS DEBUG ===");
        System.out.println("Component ID: " + component.getId());
        System.out.println("Options received: " + optionDTOs);
        System.out.println("Options count: " + (optionDTOs != null ? optionDTOs.size() : "null"));
        
        if (optionDTOs == null) {
            System.out.println("Options is null, returning early");
            return;
        }

        // Get existing options
        List<ElementOption> existingOptions = optionRepository.findByComponentIdOrderByDisplayOrderAsc(component.getId());
        System.out.println("Existing options count: " + existingOptions.size());
        System.out.println("Existing options IDs: " + existingOptions.stream().map(ElementOption::getId).collect(Collectors.toList()));

        // Track which existing options are still being used
        Set<Integer> usedOptionIds = new HashSet<>();
        
        // Process each option from the DTO
        for (ElementOptionDTO optionDTO : optionDTOs) {
            System.out.println("Processing option: " + optionDTO.getLabel() + " (ID: " + optionDTO.getId() + ")");
            
            if (optionDTO.getId() != null && optionDTO.getId() > 0) {
                // Update existing option
                ElementOption existingOption = existingOptions.stream()
                    .filter(opt -> opt.getId().equals(optionDTO.getId()))
                    .findFirst()
                    .orElse(null);
                
                if (existingOption != null) {
                    System.out.println("  - Updating existing option ID: " + existingOption.getId());
                    existingOption.setLabel(optionDTO.getLabel());
                    existingOption.setValue(optionDTO.getValue());
                    existingOption.setDisplayOrder(optionDTO.getDisplayOrder());
                    optionRepository.save(existingOption);
                    usedOptionIds.add(existingOption.getId());
                } else {
                    System.out.println("  - Option ID " + optionDTO.getId() + " not found in existing options, creating new option");
                    ElementOption newOption = new ElementOption();
                    newOption.setLabel(optionDTO.getLabel());
                    newOption.setValue(optionDTO.getValue());
                    newOption.setDisplayOrder(optionDTO.getDisplayOrder());
                    newOption.setComponent(component);
                    optionRepository.save(newOption);
                }
            } else {
                // Create new option
                System.out.println("  - Creating new option (no ID provided)");
                ElementOption newOption = new ElementOption();
                newOption.setLabel(optionDTO.getLabel());
                newOption.setValue(optionDTO.getValue());
                newOption.setDisplayOrder(optionDTO.getDisplayOrder());
                newOption.setComponent(component);
                optionRepository.save(newOption);
            }
        }

        // Delete options that are no longer used
        for (ElementOption existingOption : existingOptions) {
            if (!usedOptionIds.contains(existingOption.getId())) {
                System.out.println("  - Deleting unused option ID: " + existingOption.getId());
                optionRepository.delete(existingOption);
            }
        }
        
        System.out.println("=====================================");
    }
}

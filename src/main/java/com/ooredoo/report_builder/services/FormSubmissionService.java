package com.ooredoo.report_builder.services;


import com.ooredoo.report_builder.dto.*;
import com.ooredoo.report_builder.entity.*;
import com.ooredoo.report_builder.enums.ComponentType;
import com.ooredoo.report_builder.handler.FormBuilderException;
import com.ooredoo.report_builder.handler.ResourceNotFoundException;
import com.ooredoo.report_builder.handler.ValidationException;
import com.ooredoo.report_builder.mapper.FormSubmissionMapper;
import com.ooredoo.report_builder.repo.*;
import com.ooredoo.report_builder.user.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FormSubmissionService {

    private final FormSubmissionRepository submissionRepository;
    private final SubmissionValueRepository valueRepository;
    private final FormRepository formRepository;
    private final FormComponentAssignmentRepository assignmentRepository;
    private final UserRepository userRepository;
    private final FormSubmissionMapper submissionMapper;

    public FormSubmissionService(
            FormSubmissionRepository submissionRepository,
            SubmissionValueRepository valueRepository,
            FormRepository formRepository,
            FormComponentAssignmentRepository assignmentRepository,
            UserRepository userRepository,
            FormSubmissionMapper submissionMapper) {

        this.submissionRepository = submissionRepository;
        this.valueRepository = valueRepository;
        this.formRepository = formRepository;
        this.assignmentRepository = assignmentRepository;
        this.userRepository = userRepository;
        this.submissionMapper = submissionMapper;
    }

    // === ASSIGNMENT-BASED FORM SUBMISSION ===

    @Transactional
    public FormSubmissionResponseDTO submitForm(Integer formId, User submitter, Map<Integer, String> assignmentValues) {
// Step 1: Fetch form
        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found with id: " + formId));

        // Step 2: Fetch active assignments with components
        List<FormComponentAssignment> activeAssignments =
                assignmentRepository.findActiveAssignmentsWithComponents(formId);

        if (activeAssignments.isEmpty()) {
            throw new FormBuilderException("Form has no active components");
        }

        // Step 3: Authorization check
        if (!canUserSubmitForm(form, submitter)) {
            throw new FormBuilderException("User is not authorized to submit this form");
        }

        // Step 4: Validate required fields BEFORE saving
        validateRequiredAssignments(activeAssignments, assignmentValues);

        // Step 5: Create submission
        FormSubmission submission = new FormSubmission();
        submission.setForm(form);
        submission.setSubmittedBy(submitter);
        submission.setSubmittedDate(LocalDateTime.now());
        submission = submissionRepository.save(submission);

        // Step 6: Process submission values
        if (assignmentValues != null && !assignmentValues.isEmpty()) {
            for (Map.Entry<Integer, String> entry : assignmentValues.entrySet()) {
                Integer assignmentId = entry.getKey();
                String value = entry.getValue();

                // Find assignment
                FormComponentAssignment assignment = activeAssignments.stream()
                        .filter(a -> a.getId().equals(assignmentId))
                        .findFirst()
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Assignment not found: " + assignmentId));

                // Validate assignment belongs to this form
                if (!assignment.getForm().getId().equals(formId)) {
                    throw new ValidationException(
                            "Assignment " + assignmentId + " does not belong to form " + formId);
                }

                // Skip empty non-required fields
                FormComponent component = assignment.getComponent();
                if (value == null || value.trim().isEmpty()) {
                    if (assignment.getRequired()) {
                        throw new ValidationException(
                                "Required field '" + assignment.getLabel() + "' cannot be empty");
                    }
                    continue;
                }

                // Validate value format
                validateComponentValue(assignment, value);

                // Save submission value
                SubmissionValue submissionValue = new SubmissionValue();
                submissionValue.setSubmission(submission);
                submissionValue.setAssignment(assignment);
                submissionValue.setValue(value.trim());
                valueRepository.save(submissionValue);
            }
        }

        // Step 7: Fetch submission with all details for response
        return getSubmissionDetails(submission.getId());
    }

    // Separate method to fetch submission with all details
    @Transactional()
    public FormSubmissionResponseDTO getSubmissionDetails(Integer submissionId) {
        // Fetch submission
        FormSubmission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Submission not found: " + submissionId));

        // Fetch values with assignments and components
        List<SubmissionValue> values =
                valueRepository.findBySubmissionIdWithDetails(submissionId);

        if (values.isEmpty()) {
            // No values - return basic submission info
            return submissionMapper.toFormSubmissionResponseDTO(submission, new ArrayList<>());
        }

        // Extract component IDs
        List<Integer> componentIds = values.stream()
                .map(sv -> sv.getAssignment().getComponent().getId())
                .distinct()
                .collect(Collectors.toList());

        // Batch fetch properties
        valueRepository.fetchPropertiesForComponents(componentIds);

        // Batch fetch options
        valueRepository.fetchOptionsForComponents(componentIds);

        // Now map to DTO (all data is in session)
        return submissionMapper.toFormSubmissionResponseDTO(submission, values);
    }

    // === FORM STRUCTURE FOR SUBMISSIONS ===

    @Transactional()
    public FormWithAssignmentsDTO getFormForSubmission(Integer formId) {
        // Step 1: Fetch form
        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found with id: " + formId));

        // Step 2: Fetch assignments with components (NO properties/options yet)
        List<FormComponentAssignment> assignments =
                assignmentRepository.findActiveAssignmentsWithComponents(formId);

        if (assignments.isEmpty()) {
            FormWithAssignmentsDTO dto = new FormWithAssignmentsDTO();
            dto.setId(form.getId());
            dto.setName(form.getName_Form());
            dto.setDescription(form.getDescription());
            dto.setComponentAssignments(new ArrayList<>());
            return dto;
        }

        // Step 3: Extract component IDs
        List<Integer> componentIds = assignments.stream()
                .map(a -> a.getComponent().getId())
                .distinct()
                .collect(Collectors.toList());

        // Step 4: Batch fetch properties (one query)
        assignmentRepository.fetchPropertiesForComponents(componentIds);

        // Step 5: Batch fetch options (one query)
        assignmentRepository.fetchOptionsForComponents(componentIds);

        // Step 6: Now all data is in Hibernate session cache - no lazy loading
        FormWithAssignmentsDTO dto = new FormWithAssignmentsDTO();
        dto.setId(form.getId());
        dto.setName(form.getName_Form());
        dto.setDescription(form.getDescription());

        List<FormComponentAssignmentInfoDTO> assignmentInfos = assignments.stream()
                .map(this::toAssignmentInfoDTO)
                .collect(Collectors.toList());

        dto.setComponentAssignments(assignmentInfos);

        return dto;
    }

    private FormComponentAssignmentInfoDTO toAssignmentInfoDTO(FormComponentAssignment assignment) {
        FormComponent component = assignment.getComponent();

        FormComponentAssignmentInfoDTO dto = new FormComponentAssignmentInfoDTO();
        dto.setAssignmentId(assignment.getId());
        dto.setComponentId(component.getId());
        dto.setComponentType(component.getElementType().getValue());
        dto.setLabel(assignment.getLabel());
        dto.setRequired(assignment.getRequired());
        dto.setOrderIndex(assignment.getOrderIndex());

        if (component.getProperties() != null) {
            List<ComponentPropertyDTO> properties = component.getProperties().stream()
                    .map(this::toPropertyDTO)
                    .collect(Collectors.toList());
            dto.setProperties(properties);
        }

        if (component.getOptions() != null) {
            List<ElementOptionDTO> options = component.getOptions().stream()
                    .map(this::toOptionDTO)
                    .collect(Collectors.toList());
            dto.setOptions(options);
        }

        return dto;
    }

    // === SIMPLE CRUD OPERATIONS ===

    @Transactional()
    public List<FormSubmissionResponseDTO> getSubmissionsByForm(Integer formId) {
        List<FormSubmission> submissions = submissionRepository.findByFormIdOrderBySubmittedDateDesc(formId);
        return submissions.stream()
                .map(submissionMapper::toFormSubmissionResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional()
    public FormSubmissionResponseDTO getSubmissionById(Integer submissionId) {
        FormSubmission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new ResourceNotFoundException("Submission not found with id: " + submissionId));

        return submissionMapper.toFormSubmissionResponseDTO(submission);
    }

    @Transactional()
    public List<FormSubmissionResponseDTO> getSubmissionsByUser(User user) {
        List<FormSubmission> submissions = submissionRepository.findBySubmittedByOrderBySubmittedDateDesc(user);
        return submissions.stream()
                .map(submissionMapper::toFormSubmissionResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteSubmission(Integer submissionId) {
        if (!submissionRepository.existsById(submissionId)) {
            throw new ResourceNotFoundException("Submission not found with id: " + submissionId);
        }
        submissionRepository.deleteById(submissionId);
    }

    // === ASSIGNMENT-BASED CLEANUP ===

    @Transactional
    public void cleanupSubmissionValuesForRemovedAssignment(Integer assignmentId) {
        valueRepository.deleteByAssignmentId(assignmentId);
    }

    @Transactional
    public void cleanupSubmissionValuesForRemovedComponent(Integer formId, Integer componentId) {
        valueRepository.deleteByFormIdAndComponentId(formId, componentId);
    }

    // === VALIDATION HELPERS ===

    private void validateRequiredAssignments(List<FormComponentAssignment> assignments, Map<Integer, String> assignmentValues) {
        List<FormComponentAssignment> requiredAssignments = assignments.stream()
                .filter(assignment -> assignment.getRequired())
                .collect(Collectors.toList());

        for (FormComponentAssignment assignment : requiredAssignments) {
            String value = assignmentValues != null ? assignmentValues.get(assignment.getId()) : null;
            if (value == null || value.trim().isEmpty()) {
                throw new ValidationException("Required field '" + assignment.getLabel() + "' is missing");
            }
        }
    }

    private void validateComponentValue(FormComponentAssignment assignment, String value) {
        if (value == null || value.trim().isEmpty()) {
            return;
        }

        FormComponent component = assignment.getComponent();
        ComponentType componentType = component.getElementType();
        String fieldLabel = assignment.getLabel();

        switch (componentType) {
            case EMAIL:
                validateEmail(value, fieldLabel);
                break;
            case NUMBER:
                validateNumber(value, fieldLabel);
                break;
            case DATE:
                validateDate(value, fieldLabel);
                break;
            case DROPDOWN:
            case RADIO:
                validateOptionValue(assignment, value);
                break;
            case CHECKBOX:
                validateCheckboxValues(assignment, value);
                break;
        }
    }

    private void validateEmail(String email, String fieldLabel) {
        String emailPattern = "^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$";
        if (!email.matches(emailPattern)) {
            throw new ValidationException("Invalid email format for field: " + fieldLabel);
        }
    }

    private void validateNumber(String value, String fieldLabel) {
        try {
            Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new ValidationException("Invalid number format for field: " + fieldLabel);
        }
    }

    private void validateDate(String value, String fieldLabel) {
        try {
            LocalDate.parse(value);
        } catch (DateTimeParseException e) {
            throw new ValidationException("Invalid date format for field: " + fieldLabel + ". Expected format: yyyy-MM-dd");
        }
    }

    private void validateOptionValue(FormComponentAssignment assignment, String value) {
        FormComponent component = assignment.getComponent();
        boolean validOption = component.getOptions().stream()
                .anyMatch(option -> option.getValue().equals(value));

        if (!validOption) {
            throw new ValidationException("Invalid option selected for field: " + assignment.getLabel());
        }
    }

    private void validateCheckboxValues(FormComponentAssignment assignment, String value) {
        FormComponent component = assignment.getComponent();
        String[] selectedValues = value.split(",");
        Set<String> validValues = component.getOptions().stream()
                .map(ElementOption::getValue)
                .collect(Collectors.toSet());

        // Validate each selected value is valid
        for (String selectedValue : selectedValues) {
            if (!validValues.contains(selectedValue.trim())) {
                throw new ValidationException("Invalid checkbox option selected for field: " + assignment.getLabel());
            }
        }

        // Validate selection limits
        int selectedCount = selectedValues.length;
        int minSelections = getMinSelections(component);
        int maxSelections = getMaxSelections(component);

        if (selectedCount < minSelections) {
            throw new ValidationException("Minimum " + minSelections + " selections required for field: " + assignment.getLabel());
        }

        if (maxSelections > 0 && selectedCount > maxSelections) {
            throw new ValidationException("Maximum " + maxSelections + " selections allowed for field: " + assignment.getLabel());
        }
    }

    private int getMinSelections(FormComponent component) {
        return component.getProperties().stream()
                .filter(prop -> "minSelections".equals(prop.getPropertyName()))
                .map(prop -> {
                    try {
                        return Integer.parseInt(prop.getPropertyValue());
                    } catch (NumberFormatException e) {
                        return 0;
                    }
                })
                .findFirst()
                .orElse(0);
    }

    private int getMaxSelections(FormComponent component) {
        return component.getProperties().stream()
                .filter(prop -> "maxSelections".equals(prop.getPropertyName()))
                .map(prop -> {
                    try {
                        return Integer.parseInt(prop.getPropertyValue());
                    } catch (NumberFormatException e) {
                        return 0; // 0 means unlimited
                    }
                })
                .findFirst()
                .orElse(0); // 0 means unlimited
    }

    private boolean canUserSubmitForm(Form form, User user) {
        return form.getCreator().equals(user) || form.getAssignedUsers().contains(user);
    }

    // === HELPER METHODS ===

    private ComponentPropertyDTO toPropertyDTO(ComponentProperty property) {
        ComponentPropertyDTO dto = new ComponentPropertyDTO();
        dto.setId(property.getId());
        dto.setPropertyName(property.getPropertyName());
        dto.setPropertyValue(property.getPropertyValue());
        dto.setComponentId(property.getComponent().getId());
        return dto;
    }

    private ElementOptionDTO toOptionDTO(ElementOption option) {
        ElementOptionDTO dto = new ElementOptionDTO();
        dto.setId(option.getId());
        dto.setLabel(option.getLabel());
        dto.setValue(option.getValue());
        dto.setDisplayOrder(option.getDisplayOrder());
        dto.setComponentId(option.getComponent().getId());
        return dto;
    }

    @Transactional()
    public long getSubmissionCountByForm(Integer formId) {
        return submissionRepository.countByFormId(formId);
    }
}
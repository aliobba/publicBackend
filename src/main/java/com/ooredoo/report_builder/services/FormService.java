package com.ooredoo.report_builder.services;


import com.ooredoo.report_builder.dto.FormBuilderRequestDTO;
import com.ooredoo.report_builder.dto.FormRequestDTO;
import com.ooredoo.report_builder.dto.FormResponseDTO;
import com.ooredoo.report_builder.entity.Form;
import com.ooredoo.report_builder.entity.FormComponentAssignment;
import com.ooredoo.report_builder.handler.ResourceNotFoundException;
import com.ooredoo.report_builder.mapper.ComponentPropertyMapper;
import com.ooredoo.report_builder.mapper.ElementOptionMapper;
import com.ooredoo.report_builder.mapper.FormComponentMapper;
import com.ooredoo.report_builder.mapper.FormMapper;
import com.ooredoo.report_builder.repo.*;
import com.ooredoo.report_builder.user.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private final FormComponentAssignmentRepository assignmentRepository;

    public FormService(FormRepository formRepository,
                       FormComponentRepository componentRepository,
                       ComponentPropertyRepository propertyRepository,
                       ElementOptionRepository optionRepository,
                       UserRepository userRepository, FormMapper formMapper,
                       FormComponentMapper componentMapper,
                       ComponentPropertyMapper propertyMapper,
                       ElementOptionMapper optionMapper, FormComponentAssignmentRepository assignmentRepository) {
        this.formRepository = formRepository;
        this.componentRepository = componentRepository;
        this.propertyRepository = propertyRepository;
        this.optionRepository = optionRepository;
        this.userRepository = userRepository;
        this.formMapper = formMapper;
        this.componentMapper = componentMapper;
        this.propertyMapper = propertyMapper;
        this.optionMapper = optionMapper;
        this.assignmentRepository = assignmentRepository;
    }


    // === FORM CRUD OPERATIONS ===

    public FormResponseDTO createForm(FormRequestDTO request, User creator) {
        Form form = formMapper.toEntity(request);
        form.setCreator(creator);
        form.setCreation_Date(LocalDateTime.now());
        form.setUpdatedAt_Date(LocalDateTime.now());

        Form savedForm = formRepository.save(form);
        return formMapper.toFormResponseDTO(savedForm);
    }

    public FormResponseDTO updateForm(Integer formId, FormRequestDTO request) {
        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found with id: " + formId));

        form.setName_Form(request.getName());
        form.setDescription(request.getDescription());
        form.setUpdatedAt_Date(LocalDateTime.now());

        Form updatedForm = formRepository.save(form);
        return formMapper.toFormResponseDTO(updatedForm);
    }

    @Transactional
    public void deleteForm(Integer formId) {
        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found with id: " + formId));

        form.getComponentAssignments().removeAll(form.getComponentAssignments());

        formRepository.deleteById(formId);
    }

    @Transactional()
    public List<FormResponseDTO> getAllForms() {
        return formRepository.findAll().stream()
                .map(formMapper::toFormResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional()
    public List<FormResponseDTO> getFormsCreatedBy(User user) {
        return formRepository.findByCreator(user).stream()
                .map(formMapper::toFormResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional()
    public FormResponseDTO getFormById(Integer formId) {
        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found with id: " + formId));
        return formMapper.toFormResponseDTO(form);
    }

    // === FORM ASSIGNMENT OPERATIONS ===

    @Transactional
    public void assignFormToUsers(Integer formId, List<Integer> userIds) {
        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found with id: " + formId));

        List<User> users = userRepository.findAllById(userIds);
        if (users.size() != userIds.size()) {
            throw new ResourceNotFoundException("Some users not found");
        }

        form.getAssignedUsers().addAll(users);
        formRepository.save(form);
    }

    @Transactional
    public void unassignFormFromUser(Integer formId, Integer userId) {
        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found with id: " + formId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        form.getAssignedUsers().remove(user);
        formRepository.save(form);
    }

    @Transactional()
    public List<Integer> getFormAssignedUsers(Integer formId) {
        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found with id: " + formId));

        return form.getAssignedUsers().stream()
                .map(User::getId)
                .collect(Collectors.toList());
    }
    @Transactional()
    public List<Integer> getUsersWithoutForm(Integer formId) {
        
        List<User> users = userRepository.findAll();
        List<Integer> usersWithForm = getFormAssignedUsers(formId);
        return users.stream()
                    .filter(user -> !usersWithForm.contains(user.getId()))
                     .map(User::getId)
                    .collect(Collectors.toList());
    }

    @Transactional()
    public FormResponseDTO getFormWithAllDetails(Integer formId) {

        // Fetch form basic info
        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found with id: " + formId));

        // Fetch active assignments with components (1 query)
        List<FormComponentAssignment> assignments =
                assignmentRepository.findActiveAssignmentsWithComponents(formId);

        if (assignments.isEmpty()) {
            form.setComponentAssignments(new ArrayList<>());
            return formMapper.toFormResponseDTO(form);
        }

        // Extract component IDs
        List<Integer> componentIds = assignments.stream()
                .map(a -> a.getComponent().getId())
                .distinct()
                .collect(Collectors.toList());

        // Batch fetch properties (1 query)
        assignmentRepository.fetchPropertiesForComponents(componentIds);

        // Batch fetch options (1 query)
        assignmentRepository.fetchOptionsForComponents(componentIds);

        // All data is now in Hibernate session - no lazy loading exceptions
        form.setComponentAssignments(assignments);

        return formMapper.toFormResponseDTO(form);

    }

    @Transactional()
    public boolean isUserAuthorizedToViewForm(Integer formId, User user) {
        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found with id: " + formId));

        return form.getCreator().equals(user) || form.getAssignedUsers().contains(user);
    }

    @Transactional
    public FormResponseDTO createFormWithComponents(FormBuilderRequestDTO request, User creator) {
        // Create form
        Form form = new Form();
        form.setName_Form(request.getName());
        form.setDescription(request.getDescription());
        form.setCreator(creator);
        form.setCreation_Date(LocalDateTime.now());
        form.setUpdatedAt_Date(LocalDateTime.now());

        Form savedForm = formRepository.save(form);

        // Add components would be handled by FormComponentService

        // Assign users
        if (request.getAssignedUserIds() != null && !request.getAssignedUserIds().isEmpty()) {
            List<User> users = userRepository.findAllById(request.getAssignedUserIds());
            savedForm.getAssignedUsers().addAll(users);
            savedForm = formRepository.save(savedForm);
        }

        return formMapper.toFormResponseDTO(savedForm);
    }

    @Transactional()
    public List<FormResponseDTO> getFormsAssignedToUser(Integer userId) {
        List<Form> forms = formRepository.findFormsByAssignedUserId(userId);
        return forms.stream()
                .map(formMapper::toFormResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional()
    public boolean isUserFormCreator(Integer formId, User user) {
        Form form = formRepository.findById(formId)
                .orElseThrow(() -> new ResourceNotFoundException("Form not found with id: " + formId));

        return form.getCreator().getId().equals(user.getId());
    }
}
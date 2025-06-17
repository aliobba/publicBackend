package com.ooredoo.report_builder.controller;

import com.ooredoo.report_builder.dto.*;
import com.ooredoo.report_builder.services.FormService;
import com.ooredoo.report_builder.services.UserService;
import com.ooredoo.report_builder.user.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forms")
public class FormController {

    private final FormService formService;
    private final UserService userService;

    public FormController(FormService formService, UserService userService) {
        this.formService = formService;
        this.userService = userService;
    }

    // --- Form endpoints ---
    @PostMapping
    @PreAuthorize("hasAuthority('DEPARTMENT_ADMIN')")
    public ResponseEntity<FormResponseDTO> createForm(@Valid @RequestBody FormRequestDTO request) {
        User currentUser = userService.getCurrentAuthenticatedUser();
        return ResponseEntity.status(HttpStatus.CREATED).body(formService.createForm(request, currentUser));
    }

    @PutMapping("/{formId}")
    @PreAuthorize("hasAuthority('DEPARTMENT_ADMIN')")
    public ResponseEntity<FormResponseDTO> updateForm(@PathVariable Integer formId, @RequestBody FormRequestDTO request) {
        return ResponseEntity.ok(formService.updateForm(formId, request));
    }

    @DeleteMapping("/{formId}")
    @PreAuthorize("hasAuthority('DEPARTMENT_ADMIN')")
    public ResponseEntity<MessageResponse> deleteForm(@PathVariable Integer formId) {
        formService.deleteForm(formId);
        return ResponseEntity.ok(new MessageResponse("Form deleted successfully"));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public List<FormResponseDTO> getAllForms() {
        User currentUser = userService.getCurrentAuthenticatedUser();
        if (currentUser.hasRole("MAIN_ADMIN")) {
            return formService.getAllForms();
        } else {
            return formService.getFormsCreatedBy(currentUser);
        }
    }

    @GetMapping("/{formId}")
    @PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<FormResponseDTO> getFormById(@PathVariable Integer formId) {
        return ResponseEntity.ok(formService.getFormById(formId));
    }

    @PostMapping("/{formId}/assign")
    @PreAuthorize("hasAuthority('DEPARTMENT_ADMIN')")
    public ResponseEntity<MessageResponse> assignForm(
            @PathVariable Integer formId,
            @RequestBody List<Integer> userIds) {
        System.out.println("formId "+ formId+"userIds "+userIds );
        formService.assignFormToUsers(formId, userIds);
        return ResponseEntity.ok(new MessageResponse("Form assigned successfully"));
    }

    @DeleteMapping("/{formId}/assign/{userId}")
    @PreAuthorize("hasAuthority('DEPARTMENT_ADMIN')")
    public ResponseEntity<MessageResponse> unassignForm(
            @PathVariable Integer formId,
            @PathVariable Integer userId) {
        formService.unassignFormFromUser(formId, userId);
        return ResponseEntity.ok(new MessageResponse("Form unassigned successfully"));
    }

    // --- Components ---
    @PostMapping("/{formId}/components")
    @PreAuthorize("hasAuthority('DEPARTMENT_ADMIN')")
    public ResponseEntity<FormComponentDTO> addComponent(
            @PathVariable Integer formId,
            @RequestBody FormComponentDTO componentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(formService.addComponent(formId, componentDTO));
    }

    @PutMapping("/components/{componentId}")
    @PreAuthorize("hasAuthority('DEPARTMENT_ADMIN')")
    public ResponseEntity<FormComponentDTO> updateComponent(
            @PathVariable Integer componentId,
            @RequestBody FormComponentDTO componentDTO) {
        return ResponseEntity.ok(formService.updateComponent(componentId, componentDTO));
    }

    @DeleteMapping("/components/{componentId}")
    @PreAuthorize("hasAuthority('DEPARTMENT_ADMIN')")
    public ResponseEntity<MessageResponse> deleteComponent(@PathVariable Integer componentId) {
        formService.deleteComponent(componentId);
        return ResponseEntity.ok(new MessageResponse("Component deleted successfully"));
    }

    // --- Properties ---
    @PostMapping("/components/{componentId}/properties")
    @PreAuthorize("hasAuthority('DEPARTMENT_ADMIN')")
    public ResponseEntity<ComponentPropertyDTO> addComponentProperty(
            @PathVariable Integer componentId,
            @RequestBody ComponentPropertyDTO propertyDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(formService.addComponentProperty(componentId, propertyDTO));
    }

    @PutMapping("/components/properties/{propertyId}")
    @PreAuthorize("hasAuthority('DEPARTMENT_ADMIN')")
    public ResponseEntity<ComponentPropertyDTO> updateComponentProperty(
            @PathVariable Integer propertyId,
            @RequestParam String value) {
        return ResponseEntity.ok(formService.updateComponentProperty(propertyId, value));
    }

    @DeleteMapping("/components/properties/{propertyId}")
    @PreAuthorize("hasAuthority('DEPARTMENT_ADMIN')")
    public ResponseEntity<MessageResponse> deleteComponentProperty(@PathVariable Integer propertyId) {
        formService.deleteComponentProperty(propertyId);
        return ResponseEntity.ok(new MessageResponse("Component property deleted successfully"));
    }

    // --- Options ---
    @PostMapping("/components/{componentId}/options")
    @PreAuthorize("hasAuthority('DEPARTMENT_ADMIN')")
    public ResponseEntity<ElementOptionDTO> addElementOption(
            @PathVariable Integer componentId,
            @RequestBody ElementOptionDTO optionDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(formService.addElementOption(componentId, optionDTO));
    }

    @PutMapping("/components/options/{optionId}")
    @PreAuthorize("hasAuthority('DEPARTMENT_ADMIN')")
    public ResponseEntity<ElementOptionDTO> updateElementOption(
            @PathVariable Integer optionId,
            @RequestParam String label,
            @RequestParam String value) {
        return ResponseEntity.ok(formService.updateElementOption(optionId, label, value));
    }

    @DeleteMapping("/components/options/{optionId}")
    @PreAuthorize("hasAuthority('DEPARTMENT_ADMIN')")
    public ResponseEntity<MessageResponse> deleteElementOption(@PathVariable Integer optionId) {
        formService.deleteElementOption(optionId);
        return ResponseEntity.ok(new MessageResponse("Element option deleted successfully"));
    }
}


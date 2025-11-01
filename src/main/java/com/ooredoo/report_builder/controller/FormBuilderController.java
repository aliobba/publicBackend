package com.ooredoo.report_builder.controller;

import com.ooredoo.report_builder.dto.*;
import com.ooredoo.report_builder.services.FormComponentService;
import com.ooredoo.report_builder.services.FormService;
import com.ooredoo.report_builder.services.UserService;
import com.ooredoo.report_builder.user.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/form-builder")
@Validated
public class FormBuilderController {

    private final FormService formService;
    private final FormComponentService componentService;
    private final UserService userService;

    public FormBuilderController(
            FormService formService,
            FormComponentService componentService,
            UserService userService) {

        this.formService = formService;
        this.componentService = componentService;
        this.userService = userService;
    }

    // === COMPLETE FORM BUILDER ===

    @PostMapping("/forms/complete")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<FormResponseDTO> createCompleteForm(
            @Valid @RequestBody FormBuilderRequestDTO request) {

        User currentUser = userService.getCurrentAuthenticatedUser();
        FormResponseDTO result = formService.createFormWithComponents(request, currentUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // === FORM BUILDER UI SUPPORT ===

    @GetMapping("/component-types")
    public ResponseEntity<List<ComponentTemplateDTO>> getAvailableComponentTypes() {
        return ResponseEntity.ok(componentService.getAvailableComponentTemplates());
    }

    @GetMapping("/forms/{formId}/builder-data")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<FormBuilderDataDTO> getFormBuilderData(@PathVariable Integer formId) {
        FormResponseDTO form = formService.getFormWithAllDetails(formId);
        List<ComponentTemplateDTO> componentTypes = componentService.getAvailableComponentTemplates();

        User currentUser = userService.getCurrentAuthenticatedUser();
        List<FormComponentDTO> reusableComponents = componentService.getReusableComponents(currentUser);

        FormBuilderDataDTO builderData = new FormBuilderDataDTO();
        builderData.setForm(form);
        builderData.setAvailableComponentTypes(componentTypes);
        builderData.setReusableComponents(reusableComponents);

        return ResponseEntity.ok(builderData);
    }
}
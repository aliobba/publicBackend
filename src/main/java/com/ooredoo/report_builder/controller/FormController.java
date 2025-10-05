package com.ooredoo.report_builder.controller;

import com.ooredoo.report_builder.dto.FormComponentDTO;
import com.ooredoo.report_builder.dto.FormRequestDTO;
import com.ooredoo.report_builder.dto.FormResponseDTO;
import com.ooredoo.report_builder.services.FormComponentService;
import com.ooredoo.report_builder.services.FormService;
import com.ooredoo.report_builder.services.UserService;
import com.ooredoo.report_builder.user.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forms")
public class FormController {

    private final FormService formService;
    private final FormComponentService componentService;
    private final UserService userService;

    public FormController(FormService formService, FormComponentService componentService, UserService userService) {
        this.formService = formService;
        this.componentService = componentService;
        this.userService = userService;
    }

    // === FORM CRUD ===

    @PostMapping("/createForm")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<FormResponseDTO> createForm(@Valid @RequestBody FormRequestDTO request) {
        User currentUser = userService.getCurrentAuthenticatedUser();
        return ResponseEntity.status(HttpStatus.CREATED).body(formService.createForm(request, currentUser));
    }

    @GetMapping
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<List<FormResponseDTO>> getAllForms() {
        User currentUser = userService.getCurrentAuthenticatedUser();
        List<FormResponseDTO> forms;

        if (currentUser.hasRole("MAIN_ADMIN")) {
            forms = formService.getAllForms();
        } else {
            forms = formService.getFormsCreatedBy(currentUser);
        }

        return ResponseEntity.ok(forms);
    }

    @GetMapping("/FormById/{formId}")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<FormResponseDTO> getFormById(@PathVariable Integer formId) {
        return ResponseEntity.ok(formService.getFormWithAllDetails(formId));
    }

    @PutMapping("/updateForm/{formId}")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<FormResponseDTO> updateForm(@PathVariable Integer formId,
                                                      @Valid @RequestBody FormRequestDTO request) {
        return ResponseEntity.ok(formService.updateForm(formId, request));
    }

    @DeleteMapping("/deleteForm/{formId}")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<MessageResponse> deleteForm(@PathVariable Integer formId) {
        formService.deleteForm(formId);
        return ResponseEntity.ok(new MessageResponse("Form deleted successfully"));
    }

    // === COMPONENT ASSIGNMENT TO FORMS ===

    @PostMapping("/addComponentToForm/{formId}/components")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<FormComponentDTO> addComponentToForm(
            @PathVariable Integer formId,
            @Valid @RequestBody FormComponentDTO componentDTO) {

        User currentUser = userService.getCurrentAuthenticatedUser();
        FormComponentDTO result = componentService.createComponentWithDefaults(componentDTO, formId, currentUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/{formId}/components/{componentId}/assign")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<MessageResponse> assignExistingComponentToForm(
            @PathVariable Integer formId,
            @PathVariable Integer componentId,
            @RequestParam(required = false) Integer orderIndex) {

        componentService.assignComponentToForm(componentId, formId, orderIndex);
        return ResponseEntity.ok(new MessageResponse("Component assigned to form successfully"));
    }

    @DeleteMapping("/{formId}/components/{componentId}/unassign")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<MessageResponse> unassignComponentFromForm(
            @PathVariable Integer formId,
            @PathVariable Integer componentId) {

        componentService.unassignComponentFromForm(componentId, formId);
        return ResponseEntity.ok(new MessageResponse("Component unassigned from form successfully"));
    }

    @GetMapping("/{formId}/components")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<List<FormComponentDTO>> getFormComponents(@PathVariable Integer formId) {
        return ResponseEntity.ok(componentService.getFormComponents(formId));
    }

    @PostMapping("/{formId}/components/reorder")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<MessageResponse> reorderFormComponents(
            @PathVariable Integer formId,
            @RequestBody List<Integer> componentIds) {

        componentService.reorderFormComponents(formId, componentIds);
        return ResponseEntity.ok(new MessageResponse("Components reordered successfully"));
    }

    // === FORM ASSIGNMENT ===

    @PostMapping("/{formId}/assign/users")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<MessageResponse> assignFormToUsers(
            @PathVariable Integer formId,
            @RequestBody List<Integer> userIds) {

        formService.assignFormToUsers(formId, userIds);
        return ResponseEntity.ok(new MessageResponse("Form assigned to users successfully"));
    }

    @DeleteMapping("/{formId}/assign/users/{userId}")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<MessageResponse> unassignFormFromUser(
            @PathVariable Integer formId,
            @PathVariable Integer userId) {

        formService.unassignFormFromUser(formId, userId);
        return ResponseEntity.ok(new MessageResponse("Form unassigned from user successfully"));
    }

    @GetMapping("/{formId}/assigned-users")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<List<Integer>> getFormAssignedUsers(@PathVariable Integer formId) {
        return ResponseEntity.ok(formService.getFormAssignedUsers(formId));
    }
}


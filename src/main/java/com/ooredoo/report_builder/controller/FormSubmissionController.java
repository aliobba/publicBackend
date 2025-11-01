package com.ooredoo.report_builder.controller;

import com.ooredoo.report_builder.dto.FormSubmissionRequestDTO;
import com.ooredoo.report_builder.dto.FormSubmissionResponseDTO;
import com.ooredoo.report_builder.dto.FormWithAssignmentsDTO;
import com.ooredoo.report_builder.services.FormService;
import com.ooredoo.report_builder.services.FormSubmissionService;
import com.ooredoo.report_builder.services.UserService;
import com.ooredoo.report_builder.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submissions")
@Validated
public class FormSubmissionController {

    private final FormService formService;
    private final FormSubmissionService submissionService;
    private final UserService userService;

    public FormSubmissionController(FormService formService, FormSubmissionService submissionService, UserService userService) {
        this.formService = formService;
        this.submissionService = submissionService;
        this.userService = userService;
    }

    // === GET FORM STRUCTURE FOR SUBMISSION ===

    @GetMapping("/forms/{formId}/structure")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN', 'USER')")
    public ResponseEntity<FormWithAssignmentsDTO> getFormForSubmission(@PathVariable Integer formId) {
        FormWithAssignmentsDTO formStructure = submissionService.getFormForSubmission(formId);
        return ResponseEntity.ok(formStructure);
    }

    // === ASSIGNMENT-BASED FORM SUBMISSION ===

    @PostMapping("/forms/{formId}/submit")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN', 'USER')")
    public ResponseEntity<FormSubmissionResponseDTO> submitForm(
            @PathVariable Integer formId,
            @RequestBody FormSubmissionRequestDTO request) {

        User currentUser = userService.getCurrentAuthenticatedUser();
        formService.getFormAssignedUsers(formId);
        if(formService.getFormAssignedUsers(formId).contains(currentUser.getId())) {
            FormSubmissionResponseDTO result = submissionService.submitForm(
                    formId,
                    currentUser,
                    request.getAssignmentValues()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }else
            throw new IllegalCallerException("This user can't fill this form Check assigned Users");
    }

    // === GET SUBMISSIONS ===

    @GetMapping("/forms/{formId}")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<List<FormSubmissionResponseDTO>> getFormSubmissions(@PathVariable Integer formId) {
        List<FormSubmissionResponseDTO> submissions = submissionService.getSubmissionsByForm(formId);
        return ResponseEntity.ok(submissions);
    }

    @GetMapping("/{submissionId}")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN', 'USER')")
    public ResponseEntity<FormSubmissionResponseDTO> getSubmissionById(@PathVariable Integer submissionId) {
        FormSubmissionResponseDTO submission = submissionService.getSubmissionById(submissionId);
        return ResponseEntity.ok(submission);
    }

    @GetMapping("/my-submissions")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN', 'USER')")
    public ResponseEntity<List<FormSubmissionResponseDTO>> getMySubmissions() {
        User currentUser = userService.getCurrentAuthenticatedUser();
        List<FormSubmissionResponseDTO> submissions = submissionService.getSubmissionsByUser(currentUser);
        return ResponseEntity.ok(submissions);
    }

    @DeleteMapping("/{submissionId}")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<MessageResponse> deleteSubmission(@PathVariable Integer submissionId) {
        submissionService.deleteSubmission(submissionId);
        return ResponseEntity.ok(new MessageResponse("Submission deleted successfully"));
    }

    @GetMapping("/forms/{formId}/count")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<Long> getSubmissionCount(@PathVariable Integer formId) {
        long count = submissionService.getSubmissionCountByForm(formId);
        return ResponseEntity.ok(count);
    }
}
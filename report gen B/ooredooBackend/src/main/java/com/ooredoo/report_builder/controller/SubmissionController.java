package com.ooredoo.report_builder.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ooredoo.report_builder.dto.FormSubmissionDTO;
import com.ooredoo.report_builder.dto.SubmissionValueDTO;
import com.ooredoo.report_builder.services.FormSubmissionService;
import com.ooredoo.report_builder.services.UserService;
import com.ooredoo.report_builder.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/submissions")
public class SubmissionController {

    private final FormSubmissionService submissionService;
    private final UserService userService;

    public SubmissionController(FormSubmissionService submissionService, UserService userService) {
        this.submissionService = submissionService;
        this.userService = userService;
    }

    // 📝 Submit a form (with text values and files)
    @PostMapping(value = "/{formId}", consumes = {"multipart/form-data"})
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<FormSubmissionDTO> submitForm(
            @PathVariable Integer formId,
            @RequestPart("textValues") String textValuesJson,
            @RequestPart(value = "fileValues", required = false) Map<String, List<MultipartFile>> fileValues
    ) throws IOException {

        User currentUser = userService.getCurrentAuthenticatedUser();

        Map<Integer, String> textValues = parseTextValues(textValuesJson);
        Map<Integer, List<MultipartFile>> parsedFileValues = parseFileValues(fileValues);

        FormSubmissionDTO submissionDTO = submissionService.submitForm(formId, currentUser, textValues, parsedFileValues);

        return ResponseEntity.status(HttpStatus.CREATED).body(submissionDTO);
    }

    //  Get all submissions
    @GetMapping
    @PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public List<FormSubmissionDTO> getAllSubmissions() {
        return submissionService.getAllSubmissions();
    }

    // Get submissions by form
    @GetMapping("/form/{formId}")
    @PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public List<FormSubmissionDTO> getSubmissionsByForm(@PathVariable Integer formId) {
        return submissionService.getSubmissionsByForm(formId);
    }

    // Get submissions by user
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN', 'USER')")
    public List<FormSubmissionDTO> getSubmissionsByUser(@PathVariable Integer userId) {
        return submissionService.getSubmissionsByUser(userId);
    }

    // Get one submission by ID
    @GetMapping("/{submissionId}")
    @PreAuthorize("hasAnyRole('MAIN_ADMIN', 'DEPARTMENT_ADMIN', 'USER')")
    public ResponseEntity<FormSubmissionDTO> getSubmission(@PathVariable Integer submissionId) {
        return ResponseEntity.ok(submissionService.getSubmission(submissionId));
    }

    // Get submission values (answers)
    @GetMapping("/{submissionId}/values")
    @PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN', 'USER')")
    public List<SubmissionValueDTO> getSubmissionValues(@PathVariable Integer submissionId) {
        return submissionService.getSubmissionValues(submissionId);
    }

    // Delete a submission
    @DeleteMapping("/{submissionId}")
    @PreAuthorize("hasAuthority('MAIN_ADMIN')")
    public ResponseEntity<MessageResponse> deleteSubmission(@PathVariable Integer submissionId) {
        submissionService.deleteSubmission(submissionId);
        return ResponseEntity.ok(new MessageResponse("Submission deleted successfully"));
    }


    private Map<Integer, String> parseTextValues(String textValuesJson) throws IOException {
        if (textValuesJson == null || textValuesJson.isEmpty()) return Collections.emptyMap();

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> rawMap = mapper.readValue(textValuesJson, new TypeReference<>() {
        });

        Map<Integer, String> parsed = new HashMap<>();
        for (Map.Entry<String, String> entry : rawMap.entrySet()) {
            parsed.put(Integer.parseInt(entry.getKey()), entry.getValue());
        }
        return parsed;
    }

    private Map<Integer, List<MultipartFile>> parseFileValues(Map<String, List<MultipartFile>> fileValues) {
        if (fileValues == null) return Collections.emptyMap();

        Map<Integer, List<MultipartFile>> parsed = new HashMap<>();
        for (Map.Entry<String, List<MultipartFile>> entry : fileValues.entrySet()) {
            parsed.put(Integer.parseInt(entry.getKey()), entry.getValue());
        }
        return parsed;
    }
}

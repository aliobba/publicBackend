package com.ooredoo.report_builder.controller.department;

import com.ooredoo.report_builder.controller.MessageResponse;
import com.ooredoo.report_builder.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('MAIN_ADMIN')")
    public ResponseEntity<DepartmentResponse> createDepartment(@Valid @RequestBody DepartmentCreationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.createDepartment(request));
    }

    @PutMapping("/{departmentId}")
    @PreAuthorize("hasAuthority('MAIN_ADMIN')")
    public ResponseEntity<DepartmentResponse> updateDepartment(
            @PathVariable Integer departmentId,
            @Valid @RequestBody DepartmentUpdateRequest request) {
        return ResponseEntity.ok(departmentService.updateDepartment(departmentId, request));
    }

    @DeleteMapping("/{departmentId}")
    @PreAuthorize("hasAuthority('MAIN_ADMIN')")
    public ResponseEntity<MessageResponse> deleteDepartment(@PathVariable Integer departmentId) {
        departmentService.deleteDepartment(departmentId);
        return ResponseEntity.ok(new MessageResponse("Department deleted successfully"));
    }

    @PostMapping("/{departmentId}/users")
    @PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<MessageResponse> addUserToDepartment(
            @PathVariable Integer departmentId,
            @RequestBody UserAssignmentRequest request) {
        departmentService.addUserToDepartment(request.getUserId(), departmentId);
        return ResponseEntity.ok(new MessageResponse("User added to department successfully"));
    }

    @PutMapping("/{departmentId}/admin")
    @PreAuthorize("hasAuthority('MAIN_ADMIN')")
    public ResponseEntity<DepartmentResponse> changeDepartmentAdmin(
            @PathVariable Integer departmentId,
            @RequestBody AdminChangeRequest request) {
        return ResponseEntity.ok(departmentService.changeDepartmentAdmin(departmentId, request));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('MAIN_ADMIN')")
    public List<DepartmentResponse> getAllDepartments() {
        return departmentService.getAllDepartments();
    }
}

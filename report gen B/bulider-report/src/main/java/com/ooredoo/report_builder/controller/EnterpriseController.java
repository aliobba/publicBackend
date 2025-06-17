package com.ooredoo.report_builder.controller;

import com.ooredoo.report_builder.dto.request.CreateEnterpriseRequest;
import com.ooredoo.report_builder.dto.request.UpdateEnterpriseRequest;
import com.ooredoo.report_builder.dto.response.EnterpriseResponse;
import com.ooredoo.report_builder.services.EnterpriseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enterprises")
public class EnterpriseController {

    private final EnterpriseService enterpriseService;

    public EnterpriseController(EnterpriseService enterpriseService) {
        this.enterpriseService = enterpriseService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('MAIN_ADMIN')")
    public ResponseEntity<EnterpriseResponse> createEnterprise(@Valid @RequestBody CreateEnterpriseRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enterpriseService.createEnterprise(request));
    }

    @PutMapping("/{enterpriseId}")
    @PreAuthorize("hasAuthority('MAIN_ADMIN')")
    public ResponseEntity<EnterpriseResponse> updateEnterprise(
            @PathVariable Integer enterpriseId,
            @Valid @RequestBody UpdateEnterpriseRequest request) {
        return ResponseEntity.ok(enterpriseService.updateEnterprise(enterpriseId, request));
    }

    @DeleteMapping("/{enterpriseId}")
    @PreAuthorize("hasAuthority('MAIN_ADMIN')")
    public ResponseEntity<MessageResponse> deleteEnterprise(@PathVariable Integer enterpriseId) {
        enterpriseService.deleteEnterprise(enterpriseId);
        return ResponseEntity.ok(new MessageResponse("Enterprise deleted successfully"));
    }

    @PostMapping("/{enterpriseId}/users/{userId}")
    @PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'ENTREPRISE_ADMIN')")
    public ResponseEntity<MessageResponse> addUserToEnterprise(
            @PathVariable Integer enterpriseId,
            @PathVariable Integer userId) {
        enterpriseService.addUserToEnterprise(userId, enterpriseId);
        return ResponseEntity.ok(new MessageResponse("User added to enterprise successfully"));
    }

    @PutMapping("/{enterpriseId}/admin/{newAdminId}")
    @PreAuthorize("hasAuthority('MAIN_ADMIN')")
    public ResponseEntity<EnterpriseResponse> changeEnterpriseAdmin(
            @PathVariable Integer enterpriseId,
            @PathVariable Integer newAdminId) {
        return ResponseEntity.ok(enterpriseService.changeEnterpriseAdmin(enterpriseId, newAdminId));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('MAIN_ADMIN')")
    public List<EnterpriseResponse> getAllEnterprises() {
        return enterpriseService.getAllEnterprises();
    }

    @GetMapping("/{enterpriseId}")
    @PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'ENTREPRISE_ADMIN')")
    public ResponseEntity<EnterpriseResponse> getEnterpriseById(@PathVariable Integer enterpriseId) {
        return ResponseEntity.ok(enterpriseService.getEnterpriseById(enterpriseId));
    }

    @GetMapping("/admin/{adminId}")
    @PreAuthorize("hasAuthority('MAIN_ADMIN')")
    public List<EnterpriseResponse> getEnterprisesByAdminId(@PathVariable Integer adminId) {
        return enterpriseService.getEnterprisesByAdminId(adminId);
    }
}
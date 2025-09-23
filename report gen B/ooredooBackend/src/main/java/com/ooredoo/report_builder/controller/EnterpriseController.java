package com.ooredoo.report_builder.controller;

import com.ooredoo.report_builder.entity.Enterprise;
import com.ooredoo.report_builder.services.EnterpriseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enterprises")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    @GetMapping
    public ResponseEntity<List<Enterprise>> getAllEnterprises() {
        List<Enterprise> enterprises = enterpriseService.findAll();
        enterprises.forEach(enterprise -> {
            if (enterprise.getManager() != null) {
                enterprise.getManager().getId();
                enterprise.getManager().getName();
                enterprise.getManager().getEmail();
            }
        });
        return ResponseEntity.ok(enterprises);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enterprise> getEnterpriseById(@PathVariable Integer id) {
        return ResponseEntity.ok(enterpriseService.findById(id));

    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Enterprise> getEnterpriseByName(@PathVariable String name) {
        Optional<Enterprise> enterprise = enterpriseService.findByName(name);
        return enterprise.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/createEnterprise")
    public ResponseEntity<Enterprise> createEnterprise(@RequestBody Enterprise enterprise) {
        try {
            if (enterpriseService.existsByName(enterprise.getName())) {
                return ResponseEntity.badRequest().build();
            }
            Enterprise savedEnterprise = enterpriseService.createEnterprise(enterprise);
            return ResponseEntity.ok(savedEnterprise);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enterprise> updateEnterprise(@PathVariable Integer id, @Valid @RequestBody Enterprise enterprise) {
        try {
            if (enterpriseService.findById(id)!= null) {
                return ResponseEntity.notFound().build();
            }
            enterprise.setId(id);
            Enterprise updatedEnterprise = enterpriseService.createEnterprise(enterprise);
            return ResponseEntity.ok(updatedEnterprise);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnterprise(@PathVariable Integer id) {
        try {
            if (enterpriseService.findById(id)!= null) {
                return ResponseEntity.notFound().build();
            }
            enterpriseService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{enterpriseId}/users/{userId}")
    public ResponseEntity<MessageResponse> addUserToEnterprise(
            @PathVariable Integer enterpriseId,
            @PathVariable Integer userId) {
        enterpriseService.assignUserToEnterprise(enterpriseId, userId);
        return ResponseEntity.ok(new MessageResponse("User added to enterprise successfully"));
    }
    @PutMapping("/removeFromEntreprise/{enterpriseId}/users/{userId}")
    public ResponseEntity<MessageResponse> removeUserFromEnterprise(
            @PathVariable Integer enterpriseId,
            @PathVariable Integer userId) {
        enterpriseService.unassignUserFromEnterprise(enterpriseId, userId);
        return ResponseEntity.ok(new MessageResponse("User added to enterprise successfully"));
    }

/*
    private final EnterpriseService enterpriseService;

    public EnterpriseController(EnterpriseService enterpriseService) {
        this.enterpriseService = enterpriseService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('MAIN_ADMIN')")
    public ResponseEntity<EnterpriseResponse> createEnterprise(@Valid @RequestBody EnterpriseRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enterpriseService.createEnterprise(request));
    }

    @PutMapping("/{enterpriseId}")
    @PreAuthorize("hasAuthority('MAIN_ADMIN')")
    public ResponseEntity<EnterpriseResponse> updateEnterprise(
            @PathVariable Integer enterpriseId,
            @Valid @RequestBody EnterpriseRequestDTO request) {
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
    }*/
}
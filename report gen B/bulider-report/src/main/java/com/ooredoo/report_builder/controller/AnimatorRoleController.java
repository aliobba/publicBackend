package com.ooredoo.report_builder.controller;

import com.ooredoo.report_builder.dto.request.CreateAnimatorRoleRequest;
import com.ooredoo.report_builder.dto.request.UpdateAnimatorRoleRequest;
import com.ooredoo.report_builder.dto.AnimatorRoleDTO;
import com.ooredoo.report_builder.services.AnimatorRoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animator-roles")
public class AnimatorRoleController {

    private final AnimatorRoleService animatorRoleService;

    public AnimatorRoleController(AnimatorRoleService animatorRoleService) {
        this.animatorRoleService = animatorRoleService;
    }

    @PostMapping
    @PreAuthorize("hasRole('MAIN_ADMIN') or hasRole('ENTREPRISE_ADMIN')")
    public ResponseEntity<AnimatorRoleDTO> createAnimatorRole(@RequestBody CreateAnimatorRoleRequest request) {
        return new ResponseEntity<>(animatorRoleService.createAnimatorRole(request), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('MAIN_ADMIN') or hasRole('ENTREPRISE_ADMIN')")
    public ResponseEntity<List<AnimatorRoleDTO>> getAllAnimatorRoles() {
        return ResponseEntity.ok(animatorRoleService.getAllAnimatorRoles());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('MAIN_ADMIN') or hasRole('ENTREPRISE_ADMIN')")
    public ResponseEntity<AnimatorRoleDTO> getAnimatorRoleById(@PathVariable Integer id) {
        return ResponseEntity.ok(animatorRoleService.getAnimatorRoleById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MAIN_ADMIN') or hasRole('ENTREPRISE_ADMIN')")
    public ResponseEntity<AnimatorRoleDTO> updateAnimatorRole(
            @PathVariable Integer id,
            @RequestBody UpdateAnimatorRoleRequest request) {
        return ResponseEntity.ok(animatorRoleService.updateAnimatorRole(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MAIN_ADMIN') or hasRole('ENTREPRISE_ADMIN')")
    public ResponseEntity<MessageResponse> deleteAnimatorRole(@PathVariable Integer id) {
        animatorRoleService.deleteAnimatorRole(id);
        return ResponseEntity.ok(new MessageResponse("Animator role deleted successfully"));
    }
}
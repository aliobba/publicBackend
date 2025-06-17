package com.ooredoo.report_builder.controller;

import com.ooredoo.report_builder.dto.request.CreateAnimatorRequest;
import com.ooredoo.report_builder.dto.request.UpdateAnimatorRequest;
import com.ooredoo.report_builder.dto.response.AnimatorResponse;
import com.ooredoo.report_builder.services.AnimatorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animators")
public class AnimatorController {

    private final AnimatorService animatorService;

    public AnimatorController(AnimatorService animatorService) {
        this.animatorService = animatorService;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<AnimatorResponse> createAnimator(@Valid @RequestBody CreateAnimatorRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(animatorService.createAnimator(request));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public List<AnimatorResponse> getAllAnimators() {
        return animatorService.getAllAnimators();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<AnimatorResponse> getAnimatorById(@PathVariable Integer id) {
        return ResponseEntity.ok(animatorService.getAnimatorById(id));
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public List<AnimatorResponse> getAnimatorsByUserId(@PathVariable Integer userId) {
        return animatorService.getAnimatorsByUserId(userId);
    }

    @GetMapping("/pos/{posId}")
    @PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public List<AnimatorResponse> getAnimatorsByPosId(@PathVariable Integer posId) {
        return animatorService.getAnimatorsByPosId(posId);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<AnimatorResponse> updateAnimator(@PathVariable Integer id, @RequestBody UpdateAnimatorRequest request) {
        return ResponseEntity.ok(animatorService.updateAnimator(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<MessageResponse> deleteAnimator(@PathVariable Integer id) {
        animatorService.deleteAnimator(id);
        return ResponseEntity.ok(new MessageResponse("Animator deleted successfully"));
    }
}
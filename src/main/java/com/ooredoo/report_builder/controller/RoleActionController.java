package com.ooredoo.report_builder.controller;

import com.ooredoo.report_builder.entity.RoleAction;
import com.ooredoo.report_builder.services.RoleActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/role-actions")
@CrossOrigin(origins = "*")
public class RoleActionController {

    @Autowired
    private RoleActionService roleActionService;

    @GetMapping
    public ResponseEntity<List<RoleAction>> getAllRoleActions() {
        return ResponseEntity.ok(roleActionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleAction> getRoleActionById(@PathVariable Integer id) {
        Optional<RoleAction> roleAction = roleActionService.findById(id);
        return roleAction.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/action-key/{actionKey}")
    public ResponseEntity<RoleAction> getRoleActionByActionKey(@PathVariable String actionKey) {
        Optional<RoleAction> roleAction = roleActionService.findByActionKey(actionKey);
        return roleAction.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/createRoleAction")
    public ResponseEntity<RoleAction> createRoleAction(@RequestBody RoleAction roleAction) {
        try {
            if (roleActionService.existsByActionKey(roleAction.getActionKey())) {
                return ResponseEntity.badRequest().build();
            }
            RoleAction savedRoleAction = roleActionService.save(roleAction);
            return ResponseEntity.ok(savedRoleAction);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/updateRoleAction/{id}")
    public ResponseEntity<RoleAction> updateRoleAction(@PathVariable Integer id, @RequestBody RoleAction roleAction) {
        try {
            if (!roleActionService.findById(id).isPresent()) {
                return ResponseEntity.notFound().build();
            }
            roleAction.setId(id);
            RoleAction updatedRoleAction = roleActionService.save(roleAction);
            return ResponseEntity.ok(updatedRoleAction);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/deleteRoleAction/{id}")
    public ResponseEntity<Void> deleteRoleAction(@PathVariable Integer id) {
        try {
            if (!roleActionService.findById(id).isPresent()) {
                return ResponseEntity.notFound().build();
            }
            roleActionService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

package com.ooredoo.report_builder.controller;

import com.ooredoo.report_builder.entity.RoleAction;
import com.ooredoo.report_builder.services.RoleService;
import com.ooredoo.report_builder.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/roles")
@CrossOrigin(origins = "*")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Integer id) {
        Optional<Role> role = roleService.findById(id);
        return role.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Role> getRoleByName(@PathVariable String name) {
        Optional<Role> role = roleService.findByName(name);
        return role.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        try {
            if (roleService.existsByName(role.getName())) {
                return ResponseEntity.badRequest().build();
            }
            Role savedRole = roleService.save(role);
            return ResponseEntity.ok(savedRole);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Integer id, @RequestBody Role role) {
        try {
            if (!roleService.findById(id).isPresent()) {
                return ResponseEntity.notFound().build();
            }
            role.setId(id);
            Role updatedRole = roleService.save(role);
            return ResponseEntity.ok(updatedRole);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Integer id) {
        try {
            if (!roleService.findById(id).isPresent()) {
                return ResponseEntity.notFound().build();
            }
            roleService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/actions")
    public ResponseEntity<Role> assignActions(@PathVariable Integer id, @RequestBody Set<RoleAction> actions) {
        try {
            Role updatedRole = roleService.assignActions(id, actions);
            return ResponseEntity.ok(updatedRole);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

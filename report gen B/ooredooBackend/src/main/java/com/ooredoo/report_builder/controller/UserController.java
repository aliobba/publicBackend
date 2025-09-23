package com.ooredoo.report_builder.controller;

import com.ooredoo.report_builder.controller.user.UserCreateRequest;
import com.ooredoo.report_builder.controller.user.UserResponse;
import com.ooredoo.report_builder.controller.user.UserUpdateRequest;
import com.ooredoo.report_builder.enums.UserType;
import com.ooredoo.report_builder.services.UserService;
import com.ooredoo.report_builder.user.User;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        Optional<User> user = userService.findById(id);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.findByEmail(email);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/type/{userType}")
    public ResponseEntity<List<User>> getUsersByType(@PathVariable UserType userType) {
        return ResponseEntity.ok(userService.findByUserType(userType));
    }

    @GetMapping("/available-heads/{userType}")
    public ResponseEntity<List<User>> getAvailableHeads(@PathVariable UserType userType) {
        return ResponseEntity.ok(userService.findAvailableHeads(userType));
    }

    @PostMapping("/addUser")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) throws MessagingException {
        try {
            userService.save(user);
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            System.out.println("ena exeption 2 " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
        try {
            if (!userService.findById(id).isPresent()) {
                return ResponseEntity.notFound().build();
            }
            user.setId(id);
            User updatedUser = userService.save(user);
            return ResponseEntity.ok(updatedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // ADDED: Separate endpoint for password updates
    @PutMapping("/{id}/password")
    public ResponseEntity<Map<String, String>> updatePassword(
            @PathVariable Integer id,
            @RequestBody Map<String, String> request) {
        try {
            String newPassword = request.get("newPassword");
            if (newPassword == null || newPassword.trim().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("message", "New password is required"));
            }

            userService.updatePassword(id, newPassword);
            return ResponseEntity.ok(Map.of("message", "Password updated successfully"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "User not found"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Failed to update password"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        try {
            if (!userService.findById(id).isPresent()) {
                return ResponseEntity.notFound().build();
            }
            userService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // PIN Verification Endpoints
    @PostMapping("/verify-pin")
    public ResponseEntity<Map<String, Object>> verifyPin(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String pin = request.get("pin");

        boolean isValid = userService.verifyPin(email, pin);

        Map<String, Object> response = Map.of(
                "valid", isValid,
                "message", isValid ? "PIN verified successfully" : "Invalid PIN"
        );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/pin")
    public ResponseEntity<Map<String, String>> updatePin(
            @PathVariable Integer id,
            @RequestBody Map<String, String> request) {
        try {
            String newPin = request.get("newPin");
            if (newPin == null || newPin.trim().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("message", "New PIN is required"));
            }

            userService.updatePin(id, newPin);
            return ResponseEntity.ok(Map.of("message", "PIN updated successfully"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "User not found"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Failed to update PIN"));
        }
    }
/*
    @PostMapping
    @PreAuthorize("hasAuthority('MAIN_ADMIN')")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserCreateRequest request) throws MessagingException {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('MAIN_ADMIN')")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }


    @GetMapping("/enterprise/{enterpriseId}")
    @PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public List<UserResponse> getUsersByEnterprise(@PathVariable int enterpriseId) {
        return userService.getUsersByEnterprise(enterpriseId);
    }
    
    @GetMapping("/sector/{sectorId}")
    @PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public List<UserResponse> getUsersBySector(@PathVariable int sectorId) {
        return userService.getUsersBySector(sectorId);
    }
    
    @GetMapping("/zone/{zoneId}")
    @PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public List<UserResponse> getUsersByZone(@PathVariable int zoneId) {
        return userService.getUsersByZone(zoneId);
    }
    
    @GetMapping("/region/{regionId}")
    @PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public List<UserResponse> getUsersByRegion(@PathVariable int regionId) {
        return userService.getUsersByRegion(regionId);
    }
    
    @GetMapping("/pos/{posId}")
    @PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public List<UserResponse> getUsersByPOS(@PathVariable int posId) {
        return userService.getUsersByPOS(posId);
    }
    
    @GetMapping("/type/{userType}")
    @PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public List<UserResponse> getUsersByUserType(@PathVariable UserType userType) {
        return userService.getUsersByUserType(userType);
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('MAIN_ADMIN')")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Integer userId, @RequestBody UserUpdateRequest request) {
        return ResponseEntity.ok(userService.updateUser(userId, request));
    }*/

    /*@DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('MAIN_ADMIN')")
    public ResponseEntity<MessageResponse> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok(new MessageResponse("User deleted successfully"));
    }*/

    @PostMapping("/activate")
    public ResponseEntity<MessageResponse> activateAccount(@RequestParam("token") String token) throws MessagingException {
        userService.activateAccount(token);
        return ResponseEntity.ok(new MessageResponse("Account activated successfully"));
    }
}

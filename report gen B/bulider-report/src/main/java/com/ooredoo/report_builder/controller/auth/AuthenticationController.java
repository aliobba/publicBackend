package com.ooredoo.report_builder.controller.auth;


import com.ooredoo.report_builder.services.authService.services.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> registerUser(
            @RequestBody @Valid RegistrationRequest request
    ) throws MessagingException {
        authService.register(request);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate (
            @RequestBody @Valid AuthenticationRequest request ){
        return ResponseEntity.ok(authService.authenticate(request));

    }

    @GetMapping("/activation-account")
    public void confirm (
            @RequestParam String token) throws MessagingException {

        authService.activateAccount(token);
    }

}

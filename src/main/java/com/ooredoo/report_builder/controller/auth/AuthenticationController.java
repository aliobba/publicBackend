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
@CrossOrigin(origins = "*")
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
            @RequestBody @Valid AuthenticationRequest request ) throws MessagingException {
        return ResponseEntity.ok(authService.authenticate(request));

    }

    @GetMapping("/activation-account")
    public void confirm (
            @RequestParam String token) throws MessagingException {

        authService.activateAccount(token);
    }

    @PostMapping("/verify-activation-code")
    public ResponseEntity<?> verifyActivationCode(@RequestBody @Valid ActivationCodeRequest request) {
        try {
            authService.verifyActivationCode(request.getCode());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid activation code");
        }
    }

    @PostMapping("/resend-activation")
    public ResponseEntity<?> resendActivationEmail(@RequestBody @Valid ResendActivationRequest request) throws MessagingException {
        authService.resendActivationEmail(request.getEmail());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody @Valid OtpRequest request) {
        try {
            authService.verifyOtp(request.getOtpCode());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid OTP code");
        }
    }

    @PostMapping("/resend-otp")
    public ResponseEntity<?> resendOtp(@RequestBody @Valid ResendOtpRequest request) throws MessagingException {
        authService.resendOtp(request.getEmail());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/setup-password")
    public ResponseEntity<?> setupPassword(@RequestBody @Valid PasswordSetupRequest request) {
        try {
            authService.setupPassword(request.getActivationCode(), request.getPassword());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

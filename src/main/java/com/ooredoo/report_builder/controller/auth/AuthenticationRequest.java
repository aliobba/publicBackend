package com.ooredoo.report_builder.controller.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AuthenticationRequest {
    @Email(message = "Email is not well formatted")
    @NotEmpty(message = "Email is mandatory")
    @NotNull(message = "Email is mandatory")
    private String email;

    @NotEmpty(message = "Password is mandatory")
    @NotNull(message = "Password is mandatory")
    @Size(min = 8, message = "Password should be 8 characters long minimum")
    private String password;

    AuthenticationRequest(@Email(message = "Email is not well formatted") @NotEmpty(message = "Email is mandatory") @NotNull(message = "Email is mandatory") String email, @NotEmpty(message = "Password is mandatory") @NotNull(message = "Password is mandatory") @Size(min = 8, message = "Password should be 8 characters long minimum") String password) {
        this.email = email;
        this.password = password;
    }

    public static AuthenticationRequestBuilder builder() {
        return new AuthenticationRequestBuilder();
    }

    public @Email(message = "Email is not well formatted") @NotEmpty(message = "Email is mandatory") @NotNull(message = "Email is mandatory") String getEmail() {
        return this.email;
    }

    public @NotEmpty(message = "Password is mandatory") @NotNull(message = "Password is mandatory") @Size(min = 8, message = "Password should be 8 characters long minimum") String getPassword() {
        return this.password;
    }

    public void setEmail(@Email(message = "Email is not well formatted") @NotEmpty(message = "Email is mandatory") @NotNull(message = "Email is mandatory") String email) {
        this.email = email;
    }

    public void setPassword(@NotEmpty(message = "Password is mandatory") @NotNull(message = "Password is mandatory") @Size(min = 8, message = "Password should be 8 characters long minimum") String password) {
        this.password = password;
    }

    public static class AuthenticationRequestBuilder {
        private @Email(message = "Email is not well formatted")
        @NotEmpty(message = "Email is mandatory")
        @NotNull(message = "Email is mandatory") String email;
        private @NotEmpty(message = "Password is mandatory")
        @NotNull(message = "Password is mandatory")
        @Size(min = 8, message = "Password should be 8 characters long minimum") String password;

        AuthenticationRequestBuilder() {
        }

        public AuthenticationRequestBuilder email(@Email(message = "Email is not well formatted") @NotEmpty(message = "Email is mandatory") @NotNull(message = "Email is mandatory") String email) {
            this.email = email;
            return this;
        }

        public AuthenticationRequestBuilder password(@NotEmpty(message = "Password is mandatory") @NotNull(message = "Password is mandatory") @Size(min = 8, message = "Password should be 8 characters long minimum") String password) {
            this.password = password;
            return this;
        }

        public AuthenticationRequest build() {
            return new AuthenticationRequest(this.email, this.password);
        }

        public String toString() {
            return "AuthenticationRequest.AuthenticationRequestBuilder(email=" + this.email + ", password=" + this.password + ")";
        }
    }
}

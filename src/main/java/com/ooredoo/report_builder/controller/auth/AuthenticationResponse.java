package com.ooredoo.report_builder.controller.auth;

public class AuthenticationResponse {

    private String token;

    AuthenticationResponse(String token) {
        this.token = token;
    }

    public static AuthenticationResponseBuilder builder() {
        return new AuthenticationResponseBuilder();
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class AuthenticationResponseBuilder {
        private String token;

        AuthenticationResponseBuilder() {
        }

        public AuthenticationResponseBuilder token(String token) {
            this.token = token;
            return this;
        }

        public AuthenticationResponse build() {
            return new AuthenticationResponse(this.token);
        }

        public String toString() {
            return "AuthenticationResponse.AuthenticationResponseBuilder(token=" + this.token + ")";
        }
    }
}

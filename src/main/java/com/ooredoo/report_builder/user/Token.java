package com.ooredoo.report_builder.user;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Token {

    @Id
    @GeneratedValue
    private Integer id;
    private String token;
    private LocalDateTime expirationAt;
    private LocalDateTime createdAt;
    private LocalDateTime validatedAt;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    public Token(Integer id, String token, LocalDateTime expirationAt, LocalDateTime createdAt, LocalDateTime validatedAt, User user) {
        this.id = id;
        this.token = token;
        this.expirationAt = expirationAt;
        this.createdAt = createdAt;
        this.validatedAt = validatedAt;
        this.user = user;
    }

    public Token() {
    }

    public static TokenBuilder builder() {
        return new TokenBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpirationAt() {
        return this.expirationAt;
    }

    public void setExpirationAt(LocalDateTime expirationAt) {
        this.expirationAt = expirationAt;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getValidatedAt() {
        return this.validatedAt;
    }

    public void setValidatedAt(LocalDateTime validatedAt) {
        this.validatedAt = validatedAt;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static class TokenBuilder {
        private Integer id;
        private String token;
        private LocalDateTime expirationAt;
        private LocalDateTime createdAt;
        private LocalDateTime validatedAt;
        private User user;

        TokenBuilder() {
        }

        public TokenBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public TokenBuilder token(String token) {
            this.token = token;
            return this;
        }

        public TokenBuilder expirationAt(LocalDateTime expirationAt) {
            this.expirationAt = expirationAt;
            return this;
        }

        public TokenBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public TokenBuilder validatedAt(LocalDateTime validatedAt) {
            this.validatedAt = validatedAt;
            return this;
        }

        public TokenBuilder user(User user) {
            this.user = user;
            return this;
        }

        public Token build() {
            return new Token(this.id, this.token, this.expirationAt, this.createdAt, this.validatedAt, this.user);
        }

        public String toString() {
            return "Token.TokenBuilder(id=" + this.id + ", token=" + this.token + ", expirationAt=" + this.expirationAt + ", createdAt=" + this.createdAt + ", validatedAt=" + this.validatedAt + ", user=" + this.user + ")";
        }
    }
}

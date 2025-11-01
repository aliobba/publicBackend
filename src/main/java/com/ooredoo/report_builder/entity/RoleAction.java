package com.ooredoo.report_builder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ooredoo.report_builder.user.Role;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role_action")
@EntityListeners(AuditingEntityListener.class)
public class RoleAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String actionKey; // e.g. "FORM_CREATE", "FORM_ASSIGN", "SUBMISSION_VIEW"

    private String description;

    // optional: for UI or mapping to endpoints
    private String endpointPattern; // e.g. "/api/v1/forms/**" (optional)
    // Many actions can belong to many roles
    @ManyToMany(mappedBy = "actions")
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();

    // Audit fields
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    public RoleAction(Integer id, String actionKey, String description, String endpointPattern, Set<Role> roles, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.actionKey = actionKey;
        this.description = description;
        this.endpointPattern = endpointPattern;
        this.roles = roles;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public RoleAction() {
    }

    public static RoleActionBuilder builder() {
        return new RoleActionBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActionKey() {
        return this.actionKey;
    }

    public void setActionKey(String actionKey) {
        this.actionKey = actionKey;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEndpointPattern() {
        return this.endpointPattern;
    }

    public void setEndpointPattern(String endpointPattern) {
        this.endpointPattern = endpointPattern;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    @JsonIgnore
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static class RoleActionBuilder {
        private Integer id;
        private String actionKey;
        private String description;
        private String endpointPattern;
        private Set<Role> roles;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        RoleActionBuilder() {
        }

        public RoleActionBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public RoleActionBuilder actionKey(String actionKey) {
            this.actionKey = actionKey;
            return this;
        }

        public RoleActionBuilder description(String description) {
            this.description = description;
            return this;
        }

        public RoleActionBuilder endpointPattern(String endpointPattern) {
            this.endpointPattern = endpointPattern;
            return this;
        }

        @JsonIgnore
        public RoleActionBuilder roles(Set<Role> roles) {
            this.roles = roles;
            return this;
        }

        public RoleActionBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public RoleActionBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public RoleAction build() {
            return new RoleAction(this.id, this.actionKey, this.description, this.endpointPattern, this.roles, this.createdAt, this.updatedAt);
        }

        public String toString() {
            return "RoleAction.RoleActionBuilder(id=" + this.id + ", actionKey=" + this.actionKey + ", description=" + this.description + ", endpointPattern=" + this.endpointPattern + ", roles=" + this.roles + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ")";
        }
    }
}
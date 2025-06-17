package com.ooredoo.report_builder.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "_role")
@EntityListeners(AuditingEntityListener.class)
public class Role {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<User> users;

    //for the entity listeners
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    public Role(Integer id, String name, List<User> users, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.users = users;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }
    public static Role mainAdmin() {
        return new Role("MAIN_ADMIN");
    }

    public static Role user() {
        return new Role("USER");
    }

    public static RoleBuilder builder() {
        return new RoleBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static class RoleBuilder {
        private Integer id;
        private String name;
        private List<User> users;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        RoleBuilder() {
        }

        public RoleBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public RoleBuilder name(String name) {
            this.name = name;
            return this;
        }

        @JsonIgnore
        public RoleBuilder users(List<User> users) {
            this.users = users;
            return this;
        }

        public RoleBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public RoleBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Role build() {
            return new Role(this.id, this.name, this.users, this.createdAt, this.updatedAt);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Role role = (Role) o;
            return Objects.equals(name, role.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
        public String toString() {
            return "Role.RoleBuilder(id=" + this.id + ", name=" + this.name + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ")";
        }
    }
}

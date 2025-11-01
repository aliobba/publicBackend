package com.ooredoo.report_builder.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ooredoo.report_builder.entity.RoleAction;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "_role")
@EntityListeners(AuditingEntityListener.class)
public class Role {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "action_to_Role",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "action_id"))
    private Set<RoleAction> actions = new HashSet<>();

    //for the entity listeners
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime created_Date;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updated_Date;


    public Role(Integer id, String name, Set<User> users, Set<RoleAction> actions, LocalDateTime created_Date, LocalDateTime updated_Date) {
        this.id = id;
        this.name = name;
        this.users = users;
        this.actions = actions;
        this.created_Date = created_Date;
        this.updated_Date = updated_Date;
    }

    public Role() {
    }

    public static RoleBuilder builder() {
        return new RoleBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return this.users;
    }

    @JsonIgnore
    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<RoleAction> getActions() {
        return this.actions;
    }

    public void setActions(Set<RoleAction> actions) {
        this.actions = actions;
    }

    public LocalDateTime getCreated_Date() {
        return this.created_Date;
    }

    public void setCreated_Date(LocalDateTime created_Date) {
        this.created_Date = created_Date;
    }

    public LocalDateTime getUpdated_Date() {
        return this.updated_Date;
    }

    public void setUpdated_Date(LocalDateTime updated_Date) {
        this.updated_Date = updated_Date;
    }

    public static class RoleBuilder {
        private Integer id;
        private String name;
        private Set<User> users;
        private Set<RoleAction> actions;
        private LocalDateTime created_Date;
        private LocalDateTime updated_Date;

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
        public RoleBuilder users(Set<User> users) {
            this.users = users;
            return this;
        }

        public RoleBuilder actions(Set<RoleAction> actions) {
            this.actions = actions;
            return this;
        }

        public RoleBuilder createdAt(LocalDateTime created_Date) {
            this.created_Date = created_Date;
            return this;
        }

        public RoleBuilder updatedAt(LocalDateTime updated_Date) {
            this.updated_Date = updated_Date;
            return this;
        }

        public Role build() {
            return new Role(this.id, this.name, this.users, this.actions, this.created_Date, this.updated_Date);
        }

        public String toString() {
            return "Role.RoleBuilder(id=" + this.id + ", name=" + this.name + ", users=" + this.users + ", actions=" + this.actions + ", createdAt=" + this.created_Date + ", updatedAt=" + this.updated_Date + ")";
        }
    }
}

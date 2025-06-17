package com.ooredoo.report_builder.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "animator_roles")
@EntityListeners(AuditingEntityListener.class)
public class AnimatorRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    // Actions associated with this role
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<RoleAction> actions = new HashSet<>();

    // Animators using this role
    @OneToMany(mappedBy = "role")
    private Set<Animator> animators = new HashSet<>();

    // Audit fields
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    public AnimatorRole() {
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<RoleAction> getActions() {
        return actions;
    }

    public void setActions(Set<RoleAction> actions) {
        this.actions = actions;
    }

    public Set<Animator> getAnimators() {
        return animators;
    }

    public void setAnimators(Set<Animator> animators) {
        this.animators = animators;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
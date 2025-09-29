package com.ooredoo.report_builder.entity;

import com.ooredoo.report_builder.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "forms")
@EntityListeners(AuditingEntityListener.class)
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String description;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    // ManyToMany relationship for component reusability
    @ManyToMany
    @JoinTable(
            name = "form_component_assignments",
            joinColumns = @JoinColumn(name = "form_id"),
            inverseJoinColumns = @JoinColumn(name = "component_id")
    )
    @Where(clause = "is_active = true") // Only active assignments
    @OrderBy("orderIndex ASC")
    private List<FormComponent> components = new ArrayList<>();

    // Direct access to assignments for management
    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL)
    @Where(clause = "is_active = true")
    @OrderBy("orderIndex ASC")
    private List<FormComponentAssignment> componentAssignments = new ArrayList<>();

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL)
    private List<FormSubmission> submissions = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "form_assigned_users",
            joinColumns = @JoinColumn(name = "form_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> assignedUsers = new HashSet<>();

    // Future: Organizational hierarchy assignments
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "form_assigned_enterprises",
            joinColumns = @JoinColumn(name = "form_id"),
            inverseJoinColumns = @JoinColumn(name = "enterprise_id"))
    private Set<Enterprise> assignedEnterprises = new HashSet<>();

    public Form(String name, String description, User creator) {
        this.name = name;
        this.description = description;
        this.creator = creator;
    }

    public Form(Integer id, String name, String description, LocalDateTime createdAt, LocalDateTime updatedAt, User creator, List<FormComponent> components, List<FormComponentAssignment> componentAssignments, List<FormSubmission> submissions, Set<User> assignedUsers, Set<Enterprise> assignedEnterprises) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.creator = creator;
        this.components = components;
        this.componentAssignments = componentAssignments;
        this.submissions = submissions;
        this.assignedUsers = assignedUsers;
        this.assignedEnterprises = assignedEnterprises;
    }

    public Form() {
    }

    public static FormBuilder builder() {
        return new FormBuilder();
    }

    // Helper methods for component management
    public void addComponent(FormComponent component, Integer orderIndex) {
        FormComponentAssignment assignment = new FormComponentAssignment(this, component, orderIndex);
        this.componentAssignments.add(assignment);
        this.components.add(component);
    }

    public void removeComponent(FormComponent component) {
        this.componentAssignments.stream()
                .filter(assignment -> assignment.getComponent().equals(component) && assignment.getIsActive())
                .findFirst()
                .ifPresent(FormComponentAssignment::unassign);

        this.components.remove(component);
    }

    public List<FormComponent> getActiveComponents() {
        return this.componentAssignments.stream()
                .filter(FormComponentAssignment::getIsActive)
                .sorted((a, b) -> Integer.compare(a.getOrderIndex(), b.getOrderIndex()))
                .map(FormComponentAssignment::getComponent)
                .collect(Collectors.toList());
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public User getCreator() {
        return this.creator;
    }

    public List<FormComponent> getComponents() {
        return this.components;
    }

    public List<FormComponentAssignment> getComponentAssignments() {
        return this.componentAssignments;
    }

    public List<FormSubmission> getSubmissions() {
        return this.submissions;
    }

    public Set<User> getAssignedUsers() {
        return this.assignedUsers;
    }

    public void setComponentAssignments(List<FormComponentAssignment> componentAssignments) {
        this.componentAssignments = componentAssignments;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void setComponents(List<FormComponent> components) {
        this.components = components;
    }

    public Set<Enterprise> getAssignedEnterprises() {
        return this.assignedEnterprises;
    }

    public void setSubmissions(List<FormSubmission> submissions) {
        this.submissions = submissions;
    }

    public void setAssignedUsers(Set<User> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }

    public void setAssignedEnterprises(Set<Enterprise> assignedEnterprises) {
        this.assignedEnterprises = assignedEnterprises;
    }

    public static class FormBuilder {
        private Integer id;
        private String name;
        private String description;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private User creator;
        private List<FormComponent> components;
        private List<FormComponentAssignment> componentAssignments;
        private List<FormSubmission> submissions;
        private Set<User> assignedUsers;
        private Set<Enterprise> assignedEnterprises;

        FormBuilder() {
        }

        public FormBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public FormBuilder name(String name) {
            this.name = name;
            return this;
        }

        public FormBuilder description(String description) {
            this.description = description;
            return this;
        }

        public FormBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public FormBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public FormBuilder creator(User creator) {
            this.creator = creator;
            return this;
        }

        public FormBuilder components(List<FormComponent> components) {
            this.components = components;
            return this;
        }

        public FormBuilder componentAssignments(List<FormComponentAssignment> componentAssignments) {
            this.componentAssignments = componentAssignments;
            return this;
        }

        public FormBuilder submissions(List<FormSubmission> submissions) {
            this.submissions = submissions;
            return this;
        }

        public FormBuilder assignedUsers(Set<User> assignedUsers) {
            this.assignedUsers = assignedUsers;
            return this;
        }

        public FormBuilder assignedEnterprises(Set<Enterprise> assignedEnterprises) {
            this.assignedEnterprises = assignedEnterprises;
            return this;
        }

        public Form build() {
            return new Form(this.id, this.name, this.description, this.createdAt, this.updatedAt, this.creator, this.components, this.componentAssignments, this.submissions, this.assignedUsers, this.assignedEnterprises);
        }

        public String toString() {
            return "Form.FormBuilder(id=" + this.id + ", name=" + this.name + ", description=" + this.description + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ", creator=" + this.creator + ", components=" + this.components + ", componentAssignments=" + this.componentAssignments + ", submissions=" + this.submissions + ", assignedUsers=" + this.assignedUsers + ", assignedEnterprises=" + this.assignedEnterprises + ")";
        }
    }
}

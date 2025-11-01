package com.ooredoo.report_builder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "form")
@EntityListeners(AuditingEntityListener.class)
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name_Form;

    private String description;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime creation_Date;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt_Date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", nullable = false)
    @JsonIgnoreProperties({"createdForms"})
    private User creator;

    // ManyToMany relationship for component reusability
   /* @ManyToMany
    @JoinTable(
            name = "form_component_assignment",
            joinColumns = @JoinColumn(name = "form_id"),
            inverseJoinColumns = @JoinColumn(name = "component_id")
    )*/
    /*
    @ManyToMany(fetch = FetchType.EAGER)
    @Where(clause = "is_active = true")
    @OrderBy("orderIndex ASC")
    @JsonIgnore
    private List<FormComponent> components = new ArrayList<>();
    */

    // Direct access to assignments for management
    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL)
    @Where(clause = "is_active = true")
    @OrderBy("orderIndex ASC")
    @JsonIgnore
    private List<FormComponentAssignment> componentAssignments = new ArrayList<>();

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL)
    private List<FormSubmission> submissions = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "form_assigned_users",
            joinColumns = @JoinColumn(name = "form_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnore
    private Set<User> assignedUsers = new HashSet<>();

    // Future: Organizational hierarchy assignments
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "form_assigned_enterprises",
            joinColumns = @JoinColumn(name = "form_id"),
            inverseJoinColumns = @JoinColumn(name = "enterprise_id"))
    @JsonIgnore
    private Set<Enterprise> assignedEnterprises = new HashSet<>();

    public Form(String name_Form, String description, User creator) {
        this.name_Form = name_Form;
        this.description = description;
        this.creator = creator;
    }

    public Form(Integer id, String name_Form, String description, LocalDateTime creation_Date, LocalDateTime updatedAt_Date, User creator, List<FormComponentAssignment> componentAssignments, List<FormSubmission> submissions, Set<User> assignedUsers, Set<Enterprise> assignedEnterprises) {
        this.id = id;
        this.name_Form = name_Form;
        this.description = description;
        this.creation_Date = creation_Date;
        this.updatedAt_Date = updatedAt_Date;
        this.creator = creator;
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
    public void addComponent(FormComponent component, Integer orderIndex, String label, Boolean required, String placeholder) {
        FormComponentAssignment assignment = new FormComponentAssignment(this, component, orderIndex, label, required, placeholder);
        this.componentAssignments.add(assignment);
    }

    public void removeComponent(FormComponent component) {
        this.componentAssignments.stream()
                .filter(assignment -> assignment.getComponent().equals(component) && assignment.getIsActive())
                .findFirst()
                .ifPresent(assignment -> {
                    assignment.setIsActive(false);
                    assignment.setUnassigned_Date(java.time.LocalDateTime.now());
                });
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

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName_Form() {
        return this.name_Form;
    }

    public void setName_Form(String name_Form) {
        this.name_Form = name_Form;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreation_Date() {
        return this.creation_Date;
    }

    public void setCreation_Date(LocalDateTime creation_Date) {
        this.creation_Date = creation_Date;
    }

    public LocalDateTime getUpdatedAt_Date() {
        return this.updatedAt_Date;
    }

    public void setUpdatedAt_Date(LocalDateTime updatedAt_Date) {
        this.updatedAt_Date = updatedAt_Date;
    }

    public User getCreator() {
        return this.creator;
    }

    @JsonIgnoreProperties({"createdForms"})
    public void setCreator(User creator) {
        this.creator = creator;
    }

    /*public List<FormComponent> getComponents() {
        return this.components;
    }

    @JsonIgnore
    public void setComponents(List<FormComponent> components) {
        this.components = components;
    }*/

    public List<FormComponentAssignment> getComponentAssignments() {
        return this.componentAssignments;
    }

    @JsonIgnore
    public void setComponentAssignments(List<FormComponentAssignment> componentAssignments) {
        this.componentAssignments = componentAssignments;
    }

    public List<FormSubmission> getSubmissions() {
        return this.submissions;
    }

    public void setSubmissions(List<FormSubmission> submissions) {
        this.submissions = submissions;
    }

    public Set<User> getAssignedUsers() {
        return this.assignedUsers;
    }

    @JsonIgnore
    public void setAssignedUsers(Set<User> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }

    public Set<Enterprise> getAssignedEnterprises() {
        return this.assignedEnterprises;
    }

    @JsonIgnore
    public void setAssignedEnterprises(Set<Enterprise> assignedEnterprises) {
        this.assignedEnterprises = assignedEnterprises;
    }

    public static class FormBuilder {
        private Integer id;
        private String name_Form;
        private String description;
        private LocalDateTime creation_Date;
        private LocalDateTime updatedAt_Date;
        private User creator;
        //private List<FormComponent> components;
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

        public FormBuilder name_Form(String name_Form) {
            this.name_Form = name_Form;
            return this;
        }

        public FormBuilder description(String description) {
            this.description = description;
            return this;
        }

        public FormBuilder creation_Date(LocalDateTime creation_Date) {
            this.creation_Date = creation_Date;
            return this;
        }

        public FormBuilder updatedAt_Date(LocalDateTime updatedAt_Date) {
            this.updatedAt_Date = updatedAt_Date;
            return this;
        }

        @JsonIgnoreProperties({"createdForms"})
        public FormBuilder creator(User creator) {
            this.creator = creator;
            return this;
        }

        /*@JsonIgnore
        public FormBuilder components(List<FormComponent> components) {
            this.components = components;
            return this;
        }*/

        @JsonIgnore
        public FormBuilder componentAssignments(List<FormComponentAssignment> componentAssignments) {
            this.componentAssignments = componentAssignments;
            return this;
        }

        public FormBuilder submissions(List<FormSubmission> submissions) {
            this.submissions = submissions;
            return this;
        }

        @JsonIgnore
        public FormBuilder assignedUsers(Set<User> assignedUsers) {
            this.assignedUsers = assignedUsers;
            return this;
        }

        @JsonIgnore
        public FormBuilder assignedEnterprises(Set<Enterprise> assignedEnterprises) {
            this.assignedEnterprises = assignedEnterprises;
            return this;
        }

        public Form build() {
            return new Form(this.id, this.name_Form, this.description, this.creation_Date, this.updatedAt_Date, this.creator, this.componentAssignments, this.submissions, this.assignedUsers, this.assignedEnterprises);
        }

        public String toString() {
            return "Form.FormBuilder(id=" + this.id + ", name_Form=" + this.name_Form + ", description=" + this.description + ", creation_Date=" + this.creation_Date + ", updatedAt_Date=" + this.updatedAt_Date + ", creator=" + this.creator +  ", componentAssignments=" + this.componentAssignments + ", submissions=" + this.submissions + ", assignedUsers=" + this.assignedUsers + ", assignedEnterprises=" + this.assignedEnterprises + ")";
        }
    }
}

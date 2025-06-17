package com.ooredoo.report_builder.entity;

import com.ooredoo.report_builder.user.User;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "forms")
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt=LocalDateTime.now();

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt=LocalDateTime.now();;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private User creator;

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL)
    private List<FormComponent> components;


    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL)
    private List<FormSubmission> submissions;

    @ManyToMany
    @JoinTable(
            name = "form_assigned_users",
            joinColumns = @JoinColumn(name = "form_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> assignedUsers;

    public Form(Integer id, String name, String description, Boolean published) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Form() {
    }

    public Form(Integer id, String name, String description, Boolean published, LocalDateTime createdAt, LocalDateTime updatedAt, User creator, List<FormComponent> components) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.creator = creator;
        this.components = components;
    }

    public Form(Integer id, String name, String description, LocalDateTime createdAt, LocalDateTime updatedAt, User creator, List<FormComponent> components, List<FormSubmission> submissions, Set<User> assignedUsers) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.creator = creator;
        this.components = components;
        this.submissions = submissions;
        this.assignedUsers = assignedUsers;
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


    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<FormSubmission> getSubmissions() {
        return this.submissions;
    }

    public Set<User> getAssignedUsers() {
        return this.assignedUsers;
    }

    public void setSubmissions(List<FormSubmission> submissions) {
        this.submissions = submissions;
    }

    public void setAssignedUsers(Set<User> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }


}

package com.ooredoo.report_builder.entity;

import com.ooredoo.report_builder.user.User;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "forms")
@EntityListeners(AuditingEntityListener.class)
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();
    ;


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
/*
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "form_assigned_enterprises",
            joinColumns = @JoinColumn(name = "form_id"),
            inverseJoinColumns = @JoinColumn(name = "enterprise_id"))
    private Set<Enterprise> assignedEnterprises = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "form_assigned_sectors",
            joinColumns = @JoinColumn(name = "form_id"),
            inverseJoinColumns = @JoinColumn(name = "sector_id"))
    private Set<Sector> assignedSectors = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "form_assigned_zones",
            joinColumns = @JoinColumn(name = "form_id"),
            inverseJoinColumns = @JoinColumn(name = "zone_id"))
    private Set<Zone> assignedZones = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "form_assigned_regions",
            joinColumns = @JoinColumn(name = "form_id"),
            inverseJoinColumns = @JoinColumn(name = "region_id"))
    private Set<Region> assignedRegions = new HashSet<>();
*/
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
       /* this.assignedEnterprises = assignedEnterprises;
        this.assignedSectors = assignedSectors;
        this.assignedZones = assignedZones;
        this.assignedRegions = assignedRegions;*/
    }

    public Form() {
    }

    public static FormBuilder builder() {
        return new FormBuilder();
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

    public List<FormSubmission> getSubmissions() {
        return this.submissions;
    }

    public Set<User> getAssignedUsers() {
        return this.assignedUsers;
    }
/*
    public Set<Enterprise> getAssignedEnterprises() {
        return this.assignedEnterprises;
    }

    public Set<Sector> getAssignedSectors() {
        return this.assignedSectors;
    }

    public Set<Zone> getAssignedZones() {
        return this.assignedZones;
    }

    public Set<Region> getAssignedRegions() {
        return this.assignedRegions;
    }*/

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

    public void setSubmissions(List<FormSubmission> submissions) {
        this.submissions = submissions;
    }

    public void setAssignedUsers(Set<User> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }
/*
    public void setAssignedEnterprises(Set<Enterprise> assignedEnterprises) {
        this.assignedEnterprises = assignedEnterprises;
    }

    public void setAssignedSectors(Set<Sector> assignedSectors) {
        this.assignedSectors = assignedSectors;
    }

    public void setAssignedZones(Set<Zone> assignedZones) {
        this.assignedZones = assignedZones;
    }

    public void setAssignedRegions(Set<Region> assignedRegions) {
        this.assignedRegions = assignedRegions;
    }
*/
    public static class FormBuilder {
        private Integer id;
        private String name;
        private String description;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private User creator;
        private List<FormComponent> components;
        private List<FormSubmission> submissions;
        private Set<User> assignedUsers;
       /* private Set<Enterprise> assignedEnterprises;
        private Set<Sector> assignedSectors;
        private Set<Zone> assignedZones;
        private Set<Region> assignedRegions;*/

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

        public FormBuilder submissions(List<FormSubmission> submissions) {
            this.submissions = submissions;
            return this;
        }

        public FormBuilder assignedUsers(Set<User> assignedUsers) {
            this.assignedUsers = assignedUsers;
            return this;
        }
/*
        public FormBuilder assignedEnterprises(Set<Enterprise> assignedEnterprises) {
            this.assignedEnterprises = assignedEnterprises;
            return this;
        }

        public FormBuilder assignedSectors(Set<Sector> assignedSectors) {
            this.assignedSectors = assignedSectors;
            return this;
        }

        public FormBuilder assignedZones(Set<Zone> assignedZones) {
            this.assignedZones = assignedZones;
            return this;
        }

        public FormBuilder assignedRegions(Set<Region> assignedRegions) {
            this.assignedRegions = assignedRegions;
            return this;
        }
*/
        public Form build() {
            return new Form(this.id, this.name, this.description, this.createdAt, this.updatedAt, this.creator, this.components, this.submissions, this.assignedUsers);
        }

        public String toString() {
            return "Form.FormBuilder(id=" + this.id + ", name=" + this.name + ", description=" + this.description + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ", creator=" + this.creator + ", components=" + this.components + ", submissions=" + this.submissions + ", assignedUsers=" + this.assignedUsers  + ")";
        }
    }
}

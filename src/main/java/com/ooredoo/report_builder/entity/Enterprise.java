package com.ooredoo.report_builder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ooredoo.report_builder.user.User;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "enterprise")
@EntityListeners(AuditingEntityListener.class)
public class Enterprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String enterpriseName;

    private String logoUrl;
    private String primaryColor;
    private String secondaryColor;

    // Users directly attached to enterprise
    @OneToMany(mappedBy = "enterprise", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<User> usersInEnterprise = new HashSet<>();

    // enterprise manager (optional)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    @JsonIgnore
    private User manager;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime creation_Date;

    @LastModifiedDate
    private LocalDateTime updatedAt_Date;

    public Enterprise(Integer id, String enterpriseName, String logoUrl, String primaryColor, String secondaryColor, Set<User> usersInEnterprise, User manager, LocalDateTime creation_Date, LocalDateTime updatedAt_Date) {
        this.id = id;
        this.enterpriseName = enterpriseName;
        this.logoUrl = logoUrl;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.usersInEnterprise = usersInEnterprise;
        this.manager = manager;
        this.creation_Date = creation_Date;
        this.updatedAt_Date = updatedAt_Date;
    }

    public Enterprise() {
    }

    public static EnterpriseBuilder builder() {
        return new EnterpriseBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnterpriseName() {
        return this.enterpriseName;
    }

    public void setEnterpriseName(String enterprise_Name) {
        this.enterpriseName = enterprise_Name;
    }

    public String getLogoUrl() {
        return this.logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getPrimaryColor() {
        return this.primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getSecondaryColor() {
        return this.secondaryColor;
    }

    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public Set<User> getUsersInEnterprise() {
        return this.usersInEnterprise;
    }

    @JsonIgnore
    public void setUsersInEnterprise(Set<User> usersInEnterprise) {
        this.usersInEnterprise = usersInEnterprise;
    }

    public User getManager() {
        return this.manager;
    }

    @JsonIgnore
    public void setManager(User manager) {
        this.manager = manager;
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

    public static class EnterpriseBuilder {
        private Integer id;
        private String enterpriseName;
        private String logoUrl;
        private String primaryColor;
        private String secondaryColor;
        private Set<User> usersInEnterprise;
        private User manager;
        private LocalDateTime creation_Date;
        private LocalDateTime updatedAt_Date;

        EnterpriseBuilder() {
        }

        public EnterpriseBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public EnterpriseBuilder enterprise_Name(String enterpriseName) {
            this.enterpriseName = enterpriseName;
            return this;
        }

        public EnterpriseBuilder logoUrl(String logoUrl) {
            this.logoUrl = logoUrl;
            return this;
        }

        public EnterpriseBuilder primaryColor(String primaryColor) {
            this.primaryColor = primaryColor;
            return this;
        }

        public EnterpriseBuilder secondaryColor(String secondaryColor) {
            this.secondaryColor = secondaryColor;
            return this;
        }

        @JsonIgnore
        public EnterpriseBuilder usersInEnterprise(Set<User> usersInEnterprise) {
            this.usersInEnterprise = usersInEnterprise;
            return this;
        }

        @JsonIgnore
        public EnterpriseBuilder manager(User manager) {
            this.manager = manager;
            return this;
        }

        public EnterpriseBuilder creation_Date(LocalDateTime creation_Date) {
            this.creation_Date = creation_Date;
            return this;
        }

        public EnterpriseBuilder updatedAt_Date(LocalDateTime updatedAt_Date) {
            this.updatedAt_Date = updatedAt_Date;
            return this;
        }

        public Enterprise build() {
            return new Enterprise(this.id, this.enterpriseName, this.logoUrl, this.primaryColor, this.secondaryColor, this.usersInEnterprise, this.manager, this.creation_Date, this.updatedAt_Date);
        }

        public String toString() {
            return "Enterprise.EnterpriseBuilder(id=" + this.id + ", enterprise_Name=" + this.enterpriseName + ", logoUrl=" + this.logoUrl + ", primaryColor=" + this.primaryColor + ", secondaryColor=" + this.secondaryColor + ", usersInEnterprise=" + this.usersInEnterprise + ", manager=" + this.manager + ", creation_Date=" + this.creation_Date + ", updatedAt_Date=" + this.updatedAt_Date + ")";
        }
    }
}
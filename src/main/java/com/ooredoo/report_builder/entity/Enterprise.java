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
@Table(name = "enterprises")
@EntityListeners(AuditingEntityListener.class)
public class Enterprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

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
    private User manager;

    /*@OneToMany(mappedBy = "enterprise", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Sector> sectors = new HashSet<>();*/

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


    public Enterprise(Integer id, String name, String logoUrl, String primaryColor, String secondaryColor, Set<User> usersInEnterprise, User manager, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.logoUrl = logoUrl;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.usersInEnterprise = usersInEnterprise;
        this.manager = manager;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Enterprise(Integer id, String name, String logoUrl, String primaryColor, String secondaryColor, Set<User> usersInEnterprise, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.logoUrl = logoUrl;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.usersInEnterprise = usersInEnterprise;
        //this.manager = manager;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Enterprise() {
    }

    public static EnterpriseBuilder builder() {
        return new EnterpriseBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getLogoUrl() {
        return this.logoUrl;
    }

    public String getPrimaryColor() {
        return this.primaryColor;
    }

    public String getSecondaryColor() {
        return this.secondaryColor;
    }

    public Set<User> getUsersInEnterprise() {
        return this.usersInEnterprise;
    }

    public User getManager() {
        return this.manager;
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

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    @JsonIgnore
    public void setUsersInEnterprise(Set<User> usersInEnterprise) {
        this.usersInEnterprise = usersInEnterprise;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


    public static class EnterpriseBuilder {
        private Integer id;
        private String name;
        private String logoUrl;
        private String primaryColor;
        private String secondaryColor;
        private Set<User> usersInEnterprise;
        //private User manager;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        EnterpriseBuilder() {
        }

        public EnterpriseBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public EnterpriseBuilder name(String name) {
            this.name = name;
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

        public EnterpriseBuilder usersInEnterprise(Set<User> usersInEnterprise) {
            this.usersInEnterprise = usersInEnterprise;
            return this;
        }

       /* public EnterpriseBuilder manager(User manager) {
            this.manager = manager;
            return this;
        }*/


        public EnterpriseBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public EnterpriseBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Enterprise build() {
            return new Enterprise(this.id, this.name, this.logoUrl, this.primaryColor, this.secondaryColor, this.usersInEnterprise, this.createdAt, this.updatedAt);
        }

        public String toString() {
            return "Enterprise.EnterpriseBuilder(id=" + this.id + ", name=" + this.name + ", logoUrl=" + this.logoUrl + ", primaryColor=" + this.primaryColor + ", secondaryColor=" + this.secondaryColor + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ")";
        }
    }
}
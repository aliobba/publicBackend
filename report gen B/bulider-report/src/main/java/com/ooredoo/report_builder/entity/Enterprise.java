package com.ooredoo.report_builder.entity;

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

    private String description;

    @Column(columnDefinition = "TEXT")
    private String logo;

    @Column(name = "primary_color")
    private String primaryColor;

    @Column(name = "secondary_color")
    private String secondaryColor;

    // Enterprise admin/manager
    @OneToOne
    @JoinColumn(name = "admin_id")
    private User enterpriseAdmin;

    // Users belonging to this enterprise
    @OneToMany(mappedBy = "enterprise")
    private Set<User> users = new HashSet<>();

    // Points of Sale for this enterprise
    @OneToMany(mappedBy = "enterprise", cascade = CascadeType.ALL)
    private Set<POS> pointsOfSale = new HashSet<>();

    // Sectors in this enterprise
    @OneToMany(mappedBy = "enterprise", cascade = CascadeType.ALL)
    private Set<Sector> sectors = new HashSet<>();

    // Audit fields
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    public Enterprise() {
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public User getEnterpriseAdmin() {
        return enterpriseAdmin;
    }

    public void setEnterpriseAdmin(User enterpriseAdmin) {
        this.enterpriseAdmin = enterpriseAdmin;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<POS> getPointsOfSale() {
        return pointsOfSale;
    }

    public void setPointsOfSale(Set<POS> pointsOfSale) {
        this.pointsOfSale = pointsOfSale;
    }

    public Set<Sector> getSectors() {
        return sectors;
    }

    public void setSectors(Set<Sector> sectors) {
        this.sectors = sectors;
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
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
@Table(name = "regions")
@EntityListeners(AuditingEntityListener.class)
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String description;

    // Head of Region
    @OneToOne
    @JoinColumn(name = "head_id")
    private User regionHead;

    // Users assigned to this region
    @OneToMany(mappedBy = "region")
    private Set<User> users = new HashSet<>();

    // Zone this region belongs to
    @ManyToOne
    @JoinColumn(name = "zone_id", nullable = false)
    private Zone zone;

    // Points of Sale in this region
    @OneToMany(mappedBy = "region")
    private Set<POS> pointsOfSale = new HashSet<>();

    // Audit fields
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    public Region() {
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

    public User getRegionHead() {
        return regionHead;
    }

    public void setRegionHead(User regionHead) {
        this.regionHead = regionHead;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Set<POS> getPointsOfSale() {
        return pointsOfSale;
    }

    public void setPointsOfSale(Set<POS> pointsOfSale) {
        this.pointsOfSale = pointsOfSale;
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
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
@Table(name = "sectors")
@EntityListeners(AuditingEntityListener.class)
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_headOfSector")
    private User headOfSector;

    @OneToMany(mappedBy = "sector", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Zone> zones = new HashSet<>();

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Sector(Integer id, String name,  User headOfSector, Set<Zone> zones, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.headOfSector = headOfSector;
        this.zones = zones;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Sector() {
    }

    public static SectorBuilder builder() {
        return new SectorBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }


    public User getHeadOfSector() {
        return this.headOfSector;
    }

    public Set<Zone> getZones() {
        return this.zones;
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


    public void setHeadOfSector(User headOfSector) {
        this.headOfSector = headOfSector;
    }

    public void setZones(Set<Zone> zones) {
        this.zones = zones;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static class SectorBuilder {
        private Integer id;
        private String name;
        private Enterprise enterprise;
        private User headOfSector;
        private Set<Zone> zones;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        SectorBuilder() {
        }

        public SectorBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public SectorBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SectorBuilder headOfSector(User headOfSector) {
            this.headOfSector = headOfSector;
            return this;
        }

        public SectorBuilder zones(Set<Zone> zones) {
            this.zones = zones;
            return this;
        }

        public SectorBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public SectorBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Sector build() {
            return new Sector(this.id, this.name,  this.headOfSector, this.zones, this.createdAt, this.updatedAt);
        }

        public String toString() {
            return "Sector.SectorBuilder(id=" + this.id + ", name=" + this.name + ", headOfSector=" + this.headOfSector.getId() + ", zones=" + this.zones + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ")";
        }
    }
}
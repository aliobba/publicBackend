package com.ooredoo.report_builder.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JoinColumn(name = "id_head_of_sector")
    private User headOfSector;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id")
    @JsonIgnoreProperties({"sectors"})
    private Zone zone;

    @OneToMany(mappedBy = "sector", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<POS> posInSector = new HashSet<>();

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Sector(Integer id, String name, User headOfSector, Zone zone, Set<POS> posInSector, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.headOfSector = headOfSector;
        this.zone = zone;
        this.posInSector = posInSector;
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

    public Zone getZone() {
        return this.zone;
    }

    public Set<POS> getPosInSector() {
        return this.posInSector;
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

    @JsonIgnoreProperties({"sectors"})
    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public void setPosInSector(Set<POS> posInSector) {
        this.posInSector = posInSector;
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
        private User headOfSector;
        private Zone zone;
        private Set<POS> posInSector;
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

        @JsonIgnoreProperties({"sectors"})
        public SectorBuilder zone(Zone zone) {
            this.zone = zone;
            return this;
        }

        public SectorBuilder posInSector(Set<POS> posInSector) {
            this.posInSector = posInSector;
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
            return new Sector(this.id, this.name, this.headOfSector, this.zone, this.posInSector, this.createdAt, this.updatedAt);
        }

        public String toString() {
            return "Sector.SectorBuilder(id=" + this.id + ", name=" + this.name + ", headOfSector=" + this.headOfSector + ", zone=" + this.zone + ", posInSector=" + this.posInSector + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ")";
        }
    }
}
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
@Table(name = "sector")
@EntityListeners(AuditingEntityListener.class)
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String sectorName;

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
    private LocalDateTime creation_Date;

    @LastModifiedDate
    private LocalDateTime updatedAt_Date;


    public Sector(Integer id, String sectorName, User headOfSector, Zone zone, Set<POS> posInSector, LocalDateTime creation_Date, LocalDateTime updatedAt_Date) {
        this.id = id;
        this.sectorName = sectorName;
        this.headOfSector = headOfSector;
        this.zone = zone;
        this.posInSector = posInSector;
        this.creation_Date = creation_Date;
        this.updatedAt_Date = updatedAt_Date;
    }

    public Sector() {
    }

    public static SectorBuilder builder() {
        return new SectorBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSectorName() {
        return this.sectorName;
    }

    public void setSectorName(String sector_Name) {
        this.sectorName = sector_Name;
    }

    public User getHeadOfSector() {
        return this.headOfSector;
    }

    public void setHeadOfSector(User headOfSector) {
        this.headOfSector = headOfSector;
    }

    public Zone getZone() {
        return this.zone;
    }

    @JsonIgnoreProperties({"sectors"})
    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Set<POS> getPosInSector() {
        return this.posInSector;
    }

    public void setPosInSector(Set<POS> posInSector) {
        this.posInSector = posInSector;
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

    public static class SectorBuilder {
        private Integer id;
        private String sectorName;
        private User headOfSector;
        private Zone zone;
        private Set<POS> posInSector;
        private LocalDateTime creation_Date;
        private LocalDateTime updatedAt_Date;

        SectorBuilder() {
        }

        public SectorBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public SectorBuilder sector_Name(String sector_Name) {
            this.sectorName = sector_Name;
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

        public SectorBuilder creation_Date(LocalDateTime creation_Date) {
            this.creation_Date = creation_Date;
            return this;
        }

        public SectorBuilder updatedAt_Date(LocalDateTime updatedAt_Date) {
            this.updatedAt_Date = updatedAt_Date;
            return this;
        }

        public Sector build() {
            return new Sector(this.id, this.sectorName, this.headOfSector, this.zone, this.posInSector, this.creation_Date, this.updatedAt_Date);
        }

        public String toString() {
            return "Sector.SectorBuilder(id=" + this.id + ", sector_Name=" + this.sectorName + ", headOfSector=" + this.headOfSector + ", zone=" + this.zone + ", posInSector=" + this.posInSector + ", creation_Date=" + this.creation_Date + ", updatedAt_Date=" + this.updatedAt_Date + ")";
        }
    }
}
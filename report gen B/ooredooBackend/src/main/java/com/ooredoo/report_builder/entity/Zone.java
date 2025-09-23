package com.ooredoo.report_builder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "zones")
@EntityListeners(AuditingEntityListener.class)
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sector_id")
    //@JsonIgnore
    @JsonIgnoreProperties({"zones"})
    private Sector sector;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_headOfZone")
    private User headOfZone;

    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Region> regions = new HashSet<>();

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Zone(Integer id, String name, Sector sector, User headOfZone, Set<Region> regions, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.sector = sector;
        this.headOfZone = headOfZone;
        this.regions = regions;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Zone() {
    }

    public static ZoneBuilder builder() {
        return new ZoneBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Sector getSector() {
        return this.sector;
    }

    public User getHeadOfZone() {
        return this.headOfZone;
    }

    public Set<Region> getRegions() {
        return this.regions;
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

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public void setHeadOfZone(User headOfZone) {
        this.headOfZone = headOfZone;
    }

    public void setRegions(Set<Region> regions) {
        this.regions = regions;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static class ZoneBuilder {
        private Integer id;
        private String name;
        private Sector sector;
        private User headOfZone;
        private Set<Region> regions;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        ZoneBuilder() {
        }

        public ZoneBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public ZoneBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ZoneBuilder sector(Sector sector) {
            this.sector = sector;
            return this;
        }

        public ZoneBuilder headOfZone(User headOfZone) {
            this.headOfZone = headOfZone;
            return this;
        }

        public ZoneBuilder regions(Set<Region> regions) {
            this.regions = regions;
            return this;
        }

        public ZoneBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public ZoneBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Zone build() {
            return new Zone(this.id, this.name, this.sector, this.headOfZone, this.regions, this.createdAt, this.updatedAt);
        }

        public String toString() {
            return "Zone.ZoneBuilder(id=" + this.id + ", name=" + this.name + ", sector=" + this.sector.getId() + ", headOfZone=" + this.headOfZone.getId() + ", regions=" + this.regions + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ")";
        }
    }
}
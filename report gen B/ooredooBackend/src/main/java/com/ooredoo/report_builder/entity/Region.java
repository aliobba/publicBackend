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
@Table(name = "regions")
@EntityListeners(AuditingEntityListener.class)
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_head_of_region")
    private User headOfRegion;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


    // POS inside this region
    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<POS> posInRegion = new HashSet<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id")
    @JsonIgnoreProperties({"regions"})
    private Zone zone;

    public Region(Integer id, String name, User headOfRegion, LocalDateTime createdAt, LocalDateTime updatedAt, Set<POS> posInRegion, Zone zone) {
        this.id = id;
        this.name = name;
        this.headOfRegion = headOfRegion;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.posInRegion = posInRegion;
        this.zone = zone;
    }

    public Region() {
    }

    public static RegionBuilder builder() {
        return new RegionBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public User getHeadOfRegion() {
        return this.headOfRegion;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public Set<POS> getPosInRegion() {
        return this.posInRegion;
    }

    public Zone getZone() {
        return this.zone;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeadOfRegion(User headOfRegion) {
        this.headOfRegion = headOfRegion;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setPosInRegion(Set<POS> posInRegion) {
        this.posInRegion = posInRegion;
    }

    @JsonIgnoreProperties({"regions"})
    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public static class RegionBuilder {
        private Integer id;
        private String name;
        private User headOfRegion;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private Set<POS> posInRegion;
        private Zone zone;

        RegionBuilder() {
        }

        public RegionBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public RegionBuilder name(String name) {
            this.name = name;
            return this;
        }

        public RegionBuilder headOfRegion(User headOfRegion) {
            this.headOfRegion = headOfRegion;
            return this;
        }

        public RegionBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public RegionBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public RegionBuilder posInRegion(Set<POS> posInRegion) {
            this.posInRegion = posInRegion;
            return this;
        }

        public RegionBuilder zone(Zone zone) {
            this.zone = zone;
            return this;
        }

        public Region build() {
            return new Region(this.id, this.name, this.headOfRegion, this.createdAt, this.updatedAt, this.posInRegion, this.zone);
        }

        public String toString() {
            return "Region.RegionBuilder(id=" + this.id + ", name=" + this.name + ", headOfRegion=" + this.headOfRegion.getId() + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ", posInRegion=" + this.posInRegion + ", zone=" + this.zone.getId() + ")";
        }
    }
}
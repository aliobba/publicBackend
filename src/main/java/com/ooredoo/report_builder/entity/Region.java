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
@Table(name = "region")
@EntityListeners(AuditingEntityListener.class)
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String regionName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_head_of_region")
    private User headOfRegion;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Zone> zones = new HashSet<>();

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime creation_Date;

    @LastModifiedDate
    private LocalDateTime updatedAt_Date;

    public Region(Integer id, String regionName, User headOfRegion, Set<Zone> zones, LocalDateTime creation_Date, LocalDateTime updatedAt_Date) {
        this.id = id;
        this.regionName = regionName;
        this.headOfRegion = headOfRegion;
        this.zones = zones;
        this.creation_Date = creation_Date;
        this.updatedAt_Date = updatedAt_Date;
    }

    public Region() {
    }

    public static RegionBuilder builder() {
        return new RegionBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegionName() {
        return this.regionName;
    }

    public void setRegionName(String region_Name) {
        this.regionName = region_Name;
    }

    public User getHeadOfRegion() {
        return this.headOfRegion;
    }

    public void setHeadOfRegion(User headOfRegion) {
        this.headOfRegion = headOfRegion;
    }

    public Set<Zone> getZones() {
        return this.zones;
    }

    public void setZones(Set<Zone> zones) {
        this.zones = zones;
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

    public static class RegionBuilder {
        private Integer id;
        private String region_Name;
        private User headOfRegion;
        private Set<Zone> zones;
        private LocalDateTime creation_Date;
        private LocalDateTime updatedAt_Date;

        RegionBuilder() {
        }

        public RegionBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public RegionBuilder region_Name(String region_Name) {
            this.region_Name = region_Name;
            return this;
        }

        public RegionBuilder headOfRegion(User headOfRegion) {
            this.headOfRegion = headOfRegion;
            return this;
        }

        public RegionBuilder zones(Set<Zone> zones) {
            this.zones = zones;
            return this;
        }

        public RegionBuilder creation_Date(LocalDateTime creation_Date) {
            this.creation_Date = creation_Date;
            return this;
        }

        public RegionBuilder updatedAt_Date(LocalDateTime updatedAt_Date) {
            this.updatedAt_Date = updatedAt_Date;
            return this;
        }

        public Region build() {
            return new Region(this.id, this.region_Name, this.headOfRegion, this.zones, this.creation_Date, this.updatedAt_Date);
        }

        public String toString() {
            return "Region.RegionBuilder(id=" + this.id + ", region_Name=" + this.region_Name + ", headOfRegion=" + this.headOfRegion + ", zones=" + this.zones + ", creation_Date=" + this.creation_Date + ", updatedAt_Date=" + this.updatedAt_Date + ")";
        }
    }
}
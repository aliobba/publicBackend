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
@Table(name = "zone")
@EntityListeners(AuditingEntityListener.class)
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String zoneName;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_head_of_zone")
    private User headOfZone;

    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Sector> sectors = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @JsonIgnoreProperties({"zones"})
    private Region region;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime creationDate;

    @LastModifiedDate
    private LocalDateTime updatedAt_Date;

    public Zone(Integer id, String zoneName, User headOfZone, Set<Sector> sectors, Region region, LocalDateTime creationDate, LocalDateTime updatedAt_Date) {
        this.id = id;
        this.zoneName = zoneName;
        this.headOfZone = headOfZone;
        this.sectors = sectors;
        this.region = region;
        this.creationDate = creationDate;
        this.updatedAt_Date = updatedAt_Date;
    }

    public Zone() {
    }

    public static ZoneBuilder builder() {
        return new ZoneBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getZoneName() {
        return this.zoneName;
    }

    public void setZoneName(String zone_name) {
        this.zoneName = zone_name;
    }

    public User getHeadOfZone() {
        return this.headOfZone;
    }

    public void setHeadOfZone(User headOfZone) {
        this.headOfZone = headOfZone;
    }

    public Set<Sector> getSectors() {
        return this.sectors;
    }

    public void setSectors(Set<Sector> sectors) {
        this.sectors = sectors;
    }

    public Region getRegion() {
        return this.region;
    }

    @JsonIgnoreProperties({"zones"})
    public void setRegion(Region region) {
        this.region = region;
    }

    public LocalDateTime getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getUpdatedAt_Date() {
        return this.updatedAt_Date;
    }

    public void setUpdatedAt_Date(LocalDateTime updatedAt_Date) {
        this.updatedAt_Date = updatedAt_Date;
    }

    public static class ZoneBuilder {
        private Integer id;
        private String zone_name;
        private User headOfZone;
        private Set<Sector> sectors;
        private Region region;
        private LocalDateTime creationDate;
        private LocalDateTime updatedAt_Date;

        ZoneBuilder() {
        }

        public ZoneBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public ZoneBuilder zone_name(String zone_name) {
            this.zone_name = zone_name;
            return this;
        }

        public ZoneBuilder headOfZone(User headOfZone) {
            this.headOfZone = headOfZone;
            return this;
        }

        public ZoneBuilder sectors(Set<Sector> sectors) {
            this.sectors = sectors;
            return this;
        }

        @JsonIgnoreProperties({"zones"})
        public ZoneBuilder region(Region region) {
            this.region = region;
            return this;
        }

        public ZoneBuilder creationDate(LocalDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public ZoneBuilder updatedAt_Date(LocalDateTime updatedAt_Date) {
            this.updatedAt_Date = updatedAt_Date;
            return this;
        }

        public Zone build() {
            return new Zone(this.id, this.zone_name, this.headOfZone, this.sectors, this.region, this.creationDate, this.updatedAt_Date);
        }

        public String toString() {
            return "Zone.ZoneBuilder(id=" + this.id + ", zone_name=" + this.zone_name + ", headOfZone=" + this.headOfZone + ", sectors=" + this.sectors + ", region=" + this.region + ", creationDate=" + this.creationDate + ", updatedAt_Date=" + this.updatedAt_Date + ")";
        }
    }
}
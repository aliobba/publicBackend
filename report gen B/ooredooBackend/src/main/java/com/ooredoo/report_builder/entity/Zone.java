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
@Table(name = "zones")
@EntityListeners(AuditingEntityListener.class)
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;


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
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Zone(Integer id, String name, User headOfZone, Set<Sector> sectors, Region region, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.headOfZone = headOfZone;
        this.sectors = sectors;
        this.region = region;
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

    public User getHeadOfZone() {
        return this.headOfZone;
    }

    public Set<Sector> getSectors() {
        return this.sectors;
    }

    public Region getRegion() {
        return this.region;
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

    public void setHeadOfZone(User headOfZone) {
        this.headOfZone = headOfZone;
    }

    public void setSectors(Set<Sector> sectors) {
        this.sectors = sectors;
    }

    @JsonIgnoreProperties({"zones"})
    public void setRegion(Region region) {
        this.region = region;
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
        private User headOfZone;
        private Set<Sector> sectors;
        private Region region;
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

        public ZoneBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public ZoneBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Zone build() {
            return new Zone(this.id, this.name, this.headOfZone, this.sectors, this.region, this.createdAt, this.updatedAt);
        }

        public String toString() {
            return "Zone.ZoneBuilder(id=" + this.id + ", name=" + this.name + ", headOfZone=" + this.headOfZone + ", sectors=" + this.sectors + ", region=" + this.region + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ")";
        }
    }
}
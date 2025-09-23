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
@Table(name = "points_of_sale")
@EntityListeners(AuditingEntityListener.class)
public class POS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;
    // POS Manager
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_HeadOfPOS")
    private User headOfPOS;

    // Region this POS belongs to
    @ManyToOne
    @JoinColumn(name = "region_id")
    @JsonIgnoreProperties({"posInRegion"})
    private Region region;

    /*@OneToMany(mappedBy = "pos")
    private Set<User> users = new HashSet<>();*/

    // Audit fields
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    public POS(Integer id, String name, User headOfPOS, Region region, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.headOfPOS = headOfPOS;
        this.region = region;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public POS() {
    }

    public static POSBuilder builder() {
        return new POSBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public User getHeadOfPOS() {
        return this.headOfPOS;
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

    public void setHeadOfPOS(User headOfPOS) {
        this.headOfPOS = headOfPOS;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static class POSBuilder {
        private Integer id;
        private String name;
        private User headOfPOS;
        private Region region;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        POSBuilder() {
        }

        public POSBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public POSBuilder name(String name) {
            this.name = name;
            return this;
        }

        public POSBuilder headOfPOS(User headOfPOS) {
            this.headOfPOS = headOfPOS;
            return this;
        }

        public POSBuilder region(Region region) {
            this.region = region;
            return this;
        }

        public POSBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public POSBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public POS build() {
            return new POS(this.id, this.name, this.headOfPOS, this.region, this.createdAt, this.updatedAt);
        }

        public String toString() {
            return "POS.POSBuilder(id=" + this.id + ", name=" + this.name + ", headOfPOS=" + this.headOfPOS.getId() + ", region=" + this.region + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ")";
        }
    }
}
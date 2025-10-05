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
@Table(name = "points_of_sale")
@EntityListeners(AuditingEntityListener.class)
public class POS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String codePOS;

    // POS Manager
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_head_OfPOS")
    private User headOfPOS;

    // Region this POS belongs to
    @ManyToOne
    @JoinColumn(name = "sector_id")
    @JsonIgnoreProperties({"posInSector"})
    private Sector sector;

    @OneToMany(mappedBy = "pos")
    private Set<User> users = new HashSet<>();

    // Audit fields
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;


    public POS(Integer id, String name, String codePOS, User headOfPOS, Sector sector, Set<User> users, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.codePOS = codePOS;
        this.headOfPOS = headOfPOS;
        this.sector = sector;
        this.users = users;
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

    public String getCodePOS() {
        return this.codePOS;
    }

    public User getHeadOfPOS() {
        return this.headOfPOS;
    }

    public Sector getSector() {
        return this.sector;
    }

    public Set<User> getUsers() {
        return this.users;
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

    public void setCodePOS(String codePOS) {
        this.codePOS = codePOS;
    }

    public void setHeadOfPOS(User headOfPOS) {
        this.headOfPOS = headOfPOS;
    }

    @JsonIgnoreProperties({"posInSector"})
    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
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
        private String codePOS;
        private User headOfPOS;
        private Sector sector;
        private Set<User> users;
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

        public POSBuilder codePOS(String codePOS) {
            this.codePOS = codePOS;
            return this;
        }

        public POSBuilder headOfPOS(User headOfPOS) {
            this.headOfPOS = headOfPOS;
            return this;
        }

        @JsonIgnoreProperties({"posInSector"})
        public POSBuilder sector(Sector sector) {
            this.sector = sector;
            return this;
        }

        public POSBuilder users(Set<User> users) {
            this.users = users;
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
            return new POS(this.id, this.name, this.codePOS, this.headOfPOS, this.sector, this.users, this.createdAt, this.updatedAt);
        }

        public String toString() {
            return "POS.POSBuilder(id=" + this.id + ", name=" + this.name + ", codePOS=" + this.codePOS + ", headOfPOS=" + this.headOfPOS + ", sector=" + this.sector + ", users=" + this.users + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ")";
        }
    }
}
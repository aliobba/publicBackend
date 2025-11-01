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
@Table(name = "point_of_sale")
@EntityListeners(AuditingEntityListener.class)
public class POS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String posName;

    @Column(nullable = false, unique = true)
    private String codeOfPOS;

    // POS Manager
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_head_OfPOS")
    private User headOfPOS;

    @ManyToOne
    @JoinColumn(name = "sector_id")
    @JsonIgnoreProperties({"posInSector"})
    private Sector sector;

    @OneToMany(mappedBy = "pos")
    private Set<User> users = new HashSet<>();

    // Audit fields
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime creation_Date;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt_Date;

    public POS(Integer id, String pos_Name, String codeOfPOS, User headOfPOS, Sector sector, Set<User> users, LocalDateTime creation_Date, LocalDateTime updatedAt_Date) {
        this.id = id;
        this.posName = pos_Name;
        this.codeOfPOS = codeOfPOS;
        this.headOfPOS = headOfPOS;
        this.sector = sector;
        this.users = users;
        this.creation_Date = creation_Date;
        this.updatedAt_Date = updatedAt_Date;
    }

    public POS() {
    }

    public static POSBuilder builder() {
        return new POSBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosName() {
        return this.posName;
    }

    public void setPosName(String pos_Name) {
        this.posName = pos_Name;
    }

    public String getCodeOfPOS() {
        return this.codeOfPOS;
    }

    public void setCodeOfPOS(String code_Of_POS) {
        this.codeOfPOS = code_Of_POS;
    }

    public User getHeadOfPOS() {
        return this.headOfPOS;
    }

    public void setHeadOfPOS(User headOfPOS) {
        this.headOfPOS = headOfPOS;
    }

    public Sector getSector() {
        return this.sector;
    }

    @JsonIgnoreProperties({"posInSector"})
    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Set<User> getUsers() {
        return this.users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
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

    public static class POSBuilder {
        private Integer id;
        private String pos_Name;
        private String code_Of_POS;
        private User headOfPOS;
        private Sector sector;
        private Set<User> users;
        private LocalDateTime creation_Date;
        private LocalDateTime updatedAt_Date;

        POSBuilder() {
        }

        public POSBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public POSBuilder pos_Name(String pos_Name) {
            this.pos_Name = pos_Name;
            return this;
        }

        public POSBuilder code_Of_POS(String code_Of_POS) {
            this.code_Of_POS = code_Of_POS;
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

        public POSBuilder creation_Date(LocalDateTime creation_Date) {
            this.creation_Date = creation_Date;
            return this;
        }

        public POSBuilder updatedAt_Date(LocalDateTime updatedAt_Date) {
            this.updatedAt_Date = updatedAt_Date;
            return this;
        }

        public POS build() {
            return new POS(this.id, this.pos_Name, this.code_Of_POS, this.headOfPOS, this.sector, this.users, this.creation_Date, this.updatedAt_Date);
        }

        public String toString() {
            return "POS.POSBuilder(id=" + this.id + ", pos_Name=" + this.pos_Name + ", code_Of_POS=" + this.code_Of_POS + ", headOfPOS=" + this.headOfPOS + ", sector=" + this.sector + ", users=" + this.users + ", creation_Date=" + this.creation_Date + ", updatedAt_Date=" + this.updatedAt_Date + ")";
        }
    }
}
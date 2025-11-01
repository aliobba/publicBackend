package com.ooredoo.report_builder.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ooredoo.report_builder.entity.Enterprise;
import com.ooredoo.report_builder.entity.Form;
import com.ooredoo.report_builder.entity.FormSubmission;
import com.ooredoo.report_builder.entity.POS;
import com.ooredoo.report_builder.enums.UserType;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "_user")
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails, Principal {

    @Id
    @GeneratedValue
    private Integer id;
    private String first_Name;
    private String last_Name;
    private LocalDate birthdate;


    private String password;
    @Column(unique = true)
    private String email;

    // hashed PIN value (store BCrypt or Argon2 hash)
    @Column(name = "pin")
    private String pin;
    private boolean enabled;
    private boolean accountLocked;
    private String pos_Code;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "creator")
    @JsonIgnore
    private Set<Form> createdForms;

    @OneToMany(mappedBy = "submittedBy")
    @JsonIgnore
    private List<FormSubmission> submissions;

    //for the entity listeners
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime created_Date;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updated_Date;

    // Optionally user can belong at different hierarchy levels
    // A user can be directly assigned to an enterprise/sector/zone/region
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enterprise_id")
    @JsonIgnoreProperties({"users", "manager"})
    private Enterprise enterprise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pos_id")
    @JsonIgnoreProperties({"users"})
    private POS pos;

    // Forms directly assigned to user (many-to-many)
    @ManyToMany(mappedBy = "assignedUsers", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Form> assignedForms = new HashSet<>();

    // User type (POS or regular user)
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;

    public User(Integer id, String first_Name, String last_Name, LocalDate birthdate, String password, String email, String pin, boolean enabled, boolean accountLocked, String pos_Code, Role role, Set<Form> createdForms, List<FormSubmission> submissions, LocalDateTime created_Date, LocalDateTime updated_Date, Enterprise enterprise, POS pos, Set<Form> assignedForms, UserType userType) {
        this.id = id;
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.birthdate = birthdate;
        this.password = password;
        this.email = email;
        this.pin = pin;
        this.enabled = enabled;
        this.accountLocked = accountLocked;
        this.pos_Code = pos_Code;
        this.role = role;
        this.createdForms = createdForms;
        this.submissions = submissions;
        this.created_Date = created_Date;
        this.updated_Date = updated_Date;
        this.enterprise = enterprise;
        this.pos = pos;
        this.assignedForms = assignedForms;
        this.userType = userType;
    }

    public User() {
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }


    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role != null && this.role.getName() != null && !this.role.getName().trim().isEmpty()) {
            return List.of(new SimpleGrantedAuthority(this.role.getName()));
        }
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String getName() {
        return email;
    }

    public String fullName() {
        return first_Name + " " + last_Name;
    }

    public boolean hasRole(String roleName) {
        return role != null && role.getName().equals(roleName);
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_Name() {
        return this.first_Name;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public String getLast_Name() {
        return this.last_Name;
    }

    public void setLast_Name(String last_Name) {
        this.last_Name = last_Name;
    }

    public LocalDate getBirthdate() {
        return this.birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPin() {
        return this.pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public boolean isAccountLocked() {
        return this.accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public String getPos_Code() {
        return this.pos_Code;
    }

    public void setPos_Code(String pos_Code) {
        this.pos_Code = pos_Code;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Form> getCreatedForms() {
        return this.createdForms;
    }

    @JsonIgnore
    public void setCreatedForms(Set<Form> createdForms) {
        this.createdForms = createdForms;
    }

    public List<FormSubmission> getSubmissions() {
        return this.submissions;
    }

    @JsonIgnore
    public void setSubmissions(List<FormSubmission> submissions) {
        this.submissions = submissions;
    }

    public LocalDateTime getCreated_Date() {
        return this.created_Date;
    }

    public void setCreated_Date(LocalDateTime created_Date) {
        this.created_Date = created_Date;
    }

    public LocalDateTime getUpdated_Date() {
        return this.updated_Date;
    }

    public void setUpdated_Date(LocalDateTime updated_Date) {
        this.updated_Date = updated_Date;
    }

    public Enterprise getEnterprise() {
        return this.enterprise;
    }

    @JsonIgnoreProperties({"users", "manager"})
    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public POS getPos() {
        return this.pos;
    }

    @JsonIgnoreProperties({"users"})
    public void setPos(POS pos) {
        this.pos = pos;
    }

    public Set<Form> getAssignedForms() {
        return this.assignedForms;
    }

    @JsonIgnore
    public void setAssignedForms(Set<Form> assignedForms) {
        this.assignedForms = assignedForms;
    }

    public UserType getUserType() {
        return this.userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public static class UserBuilder {
        private Integer id;
        private String first_Name;
        private String last_Name;
        private LocalDate birthdate;
        private String password;
        private String email;
        private String pin;
        private boolean enabled;
        private boolean accountLocked;
        private String pos_Code;
        private Role role;
        private Set<Form> createdForms;
        private List<FormSubmission> submissions;
        private LocalDateTime created_Date;
        private LocalDateTime updated_Date;
        private Enterprise enterprise;
        private POS pos;
        private Set<Form> assignedForms;
        private UserType userType;

        UserBuilder() {
        }

        public UserBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public UserBuilder first_Name(String first_Name) {
            this.first_Name = first_Name;
            return this;
        }

        public UserBuilder last_Name(String last_Name) {
            this.last_Name = last_Name;
            return this;
        }

        public UserBuilder birthdate(LocalDate birthdate) {
            this.birthdate = birthdate;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder pin(String pin) {
            this.pin = pin;
            return this;
        }

        public UserBuilder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public UserBuilder accountLocked(boolean accountLocked) {
            this.accountLocked = accountLocked;
            return this;
        }

        public UserBuilder pos_Code(String pos_Code) {
            this.pos_Code = pos_Code;
            return this;
        }

        public UserBuilder role(Role role) {
            this.role = role;
            return this;
        }

        @JsonIgnore
        public UserBuilder createdForms(Set<Form> createdForms) {
            this.createdForms = createdForms;
            return this;
        }

        @JsonIgnore
        public UserBuilder submissions(List<FormSubmission> submissions) {
            this.submissions = submissions;
            return this;
        }

        public UserBuilder created_Date(LocalDateTime created_Date) {
            this.created_Date = created_Date;
            return this;
        }

        public UserBuilder updated_Date(LocalDateTime updated_Date) {
            this.updated_Date = updated_Date;
            return this;
        }

        @JsonIgnoreProperties({"users", "manager"})
        public UserBuilder enterprise(Enterprise enterprise) {
            this.enterprise = enterprise;
            return this;
        }

        @JsonIgnoreProperties({"users"})
        public UserBuilder pos(POS pos) {
            this.pos = pos;
            return this;
        }

        @JsonIgnore
        public UserBuilder assignedForms(Set<Form> assignedForms) {
            this.assignedForms = assignedForms;
            return this;
        }

        public UserBuilder userType(UserType userType) {
            this.userType = userType;
            return this;
        }

        public User build() {
            return new User(this.id, this.first_Name, this.last_Name, this.birthdate, this.password, this.email, this.pin, this.enabled, this.accountLocked, this.pos_Code, this.role, this.createdForms, this.submissions, this.created_Date, this.updated_Date, this.enterprise, this.pos, this.assignedForms, this.userType);
        }

        public String toString() {
            return "User.UserBuilder(id=" + this.id + ", first_Name=" + this.first_Name + ", last_Name=" + this.last_Name + ", birthdate=" + this.birthdate + ", password=" + this.password + ", email=" + this.email + ", pin=" + this.pin + ", enabled=" + this.enabled + ", accountLocked=" + this.accountLocked + ", pos_Code=" + this.pos_Code + ", role=" + this.role + ", createdForms=" + this.createdForms + ", submissions=" + this.submissions + ", created_Date=" + this.created_Date + ", updated_Date=" + this.updated_Date + ", enterprise=" + this.enterprise + ", pos=" + this.pos + ", assignedForms=" + this.assignedForms + ", userType=" + this.userType + ")";
        }
    }
}

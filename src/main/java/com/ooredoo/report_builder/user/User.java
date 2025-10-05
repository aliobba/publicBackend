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
import java.util.stream.Collectors;

@Entity
@Table(name = "_user")
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails, Principal {

    @Id
    @GeneratedValue
    private Integer id;
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;


    private String password;
    @Column(unique = true)
    private String email;

    // hashed PIN value (store BCrypt or Argon2 hash)
    @Column(name = "pin_hash")
    private String pinHash;
    private boolean enabled;
    private boolean accountLocked;
    private String code_POS;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    @OneToMany(mappedBy = "creator")
    @JsonIgnore
    private Set<Form> createdForms;

    @OneToMany(mappedBy = "submittedBy")
    @JsonIgnore
    private List<FormSubmission> submissions;

    //for the entity listeners
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

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


    public User() {
    }

    public User(Integer id, String firstname, String lastname,
                LocalDate dateOfBirth, String password,
                String email, String pinHash, boolean enabled,
                boolean accountLocked, String code_POS, List<Role>
                        roles, Set<Form> createdForms, List<FormSubmission> submissions,
                LocalDateTime createdAt, LocalDateTime updatedAt, Enterprise enterprise, POS pos, Set<Form> assignedForms, UserType userType) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.email = email;
        this.pinHash = pinHash;
        this.enabled = enabled;
        this.accountLocked = accountLocked;
        this.code_POS = code_POS;
        this.roles = roles;
        this.createdForms = createdForms;
        this.submissions = submissions;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.enterprise = enterprise;
        this.pos = pos;
        this.assignedForms = assignedForms;
        this.userType = userType;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
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

    @Override
    public String getName() {
        return email;
    }

    public String fullName() {
        return firstname + " " + lastname;
    }


    public boolean hasRole(String roleName) {
        return roles.stream()
                .anyMatch(role -> role.getName().equals(roleName));
    }


    public Integer getId() {
        return this.id;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPinHash() {
        return this.pinHash;
    }

    public boolean isAccountLocked() {
        return this.accountLocked;
    }

    public List<Role> getRoles() {
        return this.roles;
    }

    public Set<Form> getCreatedForms() {
        return this.createdForms;
    }

    public List<FormSubmission> getSubmissions() {
        return this.submissions;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public Enterprise getEnterprise() {
        return this.enterprise;
    }

    public POS getPos() {
        return this.pos;
    }

    public Set<Form> getAssignedForms() {
        return this.assignedForms;
    }

    public UserType getUserType() {
        return this.userType;
    }

    public String getcode_POS() {
        return code_POS;
    }

    public void setcode_POS(String code_POS) {
        this.code_POS = code_POS;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPinHash(String pinHash) {
        this.pinHash = pinHash;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @JsonIgnore
    public void setCreatedForms(Set<Form> createdForms) {
        this.createdForms = createdForms;
    }

    @JsonIgnore
    public void setSubmissions(List<FormSubmission> submissions) {
        this.submissions = submissions;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    @JsonIgnoreProperties({"users"})
    public void setPos(POS pos) {
        this.pos = pos;
    }

    public void setAssignedForms(Set<Form> assignedForms) {
        this.assignedForms = assignedForms;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public static class UserBuilder {
        private Integer id;
        private String firstname;
        private String lastname;
        private LocalDate dateOfBirth;
        private String password;
        private String email;
        private String pinHash;
        private boolean enabled;
        private boolean accountLocked;
        private String code_POS;
        private List<Role> roles;
        private Set<Form> createdForms;
        private List<FormSubmission> submissions;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
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

        public UserBuilder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public UserBuilder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public UserBuilder dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
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

        public UserBuilder pinHash(String pinHash) {
            this.pinHash = pinHash;
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

        public UserBuilder code_POS(String code_POS) {
            this.code_POS = code_POS;
            return this;
        }

        /*public UserBuilder roles(List<Role> roles) {
            this.roles = roles;
            return this;
        }*/

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

        public UserBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
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
            return new User(this.id, this.firstname, this.lastname, this.dateOfBirth, this.password, this.email, this.pinHash, this.enabled, this.accountLocked, this.code_POS, this.roles, this.createdForms, this.submissions, this.createdAt, this.updatedAt, this.enterprise, this.pos, this.assignedForms, this.userType);
        }

        public String toString() {
            return "User.UserBuilder(id=" + this.id + ", firstname=" + this.firstname + ", lastname=" + this.lastname + ", dateOfBirth=" + this.dateOfBirth + ", password=" + this.password + ", email=" + this.email + ", pinHash=" + this.pinHash + ", enabled=" + this.enabled + ", accountLocked=" + this.accountLocked + ", code_POS=" + this.code_POS + ", createdForms=" + this.createdForms + ", submissions=" + this.submissions + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ", enterprise=" + this.enterprise + ", pos=" + this.pos + ", assignedForms=" + this.assignedForms + ", userType=" + this.userType + ")";
        }
    }
}

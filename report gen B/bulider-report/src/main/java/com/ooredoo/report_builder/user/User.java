package com.ooredoo.report_builder.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ooredoo.report_builder.entity.*;
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
    private Integer id_user;
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    private String password;
    @Column(unique = true)
    private String email;

    private boolean enabled;
    private boolean accountLocked;


    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    @ManyToMany(mappedBy = "assignedUsers")
    @JsonIgnore
    private Set<Form> assignedForms;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;
    
    // New relationships for Enterprise, Sector, Zone, Region, POS
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sector_id")
    private Sector sector;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "zone_id")
    private Zone zone;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "region_id")
    private Region region;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pos_id")
    private POS pos;
    
    // User type (POS or regular user)
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;
    
    // Animators assigned to this user
    @ManyToMany
    @JoinTable(
        name = "user_animators",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "animator_id")
    )
    private Set<Animator> animators = new HashSet<>();

    public User(Integer id_user, String firstname, String lastname, LocalDate dateOfBirth, String password, String email, boolean enabled, boolean accountLocked, List<Role> roles, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id_user = id_user;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.accountLocked = accountLocked;
        this.roles = roles;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public User() {
    }

    public User(Integer id_user, String firstname, String lastname, LocalDate dateOfBirth, String password, String email, boolean enabled, boolean accountLocked, List<Role> roles, Set<Form> assignedForms, Set<Form> createdForms, List<FormSubmission> submissions, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id_user = id_user;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.accountLocked = accountLocked;
        this.roles = roles;
        this.assignedForms = assignedForms;
        this.createdForms = createdForms;
        this.submissions = submissions;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public User(Integer id_user, String firstname, String lastname,
                LocalDate dateOfBirth, String password, String email,
                boolean enabled, boolean accountLocked, List<Role> roles,
                Set<Form> assignedForms, Set<Form> createdForms,
                List<FormSubmission> submissions, LocalDateTime createdAt,
                LocalDateTime updatedAt, Department department) {
        this.id_user = id_user;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.accountLocked = accountLocked;
        this.roles = roles;
        this.assignedForms = assignedForms;
        this.createdForms = createdForms;
        this.submissions = submissions;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.department = department;
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

    public Integer getId_user() {
        return this.id_user;
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

    public boolean isAccountLocked() {
        return this.accountLocked;
    }

    public List<Role> getRoles() {
        return this.roles;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
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

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<Form> getAssignedForms() {
        return this.assignedForms;
    }

    public void setAssignedForms(Set<Form> assignedForms) {
        this.assignedForms = assignedForms;
    }

    public Set<Form> getCreatedForms() {
        return this.createdForms;
    }

    public List<FormSubmission> getSubmissions() {
        return this.submissions;
    }

    @JsonIgnore
    public void setCreatedForms(Set<Form> createdForms) {
        this.createdForms = createdForms;
    }

    @JsonIgnore
    public void setSubmissions(List<FormSubmission> submissions) {
        this.submissions = submissions;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        final User other = (User) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id_user = this.getId_user();
        final Object other$id_user = other.getId_user();
        if (this$id_user == null ? other$id_user != null : !this$id_user.equals(other$id_user)) return false;
        final Object this$firstname = this.getFirstname();
        final Object other$firstname = other.getFirstname();
        if (this$firstname == null ? other$firstname != null : !this$firstname.equals(other$firstname)) return false;
        final Object this$lastname = this.getLastname();
        final Object other$lastname = other.getLastname();
        if (this$lastname == null ? other$lastname != null : !this$lastname.equals(other$lastname)) return false;
        final Object this$dateOfBirth = this.getDateOfBirth();
        final Object other$dateOfBirth = other.getDateOfBirth();
        if (this$dateOfBirth == null ? other$dateOfBirth != null : !this$dateOfBirth.equals(other$dateOfBirth))
            return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        if (this.isEnabled() != other.isEnabled()) return false;
        if (this.isAccountLocked() != other.isAccountLocked()) return false;
        final Object this$roles = this.getRoles();
        final Object other$roles = other.getRoles();
        if (this$roles == null ? other$roles != null : !this$roles.equals(other$roles)) return false;
        final Object this$assignedForms = this.getAssignedForms();
        final Object other$assignedForms = other.getAssignedForms();
        if (this$assignedForms == null ? other$assignedForms != null : !this$assignedForms.equals(other$assignedForms))
            return false;
        final Object this$createdForms = this.getCreatedForms();
        final Object other$createdForms = other.getCreatedForms();
        if (this$createdForms == null ? other$createdForms != null : !this$createdForms.equals(other$createdForms))
            return false;
        final Object this$submissions = this.getSubmissions();
        final Object other$submissions = other.getSubmissions();
        if (this$submissions == null ? other$submissions != null : !this$submissions.equals(other$submissions))
            return false;
        final Object this$createdAt = this.getCreatedAt();
        final Object other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !this$createdAt.equals(other$createdAt)) return false;
        final Object this$updatedAt = this.getUpdatedAt();
        final Object other$updatedAt = other.getUpdatedAt();
        if (this$updatedAt == null ? other$updatedAt != null : !this$updatedAt.equals(other$updatedAt)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof User;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id_user = this.getId_user();
        result = result * PRIME + ($id_user == null ? 43 : $id_user.hashCode());
        final Object $firstname = this.getFirstname();
        result = result * PRIME + ($firstname == null ? 43 : $firstname.hashCode());
        final Object $lastname = this.getLastname();
        result = result * PRIME + ($lastname == null ? 43 : $lastname.hashCode());
        final Object $dateOfBirth = this.getDateOfBirth();
        result = result * PRIME + ($dateOfBirth == null ? 43 : $dateOfBirth.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        result = result * PRIME + (this.isEnabled() ? 79 : 97);
        result = result * PRIME + (this.isAccountLocked() ? 79 : 97);
        final Object $roles = this.getRoles();
        result = result * PRIME + ($roles == null ? 43 : $roles.hashCode());
        final Object $assignedForms = this.getAssignedForms();
        result = result * PRIME + ($assignedForms == null ? 43 : $assignedForms.hashCode());
        final Object $createdForms = this.getCreatedForms();
        result = result * PRIME + ($createdForms == null ? 43 : $createdForms.hashCode());
        final Object $submissions = this.getSubmissions();
        result = result * PRIME + ($submissions == null ? 43 : $submissions.hashCode());
        final Object $createdAt = this.getCreatedAt();
        result = result * PRIME + ($createdAt == null ? 43 : $createdAt.hashCode());
        final Object $updatedAt = this.getUpdatedAt();
        result = result * PRIME + ($updatedAt == null ? 43 : $updatedAt.hashCode());
        return result;
    }

    public String toString() {
        return "User(id_user=" + this.getId_user() + ", firstname=" + this.getFirstname() + ", lastname=" + this.getLastname() + ", dateOfBirth=" + this.getDateOfBirth() + ", password=" + this.getPassword() + ", email=" + this.getEmail() + ", enabled=" + this.isEnabled() + ", accountLocked=" + this.isAccountLocked() ;
    }

    public static class UserBuilder {
        private Integer id_user;
        private String firstname;
        private String lastname;
        private LocalDate dateOfBirth;
        private String password;
        private String email;
        private boolean enabled;
        private boolean accountLocked;
        private List<Role> roles;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private Set<Form> assignedForms;
        private Set<Form> createdForms;
        private List<FormSubmission> submissions;
        private Department department;
        private Enterprise enterprise;
        private Sector sector;
        private Zone zone;
        private Region region;
        private POS pos;
        private UserType userType;
        private Set<Animator> animators = new HashSet<>();

        UserBuilder() {
        }

        public UserBuilder id_user(Integer id_user) {
            this.id_user = id_user;
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

        public UserBuilder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public UserBuilder accountLocked(boolean accountLocked) {
            this.accountLocked = accountLocked;
            return this;
        }

        public UserBuilder roles(List<Role> roles) {
            this.roles = roles;
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

        public User build() {
            User user = new User(this.id_user, this.firstname, this.lastname, this.dateOfBirth, this.password, this.email, this.enabled, this.accountLocked, this.roles, this.createdAt, this.updatedAt);
            user.setDepartment(this.department);
            user.setEnterprise(this.enterprise);
            user.setSector(this.sector);
            user.setZone(this.zone);
            user.setRegion(this.region);
            user.setPos(this.pos);
            user.setUserType(this.userType);
            user.setAnimators(this.animators);
            user.setAssignedForms(this.assignedForms);
            user.setCreatedForms(this.createdForms);
            user.setSubmissions(this.submissions);
            return user;
        }

        public String toString() {
            return "User.UserBuilder(id_user=" + this.id_user + ", firstname=" + this.firstname + ", lastname=" + this.lastname + ", dateOfBirth=" + this.dateOfBirth + ", password=" + this.password + ", email=" + this.email + ", enabled=" + this.enabled + ", accountLocked=" + this.accountLocked + ", roles=" + this.roles + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ")";
        }
        
        public UserBuilder department(Department department) {
            this.department = department;
            return this;
        }
        
        public UserBuilder enterprise(Enterprise enterprise) {
            this.enterprise = enterprise;
            return this;
        }
        
        public UserBuilder sector(Sector sector) {
            this.sector = sector;
            return this;
        }
        
        public UserBuilder zone(Zone zone) {
            this.zone = zone;
            return this;
        }
        
        public UserBuilder region(Region region) {
            this.region = region;
            return this;
        }
        
        public UserBuilder pos(POS pos) {
            this.pos = pos;
            return this;
        }
        
        public UserBuilder userType(UserType userType) {
            this.userType = userType;
            return this;
        }
        
        public UserBuilder animators(Set<Animator> animators) {
            this.animators = animators;
            return this;
        }

        public UserBuilder assignedForms(Set<Form> assignedForms) {
            this.assignedForms = assignedForms;
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
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    
    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public POS getPos() {
        return pos;
    }

    public void setPos(POS pos) {
        this.pos = pos;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Set<Animator> getAnimators() {
        return animators;
    }

    public void setAnimators(Set<Animator> animators) {
        this.animators = animators;
    }
    
    public boolean hasRole(String roleName) {
        return roles.stream()
                .anyMatch(role -> role.getName().equals(roleName));
    }
}

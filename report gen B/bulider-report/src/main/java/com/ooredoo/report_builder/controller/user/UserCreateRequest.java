package com.ooredoo.report_builder.controller.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.ooredoo.report_builder.enums.UserType;

import java.time.LocalDate;
import java.util.List;

public class UserCreateRequest {

    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    @Email(message = "Email is not well formatted")
    @NotEmpty(message = "Email is mandatory")
    @NotNull(message = "Email is mandatory")
    private String email;

    @NotEmpty(message = "Password is mandatory")
    @NotNull(message = "Password is mandatory")
    @Size(min = 8, message = "Password should be 8 characters long minimum")
    private String password;
    private Integer enterpriseId;
    private Integer sectorId;
    private Integer zoneId;
    private Integer regionId;
    private Integer posId;
    private UserType userType;
    private List<String> roles;

    UserCreateRequest(String firstname, String lastname, LocalDate dateOfBirth, 
                    @Email(message = "Email is not well formatted") 
                    @NotEmpty(message = "Email is mandatory") 
                    @NotNull(message = "Email is mandatory") String email, 
                    @NotEmpty(message = "Password is mandatory") 
                    @NotNull(message = "Password is mandatory") 
                    @Size(min = 8, message = "Password should be 8 characters long minimum") String password, 
                    Integer enterpriseId, Integer sectorId, Integer zoneId, Integer regionId, 
                    Integer posId, UserType userType, List<String> roles) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.enterpriseId = enterpriseId;
        this.sectorId = sectorId;
        this.zoneId = zoneId;
        this.regionId = regionId;
        this.posId = posId;
        this.userType = userType;
        this.roles = roles;
    }

    public static UserCreateRequestBuilder builder() {
        return new UserCreateRequestBuilder();
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

    public @Email(message = "Email is not well formatted") @NotEmpty(message = "Email is mandatory") @NotNull(message = "Email is mandatory") String getEmail() {
        return this.email;
    }

    public @NotEmpty(message = "Password is mandatory") @NotNull(message = "Password is mandatory") @Size(min = 8, message = "Password should be 8 characters long minimum") String getPassword() {
        return this.password;
    }

    public Integer getEnterpriseId() {
        return this.enterpriseId;
    }
    
    public Integer getSectorId() {
        return this.sectorId;
    }
    
    public Integer getZoneId() {
        return this.zoneId;
    }
    
    public Integer getRegionId() {
        return this.regionId;
    }
    
    public Integer getPosId() {
        return this.posId;
    }
    
    public UserType getUserType() {
        return this.userType;
    }

    public List<String> getRoles() {
        return this.roles;
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

    public void setEmail(@Email(message = "Email is not well formatted") @NotEmpty(message = "Email is mandatory") @NotNull(message = "Email is mandatory") String email) {
        this.email = email;
    }

    public void setPassword(@NotEmpty(message = "Password is mandatory") @NotNull(message = "Password is mandatory") @Size(min = 8, message = "Password should be 8 characters long minimum") String password) {
        this.password = password;
    }
    
    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
    
    public void setSectorId(Integer sectorId) {
        this.sectorId = sectorId;
    }
    
    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }
    
    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }
    
    public void setPosId(Integer posId) {
        this.posId = posId;
    }
    
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public static class UserCreateRequestBuilder {
        private String firstname;
        private String lastname;
        private LocalDate dateOfBirth;
        private @Email(message = "Email is not well formatted")
        @NotEmpty(message = "Email is mandatory")
        @NotNull(message = "Email is mandatory") String email;
        private @NotEmpty(message = "Password is mandatory")
        @NotNull(message = "Password is mandatory")
        @Size(min = 8, message = "Password should be 8 characters long minimum") String password;
        private Integer enterpriseId;
        private Integer sectorId;
        private Integer zoneId;
        private Integer regionId;
        private Integer posId;
        private UserType userType;
        private List<String> roles;

        UserCreateRequestBuilder() {
        }

        public UserCreateRequestBuilder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public UserCreateRequestBuilder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public UserCreateRequestBuilder dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public UserCreateRequestBuilder email(@Email(message = "Email is not well formatted") @NotEmpty(message = "Email is mandatory") @NotNull(message = "Email is mandatory") String email) {
            this.email = email;
            return this;
        }

        public UserCreateRequestBuilder password(@NotEmpty(message = "Password is mandatory") @NotNull(message = "Password is mandatory") @Size(min = 8, message = "Password should be 8 characters long minimum") String password) {
            this.password = password;
            return this;
        }

        public UserCreateRequestBuilder enterpriseId(Integer enterpriseId) {
            this.enterpriseId = enterpriseId;
            return this;
        }
        
        public UserCreateRequestBuilder sectorId(Integer sectorId) {
            this.sectorId = sectorId;
            return this;
        }
        
        public UserCreateRequestBuilder zoneId(Integer zoneId) {
            this.zoneId = zoneId;
            return this;
        }
        
        public UserCreateRequestBuilder regionId(Integer regionId) {
            this.regionId = regionId;
            return this;
        }
        
        public UserCreateRequestBuilder posId(Integer posId) {
            this.posId = posId;
            return this;
        }
        
        public UserCreateRequestBuilder userType(UserType userType) {
            this.userType = userType;
            return this;
        }

        public UserCreateRequestBuilder roles(List<String> roles) {
            this.roles = roles;
            return this;
        }

        public UserCreateRequest build() {
            return new UserCreateRequest(this.firstname, this.lastname, this.dateOfBirth, this.email, 
                                        this.password, this.enterpriseId, this.sectorId, this.zoneId, 
                                        this.regionId, this.posId, this.userType, this.roles);
        }

        public String toString() {
            return "UserCreateRequest.UserCreateRequestBuilder(firstname=" + this.firstname + ", lastname=" + this.lastname + ", dateOfBirth=" + this.dateOfBirth + ", email=" + this.email + ", password=" + this.password + ", enterpriseId=" + this.enterpriseId + ", sectorId=" + this.sectorId + ", zoneId=" + this.zoneId + ", regionId=" + this.regionId + ", posId=" + this.posId + ", userType=" + this.userType + ", roles=" + this.roles + ")";
        }
    }
}

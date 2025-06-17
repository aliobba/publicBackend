package com.ooredoo.report_builder.controller.user;

import com.ooredoo.report_builder.enums.UserType;
import java.util.List;

public class UserResponse {
    private Integer id_user;
    private String firstname;
    private String lastname;
    private String email;
    private String enterpriseName;
    private String sectorName;
    private String zoneName;
    private String regionName;
    private String posName;
    private UserType userType;
    private List<String> roles;

    UserResponse(Integer id_user, String firstname, String lastname, String email, 
                String enterpriseName, String sectorName, String zoneName, 
                String regionName, String posName, UserType userType, List<String> roles) {
        this.id_user = id_user;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.enterpriseName = enterpriseName;
        this.sectorName = sectorName;
        this.zoneName = zoneName;
        this.regionName = regionName;
        this.posName = posName;
        this.userType = userType;
        this.roles = roles;
    }

    public static UserResponseBuilder builder() {
        return new UserResponseBuilder();
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

    public String getEmail() {
        return this.email;
    }

    public String getEnterpriseName() {
        return this.enterpriseName;
    }
    
    public String getSectorName() {
        return this.sectorName;
    }
    
    public String getZoneName() {
        return this.zoneName;
    }
    
    public String getRegionName() {
        return this.regionName;
    }
    
    public String getPosName() {
        return this.posName;
    }
    
    public UserType getUserType() {
        return this.userType;
    }

    public List<String> getRoles() {
        return this.roles;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }
    
    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }
    
    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }
    
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
    
    public void setPosName(String posName) {
        this.posName = posName;
    }
    
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public static class UserResponseBuilder {
        private Integer id_user;
        private String firstname;
        private String lastname;
        private String email;
        private String enterpriseName;
        private String sectorName;
        private String zoneName;
        private String regionName;
        private String posName;
        private UserType userType;
        private List<String> roles;

        UserResponseBuilder() {
        }

        public UserResponseBuilder id_user(Integer id_user) {
            this.id_user = id_user;
            return this;
        }

        public UserResponseBuilder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public UserResponseBuilder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public UserResponseBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserResponseBuilder enterpriseName(String enterpriseName) {
            this.enterpriseName = enterpriseName;
            return this;
        }
        
        public UserResponseBuilder sectorName(String sectorName) {
            this.sectorName = sectorName;
            return this;
        }
        
        public UserResponseBuilder zoneName(String zoneName) {
            this.zoneName = zoneName;
            return this;
        }
        
        public UserResponseBuilder regionName(String regionName) {
            this.regionName = regionName;
            return this;
        }
        
        public UserResponseBuilder posName(String posName) {
            this.posName = posName;
            return this;
        }
        
        public UserResponseBuilder userType(UserType userType) {
            this.userType = userType;
            return this;
        }

        public UserResponseBuilder roles(List<String> roles) {
            this.roles = roles;
            return this;
        }

        public UserResponse build() {
            return new UserResponse(this.id_user, this.firstname, this.lastname, this.email, this.enterpriseName, 
                                  this.sectorName, this.zoneName, this.regionName, this.posName, this.userType, this.roles);
        }

        public String toString() {
            return "UserResponse.UserResponseBuilder(id_user=" + this.id_user + ", firstname=" + this.firstname + ", lastname=" + this.lastname + ", email=" + this.email + ", roles=" + this.roles + ")";
        }
    }
}

package com.ooredoo.report_builder.controller.user;

import com.ooredoo.report_builder.enums.UserType;

public class UserUpdateRequest {

    private String firstname;
    private String lastname;
    private Integer enterpriseId;
    private Integer sectorId;
    private Integer zoneId;
    private Integer regionId;
    private Integer posId;
    private UserType userType;

    UserUpdateRequest(String firstname, String lastname, Integer enterpriseId, Integer sectorId, 
                    Integer zoneId, Integer regionId, Integer posId, UserType userType) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.enterpriseId = enterpriseId;
        this.sectorId = sectorId;
        this.zoneId = zoneId;
        this.regionId = regionId;
        this.posId = posId;
        this.userType = userType;
    }

    public static UserUpdateRequestBuilder builder() {
        return new UserUpdateRequestBuilder();
    }

    public String getFirstname() {
        return this.firstname;
    }

    public String getLastname() {
        return this.lastname;
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

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public static class UserUpdateRequestBuilder {
        private String firstname;
        private String lastname;
        private Integer enterpriseId;
        private Integer sectorId;
        private Integer zoneId;
        private Integer regionId;
        private Integer posId;
        private UserType userType;

        UserUpdateRequestBuilder() {
        }

        public UserUpdateRequestBuilder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public UserUpdateRequestBuilder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public UserUpdateRequestBuilder enterpriseId(Integer enterpriseId) {
            this.enterpriseId = enterpriseId;
            return this;
        }
        
        public UserUpdateRequestBuilder sectorId(Integer sectorId) {
            this.sectorId = sectorId;
            return this;
        }
        
        public UserUpdateRequestBuilder zoneId(Integer zoneId) {
            this.zoneId = zoneId;
            return this;
        }
        
        public UserUpdateRequestBuilder regionId(Integer regionId) {
            this.regionId = regionId;
            return this;
        }
        
        public UserUpdateRequestBuilder posId(Integer posId) {
            this.posId = posId;
            return this;
        }
        
        public UserUpdateRequestBuilder userType(UserType userType) {
            this.userType = userType;
            return this;
        }

        public UserUpdateRequest build() {
            return new UserUpdateRequest(this.firstname, this.lastname, this.enterpriseId, 
                                        this.sectorId, this.zoneId, this.regionId, 
                                        this.posId, this.userType);
        }

        public String toString() {
            return "UserUpdateRequest.UserUpdateRequestBuilder(firstname=" + this.firstname + ", lastname=" + this.lastname + ", enterpriseId=" + this.enterpriseId + ", sectorId=" + this.sectorId + ", zoneId=" + this.zoneId + ", regionId=" + this.regionId + ", posId=" + this.posId + ", userType=" + this.userType + ")";
        }
    }
}

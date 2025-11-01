package com.ooredoo.report_builder.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ooredoo.report_builder.enums.UserType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserInfoDTO {
    private Integer id;
    private String first_Name;
    private String last_Name;
    private String email;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdate;
    
    private boolean enabled;
    private boolean accountLocked;
    private String pos_Code;
    private UserType userType;
    private RoleInfoDTO role;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime created_Date;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime updated_Date;

    // Constructors
    public UserInfoDTO() {}

    public UserInfoDTO(Integer id, String first_Name, String last_Name, String email,
                      LocalDate birthdate, boolean enabled, boolean accountLocked,
                      String pos_Code, UserType userType, RoleInfoDTO role,
                      LocalDateTime created_Date, LocalDateTime updated_Date) {
        this.id = id;
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.email = email;
        this.birthdate = birthdate;
        this.enabled = enabled;
        this.accountLocked = accountLocked;
        this.pos_Code = pos_Code;
        this.userType = userType;
        this.role = role;
        this.created_Date = created_Date;
        this.updated_Date = updated_Date;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public String getLast_Name() {
        return last_Name;
    }

    public void setLast_Name(String last_Name) {
        this.last_Name = last_Name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public String getPos_Code() {
        return pos_Code;
    }

    public void setPos_Code(String pos_Code) {
        this.pos_Code = pos_Code;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public RoleInfoDTO getRole() {
        return role;
    }

    public void setRole(RoleInfoDTO role) {
        this.role = role;
    }

    public LocalDateTime getCreated_Date() {
        return created_Date;
    }

    public void setCreated_Date(LocalDateTime created_Date) {
        this.created_Date = created_Date;
    }

    public LocalDateTime getUpdated_Date() {
        return updated_Date;
    }

    public void setUpdated_Date(LocalDateTime updated_Date) {
        this.updated_Date = updated_Date;
    }
}

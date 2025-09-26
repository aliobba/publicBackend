package com.ooredoo.report_builder.controller.auth;


import com.ooredoo.report_builder.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


public class RegistrationRequest {

    @NotEmpty(message = "Firstname is mandatory")
    @NotBlank(message = "Firstname is mandatory")
    private String firstname;
    @NotEmpty(message = "Lastname is mandatory")
    @NotBlank(message = "Lastname is mandatory")
    private String lastname;
    @Size(min = 8, message = "Password should be 8 characters minimum")
    @NotEmpty(message = "password is mandatory")
    @NotBlank(message = "password is mandatory")
    private String password;
    @Email(message = "Email is not Formatted")
    @NotEmpty(message = "email is mandatory")
    @NotBlank(message = "email is mandatory")
    private String email;
    @Size(max = 4, message = "PIN should be 4 Numbers ")
    @NotEmpty(message = "PIN is mandatory")
    @NotBlank(message = "PIN is mandatory")
    private String pinHash;
    private UserType userType;

    public RegistrationRequest() {
    }

    public RegistrationRequest(@NotEmpty(message = "Firstname is mandatory") @NotBlank(message = "Firstname is mandatory") String firstname, @NotEmpty(message = "Lastname is mandatory") @NotBlank(message = "Lastname is mandatory") String lastname, @Size(min = 8, message = "Password should be 8 characters minimum") @NotEmpty(message = "password is mandatory") @NotBlank(message = "password is mandatory") String password, @Email(message = "Email is not Formatted") @NotEmpty(message = "email is mandatory") @NotBlank(message = "email is mandatory") String email, @Size(max = 4, message = "PIN should be 4 Numbers ") @NotEmpty(message = "PIN is mandatory") @NotBlank(message = "PIN is mandatory") String pinHash, UserType userType) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.pinHash = pinHash;
        this.userType = userType;
    }

    public static RegistrationRequestBuilder builder() {
        return new RegistrationRequestBuilder();
    }

    public @NotEmpty(message = "Firstname is mandatory") @NotBlank(message = "Firstname is mandatory") String getFirstname() {
        return this.firstname;
    }

    public @NotEmpty(message = "Lastname is mandatory") @NotBlank(message = "Lastname is mandatory") String getLastname() {
        return this.lastname;
    }

    public @Size(min = 8, message = "Password should be 8 characters minimum") @NotEmpty(message = "password is mandatory") @NotBlank(message = "password is mandatory") String getPassword() {
        return this.password;
    }

    public @Email(message = "Email is not Formatted") @NotEmpty(message = "email is mandatory") @NotBlank(message = "email is mandatory") String getEmail() {
        return this.email;
    }

    public @Size(max = 4, message = "PIN should be 4 Numbers ") @NotEmpty(message = "PIN is mandatory") @NotBlank(message = "PIN is mandatory") String getPinHash() {
        return this.pinHash;
    }

    public UserType getUserType() {
        return this.userType;
    }

    public void setFirstname(@NotEmpty(message = "Firstname is mandatory") @NotBlank(message = "Firstname is mandatory") String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(@NotEmpty(message = "Lastname is mandatory") @NotBlank(message = "Lastname is mandatory") String lastname) {
        this.lastname = lastname;
    }

    public void setPassword(@Size(min = 8, message = "Password should be 8 characters minimum") @NotEmpty(message = "password is mandatory") @NotBlank(message = "password is mandatory") String password) {
        this.password = password;
    }

    public void setEmail(@Email(message = "Email is not Formatted") @NotEmpty(message = "email is mandatory") @NotBlank(message = "email is mandatory") String email) {
        this.email = email;
    }

    public void setPinHash(@Size(max = 4, message = "PIN should be 4 Numbers ") @NotEmpty(message = "PIN is mandatory") @NotBlank(message = "PIN is mandatory") String pinHash) {
        this.pinHash = pinHash;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public static class RegistrationRequestBuilder {
        private @NotEmpty(message = "Firstname is mandatory")
        @NotBlank(message = "Firstname is mandatory") String firstname;
        private @NotEmpty(message = "Lastname is mandatory")
        @NotBlank(message = "Lastname is mandatory") String lastname;
        private @Size(min = 8, message = "Password should be 8 characters minimum")
        @NotEmpty(message = "password is mandatory")
        @NotBlank(message = "password is mandatory") String password;
        private @Email(message = "Email is not Formatted")
        @NotEmpty(message = "email is mandatory")
        @NotBlank(message = "email is mandatory") String email;
        private @Size(max = 4, message = "PIN should be 4 Numbers ")
        @NotEmpty(message = "PIN is mandatory")
        @NotBlank(message = "PIN is mandatory") String pinHash;
        private UserType userType;

        RegistrationRequestBuilder() {
        }

        public RegistrationRequestBuilder firstname(@NotEmpty(message = "Firstname is mandatory") @NotBlank(message = "Firstname is mandatory") String firstname) {
            this.firstname = firstname;
            return this;
        }

        public RegistrationRequestBuilder lastname(@NotEmpty(message = "Lastname is mandatory") @NotBlank(message = "Lastname is mandatory") String lastname) {
            this.lastname = lastname;
            return this;
        }

        public RegistrationRequestBuilder password(@Size(min = 8, message = "Password should be 8 characters minimum") @NotEmpty(message = "password is mandatory") @NotBlank(message = "password is mandatory") String password) {
            this.password = password;
            return this;
        }

        public RegistrationRequestBuilder email(@Email(message = "Email is not Formatted") @NotEmpty(message = "email is mandatory") @NotBlank(message = "email is mandatory") String email) {
            this.email = email;
            return this;
        }

        public RegistrationRequestBuilder pinHash(@Size(max = 4, message = "PIN should be 4 Numbers ") @NotEmpty(message = "PIN is mandatory") @NotBlank(message = "PIN is mandatory") String pinHash) {
            this.pinHash = pinHash;
            return this;
        }

        public RegistrationRequestBuilder userType(UserType userType) {
            this.userType = userType;
            return this;
        }

        public RegistrationRequest build() {
            return new RegistrationRequest(this.firstname, this.lastname, this.password, this.email, this.pinHash, this.userType);
        }

        public String toString() {
            return "RegistrationRequest.RegistrationRequestBuilder(firstname=" + this.firstname + ", lastname=" + this.lastname + ", password=" + this.password + ", email=" + this.email + ", pinHash=" + this.pinHash + ", userType=" + this.userType + ")";
        }
    }
}

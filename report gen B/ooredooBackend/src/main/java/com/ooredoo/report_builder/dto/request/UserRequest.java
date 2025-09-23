package com.ooredoo.report_builder.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


public class UserRequest {

    @NotEmpty(message = "Firstname is mandatory")
    @NotNull(message = "Firstname is mandatory")
    private String firstname;
    @NotEmpty(message = "Lastname is mandatory")
    @NotNull(message = "Lastname is mandatory")
    private String lastname;
    @Email(message = "Email is not well formatted")
    @NotEmpty(message = "Email is mandatory")
    @NotNull(message = "Email is mandatory")
    private String email;
    @NotEmpty(message = "Password is mandatory")
    @NotNull(message = "Password is mandatory")
    @Size(min = 8, message = "Password should be 8 characters long minimum")
    private String password;
    private Set<Integer> animatorIds; // IDs of linked animators
    private LocalDate dateOfBirth;
    private List<Integer> roleIds;   // references Role IDs

    public UserRequest(@NotEmpty(message = "Firstname is mandatory") @NotNull(message = "Firstname is mandatory") String firstname, @NotEmpty(message = "Lastname is mandatory") @NotNull(message = "Lastname is mandatory") String lastname, @Email(message = "Email is not well formatted") @NotEmpty(message = "Email is mandatory") @NotNull(message = "Email is mandatory") String email, @NotEmpty(message = "Password is mandatory") @NotNull(message = "Password is mandatory") @Size(min = 8, message = "Password should be 8 characters long minimum") String password, Set<Integer> animatorIds, LocalDate dateOfBirth, List<Integer> roleIds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.animatorIds = animatorIds;
        this.dateOfBirth = dateOfBirth;
        this.roleIds = roleIds;
    }

    public UserRequest() {
    }

    public static UserRequestBuilder builder() {
        return new UserRequestBuilder();
    }

    public @NotEmpty(message = "Firstname is mandatory") @NotNull(message = "Firstname is mandatory") String getFirstname() {
        return this.firstname;
    }

    public @NotEmpty(message = "Lastname is mandatory") @NotNull(message = "Lastname is mandatory") String getLastname() {
        return this.lastname;
    }

    public @Email(message = "Email is not well formatted") @NotEmpty(message = "Email is mandatory") @NotNull(message = "Email is mandatory") String getEmail() {
        return this.email;
    }

    public @NotEmpty(message = "Password is mandatory") @NotNull(message = "Password is mandatory") @Size(min = 8, message = "Password should be 8 characters long minimum") String getPassword() {
        return this.password;
    }

    public Set<Integer> getAnimatorIds() {
        return this.animatorIds;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public List<Integer> getRoleIds() {
        return this.roleIds;
    }

    public void setFirstname(@NotEmpty(message = "Firstname is mandatory") @NotNull(message = "Firstname is mandatory") String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(@NotEmpty(message = "Lastname is mandatory") @NotNull(message = "Lastname is mandatory") String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(@Email(message = "Email is not well formatted") @NotEmpty(message = "Email is mandatory") @NotNull(message = "Email is mandatory") String email) {
        this.email = email;
    }

    public void setPassword(@NotEmpty(message = "Password is mandatory") @NotNull(message = "Password is mandatory") @Size(min = 8, message = "Password should be 8 characters long minimum") String password) {
        this.password = password;
    }

    public void setAnimatorIds(Set<Integer> animatorIds) {
        this.animatorIds = animatorIds;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }

    public static class UserRequestBuilder {
        private @NotEmpty(message = "Firstname is mandatory")
        @NotNull(message = "Firstname is mandatory") String firstname;
        private @NotEmpty(message = "Lastname is mandatory")
        @NotNull(message = "Lastname is mandatory") String lastname;
        private @Email(message = "Email is not well formatted")
        @NotEmpty(message = "Email is mandatory")
        @NotNull(message = "Email is mandatory") String email;
        private @NotEmpty(message = "Password is mandatory")
        @NotNull(message = "Password is mandatory")
        @Size(min = 8, message = "Password should be 8 characters long minimum") String password;
        private Set<Integer> animatorIds;
        private LocalDate dateOfBirth;
        private List<Integer> roleIds;

        UserRequestBuilder() {
        }

        public UserRequestBuilder firstname(@NotEmpty(message = "Firstname is mandatory") @NotNull(message = "Firstname is mandatory") String firstname) {
            this.firstname = firstname;
            return this;
        }

        public UserRequestBuilder lastname(@NotEmpty(message = "Lastname is mandatory") @NotNull(message = "Lastname is mandatory") String lastname) {
            this.lastname = lastname;
            return this;
        }

        public UserRequestBuilder email(@Email(message = "Email is not well formatted") @NotEmpty(message = "Email is mandatory") @NotNull(message = "Email is mandatory") String email) {
            this.email = email;
            return this;
        }

        public UserRequestBuilder password(@NotEmpty(message = "Password is mandatory") @NotNull(message = "Password is mandatory") @Size(min = 8, message = "Password should be 8 characters long minimum") String password) {
            this.password = password;
            return this;
        }

        public UserRequestBuilder animatorIds(Set<Integer> animatorIds) {
            this.animatorIds = animatorIds;
            return this;
        }

        public UserRequestBuilder dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public UserRequestBuilder roleIds(List<Integer> roleIds) {
            this.roleIds = roleIds;
            return this;
        }

        public UserRequest build() {
            return new UserRequest(this.firstname, this.lastname, this.email, this.password, this.animatorIds, this.dateOfBirth, this.roleIds);
        }

        public String toString() {
            return "UserRequest.UserRequestBuilder(firstname=" + this.firstname + ", lastname=" + this.lastname + ", email=" + this.email + ", password=" + this.password + ", animatorIds=" + this.animatorIds + ", dateOfBirth=" + this.dateOfBirth + ", roleIds=" + this.roleIds + ")";
        }
    }
}


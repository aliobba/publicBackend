package com.ooredoo.report_builder.dto.response;

public class UserSummaryDTO {
    private Integer id;
    private String username;
    private String fullName;

    public UserSummaryDTO(Integer id, String username, String fullName) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
    }

    public UserSummaryDTO() {
    }

    public static UserSummaryDTOBuilder builder() {
        return new UserSummaryDTOBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public static class UserSummaryDTOBuilder {
        private Integer id;
        private String username;
        private String fullName;

        UserSummaryDTOBuilder() {
        }

        public UserSummaryDTOBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public UserSummaryDTOBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserSummaryDTOBuilder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public UserSummaryDTO build() {
            return new UserSummaryDTO(this.id, this.username, this.fullName);
        }

        public String toString() {
            return "UserSummaryDTO.UserSummaryDTOBuilder(id=" + this.id + ", username=" + this.username + ", fullName=" + this.fullName + ")";
        }
    }
}

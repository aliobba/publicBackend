package com.ooredoo.report_builder.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class RoleInfoDTO {
    private Integer id;
    private String name;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime created_Date;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime updated_Date;

    // Constructors
    public RoleInfoDTO() {}

    public RoleInfoDTO(Integer id, String name, LocalDateTime created_Date, LocalDateTime updated_Date) {
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

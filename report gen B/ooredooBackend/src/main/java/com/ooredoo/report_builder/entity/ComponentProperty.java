package com.ooredoo.report_builder.entity;


import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "component_properties")
public class ComponentProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String propertyName;

    private String propertyValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "component_id", nullable = false)
    private FormComponent component;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    // Constructors
    public ComponentProperty() {
    }

    public ComponentProperty(String propertyName, String propertyValue, FormComponent component) {
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
        this.component = component;
    }


    public Integer getId() {
        return this.id;
    }

    public String getPropertyName() {
        return this.propertyName;
    }

    public String getPropertyValue() {
        return this.propertyValue;
    }

    public FormComponent getComponent() {
        return this.component;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    public void setComponent(FormComponent component) {
        this.component = component;
    }
}

package com.ooredoo.report_builder.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "component_propertie")
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

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPropertyName() {
        return this.propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyValue() {
        return this.propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    public FormComponent getComponent() {
        return this.component;
    }

    public void setComponent(FormComponent component) {
        this.component = component;
    }
}

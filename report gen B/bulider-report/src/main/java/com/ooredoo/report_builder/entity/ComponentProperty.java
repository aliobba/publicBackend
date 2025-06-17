package com.ooredoo.report_builder.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "component_properties")
public class ComponentProperty {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String propertyName;
    private String propertyValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "component_id")
    private FormComponent component;

    public ComponentProperty(Integer id, String propertyName, String propertyValue, FormComponent component) {
        this.id = id;
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
        this.component = component;
    }

    public ComponentProperty() {
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

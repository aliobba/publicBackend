package com.ooredoo.report_builder.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "form_component_assignment",
        uniqueConstraints = @UniqueConstraint(columnNames = {"form_id", "component_id"}))
@EntityListeners(AuditingEntityListener.class)
public class FormComponentAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id", nullable = false)
    private Form form;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "component_id", nullable = false)
    private FormComponent component;

    @Column(name = "order_index")
    private Integer orderIndex;

    // Form-specific fields
    @Column(name = "label")
    private String label;

    @Column(name = "required")
    private Boolean required = false;

    @Column(name = "placeholder")
    private String placeholder;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime assigned_Date;

    @Column(name = "unassigned_date")
    private LocalDateTime unassigned_Date;

    // Constructors

    public FormComponentAssignment(Form form, FormComponent component, Integer orderIndex, String label, Boolean required, String placeholder) {
        this.form = form;
        this.component = component;
        this.orderIndex = orderIndex;
        this.label = label != null ? label : component.getComponentName();
        this.required = required != null ? required : false;
        this.placeholder = placeholder;
        this.isActive = true;
    }

    public FormComponentAssignment(Integer id, Form form, FormComponent component, Integer orderIndex, String label, Boolean required, String placeholder, Boolean isActive, LocalDateTime assigned_Date, LocalDateTime unassigned_Date) {
        this.id = id;
        this.form = form;
        this.component = component;
        this.orderIndex = orderIndex;
        this.label = label;
        this.required = required;
        this.placeholder = placeholder;
        this.isActive = isActive;
        this.assigned_Date = assigned_Date;
        this.unassigned_Date = unassigned_Date;
    }

    public FormComponentAssignment() {
    }

    public static FormComponentAssignmentBuilder builder() {
        return new FormComponentAssignmentBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Form getForm() {
        return this.form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public FormComponent getComponent() {
        return this.component;
    }

    public void setComponent(FormComponent component) {
        this.component = component;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getRequired() {
        return this.required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public String getPlaceholder() {
        return this.placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public Integer getOrderIndex() {
        return this.orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getAssigned_Date() {
        return this.assigned_Date;
    }

    public void setAssigned_Date(LocalDateTime assigned_Date) {
        this.assigned_Date = assigned_Date;
    }

    public LocalDateTime getUnassigned_Date() {
        return this.unassigned_Date;
    }

    public void setUnassigned_Date(LocalDateTime unassigned_Date) {
        this.unassigned_Date = unassigned_Date;
    }

    public static class FormComponentAssignmentBuilder {
        private Integer id;
        private Form form;
        private FormComponent component;
        private String label;
        private Boolean required;
        private Integer orderIndex;
        private String placeholder;
        private Boolean isActive;
        private LocalDateTime assigned_Date;
        private LocalDateTime unassigned_Date;

        FormComponentAssignmentBuilder() {
        }

        public FormComponentAssignmentBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public FormComponentAssignmentBuilder form(Form form) {
            this.form = form;
            return this;
        }

        public FormComponentAssignmentBuilder component(FormComponent component) {
            this.component = component;
            return this;
        }

        public FormComponentAssignmentBuilder label(String label) {
            this.label = label;
            return this;
        }

        public FormComponentAssignmentBuilder required(Boolean required) {
            this.required = required;
            return this;
        }

        public FormComponentAssignmentBuilder placeholder(String placeholder) {
            this.placeholder = placeholder;
            return this;
        }

        public FormComponentAssignmentBuilder orderIndex(Integer orderIndex) {
            this.orderIndex = orderIndex;
            return this;
        }

        public FormComponentAssignmentBuilder isActive(Boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public FormComponentAssignmentBuilder assigned_Date(LocalDateTime assigned_Date) {
            this.assigned_Date = assigned_Date;
            return this;
        }

        public FormComponentAssignmentBuilder unassigned_Date(LocalDateTime unassigned_Date) {
            this.unassigned_Date = unassigned_Date;
            return this;
        }

        public FormComponentAssignment build() {
            return new FormComponentAssignment(this.id, this.form, this.component, this.orderIndex, this.label, this.required, this.placeholder, this.isActive, this.assigned_Date, this.unassigned_Date);
        }

        public String toString() {
            return "FormComponentAssignment.FormComponentAssignmentBuilder(id=" + this.id + ", form=" + this.form + ", component=" + this.component + ", orderIndex=" + this.orderIndex + ", label=" + this.label + ", required=" + this.required + ", placeholder=" + this.placeholder + ", isActive=" + this.isActive + ", assigned_Date=" + this.assigned_Date + ", unassigned_Date=" + this.unassigned_Date + ")";
        }
    }
}
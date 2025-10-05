package com.ooredoo.report_builder.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "form_component_assignments",
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

    @Column(name = "is_active")
    private Boolean isActive = true;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime assignedAt;

    @Column(name = "unassigned_at")
    private LocalDateTime unassignedAt;

    // Constructors


    public FormComponentAssignment(Form form, FormComponent component, Integer orderIndex) {
        this.form = form;
        this.component = component;
        this.orderIndex = orderIndex;
        this.isActive = true;
    }


    public FormComponentAssignment(Integer id, Form form, FormComponent component, Integer orderIndex, Boolean isActive, LocalDateTime assignedAt, LocalDateTime unassignedAt) {
        this.id = id;
        this.form = form;
        this.component = component;
        this.orderIndex = orderIndex;
        this.isActive = isActive;
        this.assignedAt = assignedAt;
        this.unassignedAt = unassignedAt;
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

    public LocalDateTime getAssignedAt() {
        return this.assignedAt;
    }

    public void setAssignedAt(LocalDateTime assignedAt) {
        this.assignedAt = assignedAt;
    }

    public LocalDateTime getUnassignedAt() {
        return this.unassignedAt;
    }

    public void setUnassignedAt(LocalDateTime unassignedAt) {
        this.unassignedAt = unassignedAt;
    }

    public void unassign() {
        this.isActive = false;
        this.unassignedAt = LocalDateTime.now();
    }

    public static class FormComponentAssignmentBuilder {
        private Integer id;
        private Form form;
        private FormComponent component;
        private Integer orderIndex;
        private Boolean isActive;
        private LocalDateTime assignedAt;
        private LocalDateTime unassignedAt;

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

        public FormComponentAssignmentBuilder orderIndex(Integer orderIndex) {
            this.orderIndex = orderIndex;
            return this;
        }

        public FormComponentAssignmentBuilder isActive(Boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public FormComponentAssignmentBuilder assignedAt(LocalDateTime assignedAt) {
            this.assignedAt = assignedAt;
            return this;
        }

        public FormComponentAssignmentBuilder unassignedAt(LocalDateTime unassignedAt) {
            this.unassignedAt = unassignedAt;
            return this;
        }

        public FormComponentAssignment build() {
            return new FormComponentAssignment(this.id, this.form, this.component, this.orderIndex, this.isActive, this.assignedAt, this.unassignedAt);
        }

        public String toString() {
            return "FormComponentAssignment.FormComponentAssignmentBuilder(id=" + this.id + ", form=" + this.form + ", component=" + this.component + ", orderIndex=" + this.orderIndex + ", isActive=" + this.isActive + ", assignedAt=" + this.assignedAt + ", unassignedAt=" + this.unassignedAt + ")";
        }
    }
}

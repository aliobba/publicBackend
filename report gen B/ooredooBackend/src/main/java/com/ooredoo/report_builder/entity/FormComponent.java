package com.ooredoo.report_builder.entity;

import com.ooredoo.report_builder.enums.ComponentType;
import com.ooredoo.report_builder.user.User;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "form_components")
@EntityListeners(AuditingEntityListener.class)
public class FormComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ComponentType elementType;

    @Column(nullable = false)
    private String label;

    @Column(nullable = false)
    private Boolean required = false;

    @Column(name = "order_index")
    private Integer orderIndex = 0;

    // Global component that can be reused
    @Column(name = "is_global")
    private Boolean isGlobal = false;

    // Original creator of the component
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    // ManyToMany for reusability across forms
    @ManyToMany(mappedBy = "components")
    private Set<Form> forms = new HashSet<>();

    @OneToMany(mappedBy = "component", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComponentProperty> properties = new ArrayList<>();

    @OneToMany(mappedBy = "component", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ElementOption> options = new ArrayList<>();

    // Form-specific component assignments
    @OneToMany(mappedBy = "component", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FormComponentAssignment> formAssignments = new ArrayList<>();

    public FormComponent(ComponentType elementType, String label, Boolean required, User createdBy) {
        this.elementType = elementType;
        this.label = label;
        this.required = required;
        this.createdBy = createdBy;
    }

    public FormComponent(Integer id, ComponentType elementType, String label, Boolean required, Integer orderIndex, Boolean isGlobal, User createdBy, LocalDateTime createdAt, LocalDateTime updatedAt, Set<Form> forms, List<ComponentProperty> properties, List<ElementOption> options, List<FormComponentAssignment> formAssignments) {
        this.id = id;
        this.elementType = elementType;
        this.label = label;
        this.required = required;
        this.orderIndex = orderIndex;
        this.isGlobal = isGlobal;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.forms = forms;
        this.properties = properties;
        this.options = options;
        this.formAssignments = formAssignments;
    }

    public FormComponent() {
    }

    public static FormComponentBuilder builder() {
        return new FormComponentBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public ComponentType getElementType() {
        return this.elementType;
    }

    public String getLabel() {
        return this.label;
    }

    public Boolean getRequired() {
        return this.required;
    }

    public void setElementType(ComponentType elementType) {
        this.elementType = elementType;
    }

    public Integer getOrderIndex() {
        return this.orderIndex;
    }

    public Boolean getIsGlobal() {
        return this.isGlobal;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setIsGlobal(Boolean isGlobal) {
        this.isGlobal = isGlobal;
    }

    public List<ComponentProperty> getProperties() {
        return this.properties;
    }

    public List<ElementOption> getOptions() {
        return this.options;
    }

    public User getCreatedBy() {
        return this.createdBy;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Set<Form> getForms() {
        return this.forms;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public void setForms(Set<Form> forms) {
        this.forms = forms;
    }

    public List<FormComponentAssignment> getFormAssignments() {
        return this.formAssignments;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setFormAssignments(List<FormComponentAssignment> formAssignments) {
        this.formAssignments = formAssignments;
    }

    public void setProperties(List<ComponentProperty> properties) {
        this.properties = properties;
    }

    public void setOptions(List<ElementOption> options) {
        this.options = options;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public static class FormComponentBuilder {
        private Integer id;
        private ComponentType elementType;
        private String label;
        private Boolean required;
        private Integer orderIndex;
        private Boolean isGlobal;
        private User createdBy;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private Set<Form> forms;
        private List<ComponentProperty> properties;
        private List<ElementOption> options;
        private List<FormComponentAssignment> formAssignments;

        FormComponentBuilder() {
        }

        public FormComponentBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public FormComponentBuilder elementType(ComponentType elementType) {
            this.elementType = elementType;
            return this;
        }

        public FormComponentBuilder label(String label) {
            this.label = label;
            return this;
        }

        public FormComponentBuilder required(Boolean required) {
            this.required = required;
            return this;
        }

        public FormComponentBuilder orderIndex(Integer orderIndex) {
            this.orderIndex = orderIndex;
            return this;
        }

        public FormComponentBuilder isGlobal(Boolean isGlobal) {
            this.isGlobal = isGlobal;
            return this;
        }

        public FormComponentBuilder createdBy(User createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public FormComponentBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public FormComponentBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public FormComponentBuilder forms(Set<Form> forms) {
            this.forms = forms;
            return this;
        }

        public FormComponentBuilder properties(List<ComponentProperty> properties) {
            this.properties = properties;
            return this;
        }

        public FormComponentBuilder options(List<ElementOption> options) {
            this.options = options;
            return this;
        }

        public FormComponentBuilder formAssignments(List<FormComponentAssignment> formAssignments) {
            this.formAssignments = formAssignments;
            return this;
        }

        public FormComponent build() {
            return new FormComponent(this.id, this.elementType, this.label, this.required, this.orderIndex, this.isGlobal, this.createdBy, this.createdAt, this.updatedAt, this.forms, this.properties, this.options, this.formAssignments);
        }

        public String toString() {
            return "FormComponent.FormComponentBuilder(id=" + this.id + ", elementType=" + this.elementType + ", label=" + this.label + ", required=" + this.required + ", orderIndex=" + this.orderIndex + ", isGlobal=" + this.isGlobal + ", createdBy=" + this.createdBy + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ", forms=" + this.forms + ", properties=" + this.properties + ", options=" + this.options + ", formAssignments=" + this.formAssignments + ")";
        }
    }
}

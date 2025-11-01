package com.ooredoo.report_builder.entity;

import com.ooredoo.report_builder.enums.ComponentType;
import com.ooredoo.report_builder.user.User;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "form_component")
@EntityListeners(AuditingEntityListener.class)
public class FormComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ComponentType elementType;

    // Generic component name for identification (not form-specific)
    @Column(name = "component_name")
    private String componentName;

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
    private LocalDateTime creation_Date;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt_Date;

    // ManyToMany for reusability across forms
   /* @ManyToMany(mappedBy = "components")
    private Set<Form> forms = new HashSet<>();
*/
    @OneToMany(mappedBy = "component", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComponentProperty> properties = new ArrayList<>();

    @OneToMany(mappedBy = "component", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ElementOption> options = new ArrayList<>();

    // Form-specific component assignments
    @OneToMany(mappedBy = "component", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FormComponentAssignment> formAssignments = new ArrayList<>();

    public FormComponent(ComponentType elementType, String componentName, User createdBy) {
        this.elementType = elementType;
        this.componentName = componentName;
        this.createdBy = createdBy;
    }

    public FormComponent(Integer id, ComponentType elementType, String componentName, Integer orderIndex, Boolean isGlobal, User createdBy, LocalDateTime creation_Date, LocalDateTime updatedAt_Date, List<ComponentProperty> properties, List<ElementOption> options, List<FormComponentAssignment> formAssignments) {
        this.id = id;
        this.elementType = elementType;
        this.componentName = componentName;
        this.orderIndex = orderIndex;
        this.isGlobal = isGlobal;
        this.createdBy = createdBy;
        this.creation_Date = creation_Date;
        this.updatedAt_Date = updatedAt_Date;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public ComponentType getElementType() {
        return this.elementType;
    }

    public void setElementType(ComponentType elementType) {
        this.elementType = elementType;
    }

    public String getComponentName() {
        return this.componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public Integer getOrderIndex() {
        return this.orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public Boolean getIsGlobal() {
        return this.isGlobal;
    }

    public void setIsGlobal(Boolean isGlobal) {
        this.isGlobal = isGlobal;
    }

    public User getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreation_Date() {
        return this.creation_Date;
    }

    public void setCreation_Date(LocalDateTime creation_Date) {
        this.creation_Date = creation_Date;
    }

    public LocalDateTime getUpdatedAt_Date() {
        return this.updatedAt_Date;
    }

    public void setUpdatedAt_Date(LocalDateTime updatedAt_Date) {
        this.updatedAt_Date = updatedAt_Date;
    }

    /*public Set<Form> getForms() {
        return this.forms;
    }

    public void setForms(Set<Form> forms) {
        this.forms = forms;
    }*/

    public List<ComponentProperty> getProperties() {
        return this.properties;
    }

    public void setProperties(List<ComponentProperty> properties) {
        this.properties = properties;
    }

    public List<ElementOption> getOptions() {
        return this.options;
    }

    public void setOptions(List<ElementOption> options) {
        this.options = options;
    }

    public List<FormComponentAssignment> getFormAssignments() {
        return this.formAssignments;
    }

    public void setFormAssignments(List<FormComponentAssignment> formAssignments) {
        this.formAssignments = formAssignments;
    }

    public static class FormComponentBuilder {
        private Integer id;
        private ComponentType elementType;
        private String componentName;
        private Integer orderIndex;
        private Boolean isGlobal;
        private User createdBy;
        private LocalDateTime creation_Date;
        private LocalDateTime updatedAt_Date;
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

        public FormComponentBuilder componentName(String componentName) {
            this.componentName = componentName;
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

        public FormComponentBuilder creation_Date(LocalDateTime creation_Date) {
            this.creation_Date = creation_Date;
            return this;
        }

        public FormComponentBuilder updatedAt_Date(LocalDateTime updatedAt_Date) {
            this.updatedAt_Date = updatedAt_Date;
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
            return new FormComponent(this.id, this.elementType, this.componentName, this.orderIndex, this.isGlobal, this.createdBy, this.creation_Date, this.updatedAt_Date, this.properties, this.options, this.formAssignments);
        }

        public String toString() {
            return "FormComponent.FormComponentBuilder(id=" + this.id + ", elementType=" + this.elementType + ", componentName=" + this.componentName + ", orderIndex=" + this.orderIndex + ", isGlobal=" + this.isGlobal + ", createdBy=" + this.createdBy + ", creation_Date=" + this.creation_Date + ", updatedAt_Date=" + this.updatedAt_Date + ", properties=" + this.properties + ", options=" + this.options + ", formAssignments=" + this.formAssignments + ")";
        }
    }
}

package com.ooredoo.report_builder.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "form_components")
@EntityListeners(AuditingEntityListener.class)
public class FormComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String elementType; // TEXT, NUMBER, DROPDOWN, etc.
    private String label;
    private Integer orderIndex;
    private Boolean required = false;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id")
    private Form form;

    @OneToMany(mappedBy = "component", cascade = CascadeType.ALL)
    private List<ComponentProperty> properties;

    @OneToMany(mappedBy = "component", cascade = CascadeType.ALL)
    private List<ElementOption> options;


    public FormComponent(Integer id, String elementType, String label, Integer orderIndex, Boolean required, Form form, List<ComponentProperty> properties) {
        this.id = id;
        this.elementType = elementType;
        this.label = label;
        this.orderIndex = orderIndex;
        this.required = required;
        this.form = form;
        this.properties = properties;
    }

    public FormComponent() {
    }

    protected FormComponent(FormComponentBuilder<?, ?> b) {
        this.id = b.id;
        this.elementType = b.elementType;
        this.label = b.label;
        this.orderIndex = b.orderIndex;
        this.required = b.required;
        this.form = b.form;
        this.properties = b.properties;
    }

    public FormComponent(Integer id, String elementType, String label, Integer orderIndex, Boolean required, LocalDateTime createdAt, LocalDateTime updatedAt, Form form, List<ComponentProperty> properties, List<ElementOption> options) {
        this.id = id;
        this.elementType = elementType;
        this.label = label;

        this.orderIndex = orderIndex;
        this.required = required;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.form = form;
        this.properties = properties;
        this.options = options;
    }

    public static FormComponentBuilder<?, ?> builder() {
        return new FormComponentBuilderImpl();
    }

    public Integer getId() {
        return this.id;
    }

    public String getElementType() {
        return this.elementType;
    }

    public String getLabel() {
        return this.label;
    }



    public Integer getOrderIndex() {
        return this.orderIndex;
    }

    public Boolean getRequired() {
        return this.required;
    }

    public Form getForm() {
        return this.form;
    }

    public List<ComponentProperty> getProperties() {
        return this.properties;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public void setProperties(List<ComponentProperty> properties) {
        this.properties = properties;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public List<ElementOption> getOptions() {
        return this.options;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setOptions(List<ElementOption> options) {
        this.options = options;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof FormComponent)) return false;
        final FormComponent other = (FormComponent) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$elementType = this.getElementType();
        final Object other$elementType = other.getElementType();
        if (this$elementType == null ? other$elementType != null : !this$elementType.equals(other$elementType))
            return false;
        final Object this$label = this.getLabel();
        final Object other$label = other.getLabel();
        if (this$label == null ? other$label != null : !this$label.equals(other$label)) return false;

        final Object this$orderIndex = this.getOrderIndex();
        final Object other$orderIndex = other.getOrderIndex();
        if (this$orderIndex == null ? other$orderIndex != null : !this$orderIndex.equals(other$orderIndex))
            return false;
        final Object this$required = this.getRequired();
        final Object other$required = other.getRequired();
        if (this$required == null ? other$required != null : !this$required.equals(other$required)) return false;
        final Object this$createdAt = this.getCreatedAt();
        final Object other$createdAt = other.getCreatedAt();
        if (this$createdAt == null ? other$createdAt != null : !this$createdAt.equals(other$createdAt)) return false;
        final Object this$updatedAt = this.getUpdatedAt();
        final Object other$updatedAt = other.getUpdatedAt();
        if (this$updatedAt == null ? other$updatedAt != null : !this$updatedAt.equals(other$updatedAt)) return false;
        final Object this$form = this.getForm();
        final Object other$form = other.getForm();
        if (this$form == null ? other$form != null : !this$form.equals(other$form)) return false;
        final Object this$properties = this.getProperties();
        final Object other$properties = other.getProperties();
        if (this$properties == null ? other$properties != null : !this$properties.equals(other$properties))
            return false;
        final Object this$options = this.getOptions();
        final Object other$options = other.getOptions();
        if (this$options == null ? other$options != null : !this$options.equals(other$options)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof FormComponent;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $elementType = this.getElementType();
        result = result * PRIME + ($elementType == null ? 43 : $elementType.hashCode());
        final Object $label = this.getLabel();
        result = result * PRIME + ($label == null ? 43 : $label.hashCode());
        final Object $orderIndex = this.getOrderIndex();
        result = result * PRIME + ($orderIndex == null ? 43 : $orderIndex.hashCode());
        final Object $required = this.getRequired();
        result = result * PRIME + ($required == null ? 43 : $required.hashCode());
        final Object $createdAt = this.getCreatedAt();
        result = result * PRIME + ($createdAt == null ? 43 : $createdAt.hashCode());
        final Object $updatedAt = this.getUpdatedAt();
        result = result * PRIME + ($updatedAt == null ? 43 : $updatedAt.hashCode());
        final Object $form = this.getForm();
        result = result * PRIME + ($form == null ? 43 : $form.hashCode());
        final Object $properties = this.getProperties();
        result = result * PRIME + ($properties == null ? 43 : $properties.hashCode());
        final Object $options = this.getOptions();
        result = result * PRIME + ($options == null ? 43 : $options.hashCode());
        return result;
    }

    public String toString() {
        return "FormComponent(id=" + this.getId() + ", elementType=" + this.getElementType() + ", label=" + this.getLabel() +  ", orderIndex=" + this.getOrderIndex() + ", required=" + this.getRequired() + ", createdAt=" + this.getCreatedAt() + ", updatedAt=" + this.getUpdatedAt() + ", form=" + this.getForm() + ", properties=" + this.getProperties() + ", options=" + this.getOptions() + ")";
    }

    public static abstract class FormComponentBuilder<C extends FormComponent, B extends FormComponentBuilder<C, B>> {
        private Integer id;
        private String elementType;
        private String label;
        private Integer positionX;
        private Integer positionY;
        private Integer width;
        private Integer height;
        private Integer orderIndex;
        private Boolean required;
        private Form form;
        private List<ComponentProperty> properties;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private List<ElementOption> options;

        FormComponentBuilder() {
        }

        public B id(Integer id) {
            this.id = id;
            return self();
        }

        public B elementType(String elementType) {
            this.elementType = elementType;
            return self();
        }

        public B label(String label) {
            this.label = label;
            return self();
        }

        public B positionX(Integer positionX) {
            this.positionX = positionX;
            return self();
        }

        public B positionY(Integer positionY) {
            this.positionY = positionY;
            return self();
        }

        public B width(Integer width) {
            this.width = width;
            return self();
        }

        public B height(Integer height) {
            this.height = height;
            return self();
        }

        public B orderIndex(Integer orderIndex) {
            this.orderIndex = orderIndex;
            return self();
        }

        public B required(Boolean required) {
            this.required = required;
            return self();
        }

        public B form(Form form) {
            this.form = form;
            return self();
        }

        public B properties(List<ComponentProperty> properties) {
            this.properties = properties;
            return self();
        }

        protected abstract B self();

        public abstract C build();

        public String toString() {
            return "FormComponent.FormComponentBuilder(id=" + this.id + ", elementType=" + this.elementType + ", label=" + this.label  + ", orderIndex=" + this.orderIndex + ", required=" + this.required + ", form=" + this.form + ", properties=" + this.properties + ")";
        }

        public FormComponentBuilder<C, B> createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public FormComponentBuilder<C, B> updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public FormComponentBuilder<C, B> options(List<ElementOption> options) {
            this.options = options;
            return this;
        }
    }

    private static final class FormComponentBuilderImpl extends FormComponentBuilder<FormComponent, FormComponentBuilderImpl> {
        private FormComponentBuilderImpl() {
        }

        protected FormComponentBuilderImpl self() {
            return this;
        }

        public FormComponent build() {
            return new FormComponent(this);
        }
    }
}

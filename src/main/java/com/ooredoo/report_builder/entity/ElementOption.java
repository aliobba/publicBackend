package com.ooredoo.report_builder.entity;


import jakarta.persistence.*;

@Entity
public class ElementOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String label;
    private String value;
    private Integer displayOrder;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "component_id", nullable = false)
    private FormComponent component;


    public ElementOption(Integer id, String label, String value, Integer displayOrder, FormComponent component) {
        this.id = id;
        this.label = label;
        this.value = value;
        this.displayOrder = displayOrder;
        this.component = component;
    }

    public ElementOption() {
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ElementOption)) return false;
        final ElementOption other = (ElementOption) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$label = this.getLabel();
        final Object other$label = other.getLabel();
        if (this$label == null ? other$label != null : !this$label.equals(other$label)) return false;
        final Object this$value = this.getValue();
        final Object other$value = other.getValue();
        if (this$value == null ? other$value != null : !this$value.equals(other$value)) return false;
        final Object this$displayOrder = this.getDisplayOrder();
        final Object other$displayOrder = other.getDisplayOrder();
        if (this$displayOrder == null ? other$displayOrder != null : !this$displayOrder.equals(other$displayOrder))
            return false;
        final Object this$component = this.getComponent();
        final Object other$component = other.getComponent();
        if (this$component == null ? other$component != null : !this$component.equals(other$component)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ElementOption;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $label = this.getLabel();
        result = result * PRIME + ($label == null ? 43 : $label.hashCode());
        final Object $value = this.getValue();
        result = result * PRIME + ($value == null ? 43 : $value.hashCode());
        final Object $displayOrder = this.getDisplayOrder();
        result = result * PRIME + ($displayOrder == null ? 43 : $displayOrder.hashCode());
        final Object $component = this.getComponent();
        result = result * PRIME + ($component == null ? 43 : $component.hashCode());
        return result;
    }

    public String toString() {
        return "ElementOption(id=" + this.getId() + ", label=" + this.getLabel() + ", value=" + this.getValue() + ", displayOrder=" + this.getDisplayOrder() + ", component=" + this.getComponent() + ")";
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getDisplayOrder() {
        return this.displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public FormComponent getComponent() {
        return this.component;
    }

    public void setComponent(FormComponent component) {
        this.component = component;
    }
}

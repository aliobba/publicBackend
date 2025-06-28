package com.ooredoo.report_builder.dto.response;

public class RoleActionSummaryDTO {
    private Integer id;
    private String name;
    private String actionType;

    public RoleActionSummaryDTO() {
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getActionType() {
        return this.actionType;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof RoleActionSummaryDTO)) return false;
        final RoleActionSummaryDTO other = (RoleActionSummaryDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$actionType = this.getActionType();
        final Object other$actionType = other.getActionType();
        if (this$actionType == null ? other$actionType != null : !this$actionType.equals(other$actionType))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof RoleActionSummaryDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $actionType = this.getActionType();
        result = result * PRIME + ($actionType == null ? 43 : $actionType.hashCode());
        return result;
    }

    public String toString() {
        return "RoleActionSummaryDTO(id=" + this.getId() + ", name=" + this.getName() + ", actionType=" + this.getActionType() + ")";
    }
}

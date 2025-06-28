package com.ooredoo.report_builder.dto.response;

public class AnimatorSummaryDTO {
    private Integer id;
    private String pin;

    public AnimatorSummaryDTO() {
    }

    public Integer getId() {
        return this.id;
    }

    public String getPin() {
        return this.pin;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof AnimatorSummaryDTO)) return false;
        final AnimatorSummaryDTO other = (AnimatorSummaryDTO) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$pin = this.getPin();
        final Object other$pin = other.getPin();
        if (this$pin == null ? other$pin != null : !this$pin.equals(other$pin)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AnimatorSummaryDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $pin = this.getPin();
        result = result * PRIME + ($pin == null ? 43 : $pin.hashCode());
        return result;
    }

    public String toString() {
        return "AnimatorSummaryDTO(id=" + this.getId() + ", pin=" + this.getPin() + ")";
    }
}

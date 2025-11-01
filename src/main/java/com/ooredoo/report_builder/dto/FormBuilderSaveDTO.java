package com.ooredoo.report_builder.dto;

import java.util.List;

public class FormBuilderSaveDTO {
    private FormRequestDTO formUpdate;
    private List<Integer> componentOrder;
    private List<FormComponentDTO> newComponents;
    private List<Integer> removedComponentIds;
    private List<FormComponentAssignmentDTO> assignmentUpdates;

    public FormBuilderSaveDTO(FormRequestDTO formUpdate, List<Integer> componentOrder, List<FormComponentDTO> newComponents, List<Integer> removedComponentIds, List<FormComponentAssignmentDTO> assignmentUpdates) {
        this.formUpdate = formUpdate;
        this.componentOrder = componentOrder;
        this.newComponents = newComponents;
        this.removedComponentIds = removedComponentIds;
        this.assignmentUpdates = assignmentUpdates;
    }

    public FormBuilderSaveDTO() {
    }

    public FormRequestDTO getFormUpdate() {
        return this.formUpdate;
    }

    public void setFormUpdate(FormRequestDTO formUpdate) {
        this.formUpdate = formUpdate;
    }

    public List<Integer> getComponentOrder() {
        return this.componentOrder;
    }

    public void setComponentOrder(List<Integer> componentOrder) {
        this.componentOrder = componentOrder;
    }

    public List<FormComponentDTO> getNewComponents() {
        return this.newComponents;
    }

    public void setNewComponents(List<FormComponentDTO> newComponents) {
        this.newComponents = newComponents;
    }

    public List<Integer> getRemovedComponentIds() {
        return this.removedComponentIds;
    }

    public void setRemovedComponentIds(List<Integer> removedComponentIds) {
        this.removedComponentIds = removedComponentIds;
    }

    public List<FormComponentAssignmentDTO> getAssignmentUpdates() {
        return this.assignmentUpdates;
    }

    public void setAssignmentUpdates(List<FormComponentAssignmentDTO> assignmentUpdates) {
        this.assignmentUpdates = assignmentUpdates;
    }
}

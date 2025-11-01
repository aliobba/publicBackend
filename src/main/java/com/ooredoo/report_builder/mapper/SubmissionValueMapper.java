package com.ooredoo.report_builder.mapper;


import com.ooredoo.report_builder.dto.SubmissionValueDTO;
import com.ooredoo.report_builder.entity.FormComponent;
import com.ooredoo.report_builder.entity.FormComponentAssignment;
import com.ooredoo.report_builder.entity.SubmissionValue;
import org.springframework.stereotype.Component;

@Component
public class SubmissionValueMapper {

    public SubmissionValueDTO toSubmissionValueDTO(SubmissionValue entity) {
        if (entity == null) return null;

        SubmissionValueDTO dto = new SubmissionValueDTO();
        dto.setId(entity.getId());
        dto.setValue(entity.getValue());
        dto.setSubmissionId(entity.getSubmission() != null ? entity.getSubmission().getId() : null);

        FormComponentAssignment assignment = entity.getAssignment();
        if (assignment != null) {
            dto.setAssignmentId(assignment.getId());
            dto.setOrderIndex(assignment.getOrderIndex());

            FormComponent component = assignment.getComponent();
            if (component != null) {
                dto.setComponentId(component.getId());
                dto.setComponentLabel(assignment.getLabel());
                dto.setComponentType(component.getElementType().getValue());
            }
        }

        return dto;
    }
}

package com.ooredoo.report_builder.mapper;


import com.ooredoo.report_builder.dto.FormSubmissionResponseDTO;
import com.ooredoo.report_builder.dto.SubmissionValueDTO;
import com.ooredoo.report_builder.entity.FormComponent;
import com.ooredoo.report_builder.entity.FormComponentAssignment;
import com.ooredoo.report_builder.entity.FormSubmission;
import com.ooredoo.report_builder.entity.SubmissionValue;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FormSubmissionMapper {

    public FormSubmissionResponseDTO toFormSubmissionResponseDTO(
            FormSubmission submission,
            List<SubmissionValue> values) {

        FormSubmissionResponseDTO dto = new FormSubmissionResponseDTO();
        dto.setId(submission.getId());
        dto.setFormId(submission.getForm().getId());
        dto.setFormName(submission.getForm().getName_Form());
        dto.setSubmittedById(submission.getSubmittedBy().getId());
        dto.setSubmittedByName(submission.getSubmittedBy().getFirst_Name() + " " +
                submission.getSubmittedBy().getLast_Name());
        dto.setSubmittedAt(submission.getSubmittedDate());

        // Map values (no lazy loading because we pre-fetched)
        List<SubmissionValueDTO> valueDTOs = values.stream()
                .map(this::toSubmissionValueResponseDTO)
                .collect(Collectors.toList());

        dto.setValues(valueDTOs);

        return dto;
    }

    private SubmissionValueDTO toSubmissionValueResponseDTO(SubmissionValue value) {
        SubmissionValueDTO dto = new SubmissionValueDTO();
        dto.setId(value.getId());
        dto.setValue(value.getValue());
        dto.setAssignmentId(value.getAssignment().getId());

        FormComponent component = value.getAssignment().getComponent();
        dto.setComponentId(component.getId());
        dto.setComponentLabel(value.getAssignment().getLabel());
        dto.setComponentType(component.getElementType().getValue());

        return dto;
    }

    public FormSubmissionResponseDTO toFormSubmissionResponseDTO(FormSubmission entity) {
        if (entity == null) return null;

        FormSubmissionResponseDTO dto = new FormSubmissionResponseDTO();
        dto.setId(entity.getId());
        dto.setFormId(entity.getForm().getId());
        dto.setFormName(entity.getForm().getName_Form());
        dto.setSubmittedById(entity.getSubmittedBy().getId());
        dto.setSubmittedByName(entity.getSubmittedBy().getFirst_Name() + " " + entity.getSubmittedBy().getLast_Name());
        dto.setSubmittedAt(entity.getSubmittedDate());
        dto.setIsComplete(entity.getIsComplete());

        // FIXED: Convert submission values with assignment info
        if (entity.getValues() != null) {
            List<SubmissionValueDTO> valueDTOs = entity.getValues().stream()
                    .map(this::toSubmissionValueDTO)
                    .sorted((a, b) -> Integer.compare(a.getOrderIndex(), b.getOrderIndex()))
                    .collect(Collectors.toList());
            dto.setValues(valueDTOs);
        }

        return dto;
    }

    private SubmissionValueDTO toSubmissionValueDTO(SubmissionValue value) {
        if (value == null) return null;

        SubmissionValueDTO dto = new SubmissionValueDTO();
        dto.setId(value.getId());
        dto.setValue(value.getValue());
        dto.setSubmissionId(value.getSubmission().getId());

        // FIXED: Include assignment info
        FormComponentAssignment assignment = value.getAssignment();
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

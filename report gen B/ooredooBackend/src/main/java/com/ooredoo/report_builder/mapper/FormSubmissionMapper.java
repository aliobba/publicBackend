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
        dto.setFormName(submission.getForm().getName());
        dto.setSubmittedById(submission.getSubmittedBy().getId());
        dto.setSubmittedByName(submission.getSubmittedBy().getFirstname() + " " +
                submission.getSubmittedBy().getLastname());
        dto.setSubmittedAt(submission.getSubmittedAt());

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
        dto.setComponentLabel(component.getLabel());
        dto.setComponentType(component.getElementType().getValue());

        return dto;
    }

    public FormSubmissionResponseDTO toFormSubmissionResponseDTO(FormSubmission entity) {
        if (entity == null) return null;

        FormSubmissionResponseDTO dto = new FormSubmissionResponseDTO();
        dto.setId(entity.getId());
        dto.setFormId(entity.getForm().getId());
        dto.setFormName(entity.getForm().getName());
        dto.setSubmittedById(entity.getSubmittedBy().getId());
        dto.setSubmittedByName(entity.getSubmittedBy().getFirstname() + " " + entity.getSubmittedBy().getLastname());
        dto.setSubmittedAt(entity.getSubmittedAt());
        dto.setIsComplete(entity.getComplete());

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
                dto.setComponentLabel(component.getLabel());
                dto.setComponentType(component.getElementType().getValue());
            }
        }

        return dto;
    }

}

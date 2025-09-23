package com.ooredoo.report_builder.mapper;


import com.ooredoo.report_builder.entity.FormComponent;
import com.ooredoo.report_builder.entity.FormSubmission;
import com.ooredoo.report_builder.entity.SubmissionValue;
import com.ooredoo.report_builder.dto.SubmissionValueDTO;
import com.ooredoo.report_builder.repo.FormComponentRepository;
import com.ooredoo.report_builder.repo.FormSubmissionRepository;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SubmissionValueMapper {

    @Mapping(target = "componentId", expression = "java(value.getComponent() != null ? value.getComponent().getId() : null)")
    @Mapping(target = "submissionId", expression = "java(value.getSubmission() != null ? value.getSubmission().getId() : null)")
    SubmissionValueDTO toSubmissionValueDTO(SubmissionValue value);

    @Mapping(target = "submission", ignore = true) // Ignore the submission field in automatic mapping
    @Mapping(target = "component", ignore = true) // Ignore the component field in automatic mapping
    SubmissionValue toSubmissionValue(SubmissionValueDTO submissionValueDTO);

    @AfterMapping
    default void setSubmissionAndComponent(@MappingTarget SubmissionValue submissionValue, SubmissionValueDTO submissionValueDTO,
                                           @Context FormSubmissionRepository formSubmissionRepository,
                                           @Context FormComponentRepository formComponentRepository) {
        if (submissionValueDTO.getSubmissionId() != null) {
            FormSubmission submission = formSubmissionRepository.findById(submissionValueDTO.getSubmissionId())
                    .orElseThrow(() -> new RuntimeException("FormSubmission not found"));
            submissionValue.setSubmission(submission);
        }
        if (submissionValueDTO.getComponentId() != null) {
            FormComponent component = formComponentRepository.findById(submissionValueDTO.getComponentId())
                    .orElseThrow(() -> new RuntimeException("FormComponent not found"));
            submissionValue.setComponent(component);
        }
    }
}

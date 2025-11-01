package com.ooredoo.report_builder.services;


import com.ooredoo.report_builder.dto.SubmissionValueDTO;
import com.ooredoo.report_builder.entity.FormComponentAssignment;
import com.ooredoo.report_builder.entity.FormSubmission;
import com.ooredoo.report_builder.entity.SubmissionValue;
import com.ooredoo.report_builder.mapper.SubmissionValueMapper;
import com.ooredoo.report_builder.repo.FormComponentAssignmentRepository;
import com.ooredoo.report_builder.repo.FormComponentRepository;
import com.ooredoo.report_builder.repo.FormSubmissionRepository;
import com.ooredoo.report_builder.repo.SubmissionValueRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SubmissionValueService {
    private final SubmissionValueRepository valueRepository;
    private final FormSubmissionRepository submissionRepository;
    private final FormComponentRepository componentRepository;
    private final SubmissionValueMapper valueMapper;
    private final FormComponentAssignmentRepository assignmentRepository;


    public SubmissionValueService(SubmissionValueRepository valueRepository,
                                  FormSubmissionRepository submissionRepository,
                                  FormComponentRepository componentRepository,
                                  SubmissionValueMapper valueMapper, FormComponentAssignmentRepository assignmentRepository) {
        this.valueRepository = valueRepository;
        this.submissionRepository = submissionRepository;
        this.componentRepository = componentRepository;
        this.valueMapper = valueMapper;
        this.assignmentRepository = assignmentRepository;
    }

    // Get all submission values
    public List<SubmissionValueDTO> getAllValues() {
        List<SubmissionValue> values = valueRepository.findAll();
        return values.stream()
                .map(valueMapper::toSubmissionValueDTO)
                .collect(Collectors.toList());
    }

    // Get value by ID
    public Optional<SubmissionValueDTO> getValueById(Integer id) {
        return valueRepository.findById(id)
                .map(valueMapper::toSubmissionValueDTO);
    }

    // Create a new value
    @Transactional
    public SubmissionValueDTO createValue(SubmissionValueDTO valueDTO, Integer submissionId, Integer assignmentId) {
        FormSubmission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("Submission not found"));


        FormComponentAssignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Component not found"));

        // Direct mapping from DTO to entity
        SubmissionValue value = new SubmissionValue();
        value.setValue(valueDTO.getValue());
        value.setSubmission(submission);
        value.setAssignment(assignment);


        SubmissionValue savedValue = valueRepository.save(value);
        return valueMapper.toSubmissionValueDTO(savedValue);
    }

    // Update an existing value
    @Transactional
    public SubmissionValueDTO updateValue(Integer id, String inputValue) {
        SubmissionValue value = valueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Value not found"));

        value.setValue(inputValue);
        SubmissionValue updatedValue = valueRepository.save(value);
        return valueMapper.toSubmissionValueDTO(updatedValue);
    }

    // Delete a value
    @Transactional
    public void deleteValue(Integer id) {
        valueRepository.deleteById(id);
    }

    // Batch create values
    @Transactional
    public List<SubmissionValueDTO> batchCreateValues(List<SubmissionValueDTO> valueDTOs, Integer submissionId) {
        FormSubmission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("Submission not found"));

        List<SubmissionValue> values = new ArrayList<>();
        for (SubmissionValueDTO valueDTO : valueDTOs) {
            //FormComponent component = componentRepository.findById(valueDTO.getComponentId())
            //  .orElseThrow(() -> new RuntimeException("Component not found"));
            FormComponentAssignment assignment = assignmentRepository.findById(valueDTO.getAssignmentId())
                    .orElseThrow(() -> new RuntimeException("Component not found"));

            // Direct mapping from DTO to entity
            SubmissionValue value = new SubmissionValue();
            value.setValue(valueDTO.getValue());
            value.setSubmission(submission);
            value.setAssignment(assignment);
            values.add(value);
        }

        List<SubmissionValue> savedValues = valueRepository.saveAll(values);
        return savedValues.stream()
                .map(valueMapper::toSubmissionValueDTO)
                .collect(Collectors.toList());
    }


}

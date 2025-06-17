package com.ooredoo.report_builder.dto.request;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class UpdateFormSubmissionRequest {
    @NotNull
    private Integer id;
    private LocalDateTime submittedAt;
    private Integer formId;
    private Integer submittedById;
    private List<SubmissionValueRequest> values;
}
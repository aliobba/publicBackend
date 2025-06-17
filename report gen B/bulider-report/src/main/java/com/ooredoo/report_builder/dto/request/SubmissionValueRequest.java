package com.ooredoo.report_builder.dto.request;

import lombok.Data;

@Data
public class SubmissionValueRequest {
    private Integer id;
    private String value;
    private Integer componentId;
}
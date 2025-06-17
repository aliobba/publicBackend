package com.ooredoo.report_builder.dto.request;

import lombok.Data;

@Data
public class ComponentPropertyRequest {
    private Integer id;
    private String propertyName;
    private String propertyValue;
}
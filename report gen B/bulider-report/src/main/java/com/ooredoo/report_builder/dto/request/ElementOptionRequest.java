package com.ooredoo.report_builder.dto.request;

import lombok.Data;

@Data
public class ElementOptionRequest {
    private Integer id;
    private String label;
    private String value;
    private Integer displayOrder;
}
package com.ooredoo.report_builder.dto.request;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
public class UpdateFormComponentRequest {
    @NotNull
    private Integer id;
    private String elementType;
    private String label;
    private Integer orderIndex;
    private Boolean required;
    private Integer formId;
    private List<ComponentPropertyRequest> properties;
    private List<ElementOptionRequest> options;
}
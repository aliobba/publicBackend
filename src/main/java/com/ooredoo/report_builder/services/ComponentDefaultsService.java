package com.ooredoo.report_builder.services;

import com.ooredoo.report_builder.dto.ElementOptionDTO;
import com.ooredoo.report_builder.enums.ComponentType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class ComponentDefaultsService {

    public Map<String, String> getDefaultProperties(ComponentType componentType) {
        Map<String, String> defaults = new HashMap<>();

        switch (componentType) {
            case TEXT:
                defaults.put("placeholder", "Enter text");
                defaults.put("maxLength", "255");
                defaults.put("minLength", "0");
                defaults.put("pattern", "");
                break;

            case EMAIL:
                defaults.put("placeholder", "Enter email address");
                defaults.put("maxLength", "255");
                defaults.put("pattern", "^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$");
                break;

            case NUMBER:
                defaults.put("placeholder", "Enter number");
                defaults.put("min", "");
                defaults.put("max", "");
                defaults.put("step", "1");
                defaults.put("decimalPlaces", "0");
                break;

            case TEXTAREA:
                defaults.put("placeholder", "Enter text");
                defaults.put("rows", "4");
                defaults.put("cols", "50");
                defaults.put("maxLength", "1000");
                defaults.put("minLength", "0");
                break;

            case DROPDOWN:
                defaults.put("placeholder", "Select an option");
                defaults.put("allowMultiple", "false");
                defaults.put("searchable", "false");
                break;

            case RADIO:
                defaults.put("layout", "vertical"); // vertical or horizontal
                defaults.put("defaultValue", "");
                break;

            case CHECKBOX:
                defaults.put("layout", "vertical"); // vertical or horizontal
                defaults.put("minSelections", "0");
                defaults.put("maxSelections", ""); // empty means unlimited
                break;

            case DATE:
                defaults.put("format", "yyyy-MM-dd");
                defaults.put("placeholder", "Select date");
                defaults.put("minDate", "");
                defaults.put("maxDate", "");
                defaults.put("allowPastDates", "true");
                defaults.put("allowFutureDates", "true");
                break;

            case FILE_UPLOAD:
                defaults.put("allowedFileTypes", "pdf,doc,docx,jpg,jpeg,png");
                defaults.put("maxFileSize", "50MB");
                defaults.put("maxFiles", "1");
                defaults.put("allowedMimeTypes", "application/pdf,image/jpeg,image/png,application/msword");
                defaults.put("dragAndDrop", "true");
                break;

            default:
                break;
        }

        return defaults;
    }

    public List<ElementOptionDTO> getDefaultOptions(ComponentType componentType) {
        List<ElementOptionDTO> defaultOptions = new ArrayList<>();

        switch (componentType) {
            case RADIO:
                defaultOptions.add(new ElementOptionDTO("Option 1", "option1", 1));
                defaultOptions.add(new ElementOptionDTO("Option 2", "option2", 2));
                defaultOptions.add(new ElementOptionDTO("Option 3", "option3", 3));
                break;

            case CHECKBOX:
                defaultOptions.add(new ElementOptionDTO("Choice 1", "choice1", 1));
                defaultOptions.add(new ElementOptionDTO("Choice 2", "choice2", 2));
                defaultOptions.add(new ElementOptionDTO("Choice 3", "choice3", 3));
                break;

            case DROPDOWN:
                defaultOptions.add(new ElementOptionDTO("Please select", "", 0));
                defaultOptions.add(new ElementOptionDTO("Option 1", "option1", 1));
                defaultOptions.add(new ElementOptionDTO("Option 2", "option2", 2));
                break;

            default:
                // No default options for other component types
                break;
        }

        return defaultOptions;
    }

    public boolean isOptionBasedComponent(ComponentType componentType) {
        return componentType == ComponentType.DROPDOWN ||
                componentType == ComponentType.RADIO ||
                componentType == ComponentType.CHECKBOX;
    }

    public boolean requiresFileHandling(ComponentType componentType) {
        return componentType == ComponentType.FILE_UPLOAD;
    }
}

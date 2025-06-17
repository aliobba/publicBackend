package com.ooredoo.report_builder.services;


import com.ooredoo.report_builder.entity.ElementOption;
import com.ooredoo.report_builder.entity.FormComponent;
import com.ooredoo.report_builder.dto.ElementOptionDTO;
import com.ooredoo.report_builder.mapper.ElementOptionMapper;

import com.ooredoo.report_builder.repo.ElementOptionRepository;
import com.ooredoo.report_builder.repo.FormComponentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ElementOptionService {
    private final ElementOptionRepository optionRepository;
    private final FormComponentRepository componentRepository;
    // Keeping the mapper reference but not using it for direct mapping
    private final ElementOptionMapper elementOptionMapper;
    
    /**
     * Helper method to convert ElementOption entity to ElementOptionDTO
     * Direct mapping implementation to replace mapper usage
     */
    private ElementOptionDTO convertToElementOptionDTO(ElementOption option) {
        if (option == null) return null;
        
        ElementOptionDTO dto = new ElementOptionDTO();
        dto.setId(option.getId());
        dto.setLabel(option.getLabel());
        dto.setValue(option.getValue());
        dto.setDisplayOrder(option.getDisplayOrder());
        
        // Set component ID if component is available
        if (option.getComponent() != null) {
            dto.setComponentId(option.getComponent().getId());
        }
        
        return dto;
    }

    public ElementOptionService(ElementOptionRepository optionRepository, FormComponentRepository componentRepository, ElementOptionMapper elementOptionMapper) {
        this.optionRepository = optionRepository;
        this.componentRepository = componentRepository;
        this.elementOptionMapper = elementOptionMapper;
    }

    // Get all options
    public List<ElementOptionDTO> getAllOptions() {
        List<ElementOption> options = optionRepository.findAll();
        return options.stream()
                .map(this::convertToElementOptionDTO)
                .collect(Collectors.toList());
    }

    // Get option by ID
    public Optional<ElementOptionDTO> getOptionById(Integer id) {
        return optionRepository.findById(id)
                .map(this::convertToElementOptionDTO);
    }

    // Create a new option
    @Transactional
    public ElementOptionDTO createOption(ElementOptionDTO optionDTO, Integer componentId) {
        FormComponent component = componentRepository.findById(componentId)
                .orElseThrow(() -> new RuntimeException("Component not found"));

        // Direct mapping from ElementOptionDTO to ElementOption entity
        ElementOption option = new ElementOption();
        option.setLabel(optionDTO.getLabel());
        option.setValue(optionDTO.getValue());
        option.setComponent(component);

        // Get the highest current display order and add 1
        Integer maxDisplayOrder = optionRepository.findByComponentIdOrderByDisplayOrderAsc(componentId)
                .stream()
                .map(ElementOption::getDisplayOrder)
                .max(Integer::compareTo)
                .orElse(0);

        option.setDisplayOrder(maxDisplayOrder + 1);
        ElementOption savedOption = optionRepository.save(option);
        return convertToElementOptionDTO(savedOption);
    }

    // Update an existing option
    @Transactional
    public ElementOptionDTO updateOption(Integer id, ElementOptionDTO optionDTO) {
        ElementOption option = optionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Option not found"));

        option.setLabel(optionDTO.getLabel());
        option.setValue(optionDTO.getValue());
        ElementOption updatedOption = optionRepository.save(option);
        return convertToElementOptionDTO(updatedOption);
    }

    // Delete an option
    @Transactional
    public void deleteOption(Integer id) {
        optionRepository.deleteById(id);
    }

    // Reorder options
    @Transactional
    public List<ElementOptionDTO> reorderOptions(List<ElementOptionDTO> optionDTOs) {
        List<ElementOption> updatedOptions = new ArrayList<>();
        
        for (ElementOptionDTO dto : optionDTOs) {
            ElementOption option = optionRepository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Element option not found with id: " + dto.getId()));
            
            option.setDisplayOrder(dto.getDisplayOrder());
            updatedOptions.add(optionRepository.save(option));
        }
        
        return updatedOptions.stream()
                .map(this::convertToElementOptionDTO)
                .collect(Collectors.toList());
    }

    // Batch create options
    @Transactional
    public List<ElementOptionDTO> batchCreateOptions(List<ElementOptionDTO> optionDTOs, Integer componentId) {
        FormComponent component = componentRepository.findById(componentId)
                .orElseThrow(() -> new RuntimeException("Component not found"));

        Integer maxDisplayOrder = optionRepository.findByComponentIdOrderByDisplayOrderAsc(componentId)
                .stream()
                .map(ElementOption::getDisplayOrder)
                .max(Integer::compareTo)
                .orElse(0);

        List<ElementOption> options = new ArrayList<>();
        for (int i = 0; i < optionDTOs.size(); i++) {
            // Direct mapping from DTO to entity
            ElementOption option = new ElementOption();
            option.setLabel(optionDTOs.get(i).getLabel());
            option.setValue(optionDTOs.get(i).getValue());
            option.setComponent(component);
            option.setDisplayOrder(maxDisplayOrder + i + 1);
            options.add(option);
        }

        List<ElementOption> savedOptions = optionRepository.saveAll(options);
        return savedOptions.stream()
                .map(this::convertToElementOptionDTO)
                .collect(Collectors.toList());
    }


}

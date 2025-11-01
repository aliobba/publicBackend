package com.ooredoo.report_builder.services;


import com.ooredoo.report_builder.dto.ElementOptionDTO;
import com.ooredoo.report_builder.entity.ElementOption;
import com.ooredoo.report_builder.entity.FormComponent;
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
@Transactional
public class ElementOptionService {
    private final ElementOptionRepository optionRepository;
    private final FormComponentRepository componentRepository;
    private final ElementOptionMapper elementOptionMapper;

    public ElementOptionService(ElementOptionRepository optionRepository,
                                FormComponentRepository componentRepository,
                                ElementOptionMapper elementOptionMapper) {
        this.optionRepository = optionRepository;
        this.componentRepository = componentRepository;
        this.elementOptionMapper = elementOptionMapper;
    }

    @Transactional
    public List<ElementOptionDTO> getAllOptions() {
        List<ElementOption> options = optionRepository.findAll();
        return options.stream()
                .map(elementOptionMapper::toElementOptionDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<ElementOptionDTO> getOptionById(Integer id) {
        return optionRepository.findById(id)
                .map(elementOptionMapper::toElementOptionDTO);
    }

    @Transactional
    public List<ElementOptionDTO> getOptionsByComponentId(Integer componentId) {
        List<ElementOption> options = optionRepository.findByComponentIdOrderByDisplayOrderAsc(componentId);
        return options.stream()
                .map(elementOptionMapper::toElementOptionDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ElementOptionDTO createOption(ElementOptionDTO optionDTO, Integer componentId) {
        FormComponent component = componentRepository.findById(componentId)
                .orElseThrow(() -> new RuntimeException("Component not found"));

        ElementOption option = elementOptionMapper.toElementOption(optionDTO);
        option.setComponent(component);

        Integer maxDisplayOrder = optionRepository.findMaxDisplayOrderByComponentId(componentId).orElse(0);
        option.setDisplayOrder(maxDisplayOrder + 1);
        ElementOption savedOption = optionRepository.save(option);
        return elementOptionMapper.toElementOptionDTO(savedOption);
    }

    @Transactional
    public ElementOptionDTO updateOption(Integer id, ElementOptionDTO optionDTO) {
        ElementOption option = optionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Option not found"));

        option.setLabel(optionDTO.getLabel());
        option.setValue(optionDTO.getValue());
        ElementOption updatedOption = optionRepository.save(option);
        return elementOptionMapper.toElementOptionDTO(updatedOption);
    }

    @Transactional
    public void deleteOption(Integer id) {
        optionRepository.deleteById(id);
    }

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
                .map(elementOptionMapper::toElementOptionDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ElementOptionDTO> batchCreateOptions(List<ElementOptionDTO> optionDTOs, Integer componentId) {
        FormComponent component = componentRepository.findById(componentId)
                .orElseThrow(() -> new RuntimeException("Component not found"));

        Integer maxDisplayOrder = optionRepository.findMaxDisplayOrderByComponentId(componentId).orElse(0);

        List<ElementOption> options = new ArrayList<>();
        for (int i = 0; i < optionDTOs.size(); i++) {
            ElementOption option = elementOptionMapper.toElementOption(optionDTOs.get(i));
            option.setComponent(component);
            option.setDisplayOrder(maxDisplayOrder + i + 1);
            options.add(option);
        }

        List<ElementOption> savedOptions = optionRepository.saveAll(options);
        return savedOptions.stream()
                .map(elementOptionMapper::toElementOptionDTO)
                .collect(Collectors.toList());
    }
}

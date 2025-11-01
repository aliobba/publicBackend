package com.ooredoo.report_builder.controller;

import com.ooredoo.report_builder.dto.ComponentPropertyDTO;
import com.ooredoo.report_builder.dto.ComponentTemplateDTO;
import com.ooredoo.report_builder.dto.ElementOptionDTO;
import com.ooredoo.report_builder.dto.FormComponentDTO;
import com.ooredoo.report_builder.services.ComponentPropertyService;
import com.ooredoo.report_builder.services.ElementOptionService;
import com.ooredoo.report_builder.services.FormComponentService;
import com.ooredoo.report_builder.services.UserService;
import com.ooredoo.report_builder.user.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/components")
@Validated
public class ComponentController {
    private final FormComponentService componentService;
    private final ComponentPropertyService propertyService;
    private final ElementOptionService optionService;
    private final UserService userService;

    public ComponentController(
            FormComponentService componentService,
            ComponentPropertyService propertyService,
            ElementOptionService optionService,
            UserService userService) {

        this.componentService = componentService;
        this.propertyService = propertyService;
        this.optionService = optionService;
        this.userService = userService;
    }

    // === COMPONENT TEMPLATES & TYPES ===

    @GetMapping("/templates")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<List<ComponentTemplateDTO>> getComponentTemplates() {
        return ResponseEntity.ok(componentService.getAvailableComponentTemplates());
    }

    // === COMPONENT CRUD ===

    @GetMapping("/getComponentById/{componentId}")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<FormComponentDTO> getComponentById(@PathVariable Integer componentId) {
        return ResponseEntity.ok(componentService.getComponentById(componentId));
    }

    @PutMapping("/updateComponent/{componentId}")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<FormComponentDTO> updateComponent(
            @PathVariable Integer componentId,
            @Valid @RequestBody FormComponentDTO componentDTO) {

        System.out.println("=== BACKEND UPDATE COMPONENT DEBUG ===");
        System.out.println("Component ID: " + componentId);
        System.out.println("Component DTO: " + componentDTO);
        System.out.println("Options received: " + componentDTO.getOptions());
        System.out.println("=====================================");

        FormComponentDTO updated = componentService.updateComponent(componentId, componentDTO);
        
        System.out.println("=== BACKEND UPDATE COMPONENT RESULT ===");
        System.out.println("Updated component: " + updated);
        System.out.println("Updated options: " + updated.getOptions());
        System.out.println("=====================================");
        
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/updateAssignment/{assignmentId}")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<FormComponentDTO> updateAssignment(
            @PathVariable Integer assignmentId,
            @Valid @RequestBody FormComponentDTO componentDTO) {

        System.out.println("=== BACKEND UPDATE ASSIGNMENT DEBUG ===");
        System.out.println("Assignment ID: " + assignmentId);
        System.out.println("Component DTO: " + componentDTO);
        System.out.println("Label: " + componentDTO.getLabel());
        System.out.println("Required: " + componentDTO.getRequired());
        System.out.println("=====================================");

        FormComponentDTO updated = componentService.updateComponentAssignment(assignmentId, componentDTO);
        
        System.out.println("=== BACKEND UPDATE ASSIGNMENT RESULT ===");
        System.out.println("Updated assignment: " + updated);
        System.out.println("Updated label: " + updated.getLabel());
        System.out.println("Updated required: " + updated.getRequired());
        System.out.println("=====================================");
        
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/deleteComponent/{componentId}")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<MessageResponse> deleteComponent(@PathVariable Integer componentId) {
        componentService.deleteComponent(componentId);
        return ResponseEntity.ok(new MessageResponse("Component deleted successfully"));
    }

    // === GLOBAL/REUSABLE COMPONENTS ===

    @GetMapping("/global")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<List<FormComponentDTO>> getGlobalComponents() {
        User currentUser = userService.getCurrentAuthenticatedUser();
        return ResponseEntity.ok(componentService.getGlobalComponents(currentUser));
    }

    @GetMapping("/reusable")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<List<FormComponentDTO>> getReusableComponents() {
        User currentUser = userService.getCurrentAuthenticatedUser();
        return ResponseEntity.ok(componentService.getReusableComponents(currentUser));
    }

    @PostMapping("/{componentId}/clone/{targetFormId}")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<FormComponentDTO> cloneComponent(
            @PathVariable Integer componentId,
            @PathVariable Integer targetFormId) {

        User currentUser = userService.getCurrentAuthenticatedUser();
        FormComponentDTO cloned = componentService.cloneComponent(componentId, targetFormId, currentUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(cloned);
    }

    // === COMPONENT PROPERTIES ===

    @PostMapping("/addComponentProperty/{componentId}/properties")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<ComponentPropertyDTO> addComponentProperty(
            @PathVariable Integer componentId,
            @Valid @RequestBody ComponentPropertyDTO propertyDTO) {

        ComponentPropertyDTO result = propertyService.createProperty(propertyDTO, componentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/getComponentProperties/{componentId}/properties")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<List<ComponentPropertyDTO>> getComponentProperties(@PathVariable Integer componentId) {
        return ResponseEntity.ok(propertyService.getPropertiesByComponentId(componentId));
    }

    @PutMapping("/properties/{propertyId}")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<ComponentPropertyDTO> updateComponentProperty(
            @PathVariable Integer propertyId,
            @RequestParam String value) {

        ComponentPropertyDTO updated = propertyService.updateProperty(propertyId, value);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/properties/{propertyId}")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<MessageResponse> deleteComponentProperty(@PathVariable Integer propertyId) {
        propertyService.deleteProperty(propertyId);
        return ResponseEntity.ok(new MessageResponse("Property deleted successfully"));
    }

    // === COMPONENT OPTIONS ===

    @PostMapping("/addElementOption/{componentId}/options")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<ElementOptionDTO> addElementOption(
            @PathVariable Integer componentId,
            @Valid @RequestBody ElementOptionDTO optionDTO) {

        ElementOptionDTO result = optionService.createOption(optionDTO, componentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/{componentId}/options/batch")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<List<ElementOptionDTO>> batchCreateOptions(
            @PathVariable Integer componentId,
            @Valid @RequestBody List<ElementOptionDTO> optionDTOs) {

        List<ElementOptionDTO> result = optionService.batchCreateOptions(optionDTOs, componentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/getComponentOptions/{componentId}/options")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<List<ElementOptionDTO>> getComponentOptions(@PathVariable Integer componentId) {
        return ResponseEntity.ok(optionService.getOptionsByComponentId(componentId));
    }

    @PutMapping("/options/{optionId}")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<ElementOptionDTO> updateElementOption(
            @PathVariable Integer optionId,
            @Valid @RequestBody ElementOptionDTO optionDTO) {

        ElementOptionDTO updated = optionService.updateOption(optionId, optionDTO);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/{componentId}/options/reorder")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<List<ElementOptionDTO>> reorderOptions(
            @PathVariable Integer componentId,
            @Valid @RequestBody List<ElementOptionDTO> optionDTOs) {

        List<ElementOptionDTO> reordered = optionService.reorderOptions(optionDTOs);
        return ResponseEntity.ok(reordered);
    }

    @DeleteMapping("/deleteElementOption/options/{optionId}")
    //@PreAuthorize("hasAnyAuthority('MAIN_ADMIN', 'DEPARTMENT_ADMIN')")
    public ResponseEntity<MessageResponse> deleteElementOption(@PathVariable Integer optionId) {
        optionService.deleteOption(optionId);
        return ResponseEntity.ok(new MessageResponse("Option deleted successfully"));
    }
}

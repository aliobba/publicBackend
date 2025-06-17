package com.ooredoo.report_builder.services;

import com.ooredoo.report_builder.controller.department.AdminChangeRequest;
import com.ooredoo.report_builder.controller.department.DepartmentCreationRequest;
import com.ooredoo.report_builder.controller.department.DepartmentResponse;
import com.ooredoo.report_builder.controller.department.DepartmentUpdateRequest;
import com.ooredoo.report_builder.controller.department.UserSummaryResponse;
import com.ooredoo.report_builder.entity.Department;
import com.ooredoo.report_builder.enums.RoleType;
import com.ooredoo.report_builder.handler.ResourceNotFoundException;
import com.ooredoo.report_builder.repo.DepartmentRepository;
import com.ooredoo.report_builder.repo.RoleRepository;
import com.ooredoo.report_builder.repo.UserRepository;
import com.ooredoo.report_builder.user.Role;
import com.ooredoo.report_builder.user.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public DepartmentService(DepartmentRepository departmentRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public DepartmentResponse createDepartment(DepartmentCreationRequest request) {
        if (departmentRepository.existsByName(request.getName())) {
            throw new RuntimeException("Department with this name already exists");
        }

        // Create department entity directly from request
        Department department = new Department();
        department.setName(request.getName());
        department.setDescription(request.getDescription());
        department.setCreatedAt(LocalDateTime.now());

        User admin = userRepository.findById(request.getAdminId())
                .orElseThrow(() -> new ResourceNotFoundException("Admin user not found"));

        // Assign department admin role if not already assigned
        Role deptAdminRole = roleRepository.findByName(RoleType.ENTREPRISE_ADMIN.getValue())
                .orElseThrow(() -> new RuntimeException("Department admin role not found"));

        if (!admin.getRoles().contains(deptAdminRole)) {
            admin.getRoles().add(deptAdminRole);
            userRepository.save(admin);
        }

        department.setDepartmentAdmin(admin);

        department = departmentRepository.save(department);

        // Update admin's department if not set
        if (admin.getDepartment() == null || !admin.getDepartment().equals(department)) {
            admin.setDepartment(department);
            userRepository.save(admin);
        }

        return convertToResponse(department);
    }

    public DepartmentResponse updateDepartment(Integer departmentId, DepartmentUpdateRequest request) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        // Update department fields directly
        if (request.getName() != null) {
            department.setName(request.getName());
        }
        if (request.getDescription() != null) {
            department.setDescription(request.getDescription());
        }
        department.setUpdatedAt(LocalDateTime.now());
        
        return convertToResponse(departmentRepository.save(department));
    }

    public void deleteDepartment(Integer departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        for (User user : department.getUsers()) {
            user.setDepartment(null);
            userRepository.save(user);
        }

        departmentRepository.delete(department);
    }

    public void addUserToDepartment(Integer userId, Integer departmentId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        user.setDepartment(department);
        userRepository.save(user);
    }

    public DepartmentResponse changeDepartmentAdmin(Integer departmentId, AdminChangeRequest request) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        User newAdmin = userRepository.findById(request.getNewAdminId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Assign department admin role if not already assigned
        Role deptAdminRole = roleRepository.findByName(RoleType.ENTREPRISE_ADMIN.getValue())
                .orElseThrow(() -> new RuntimeException("Department admin role not found"));

        if (!newAdmin.getRoles().contains(deptAdminRole)) {
            newAdmin.getRoles().add(deptAdminRole);
            userRepository.save(newAdmin);
        }

        department.setDepartmentAdmin(newAdmin);
        department = departmentRepository.save(department);

        // Update admin's department
        newAdmin.setDepartment(department);
        userRepository.save(newAdmin);

        return convertToResponse(department);
    }

    public List<DepartmentResponse> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public DepartmentResponse getDepartmentById(Integer departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
        return convertToResponse(department);
    }
    
    // Helper method to convert Department entity to DepartmentResponse
    private DepartmentResponse convertToResponse(Department department) {
        if (department == null) return null;
        
        DepartmentResponse response = new DepartmentResponse();
        response.setId(department.getId());
        response.setName(department.getName());
        response.setDescription(department.getDescription());
        response.setCreatedAt(department.getCreatedAt());
        response.setUpdatedAt(department.getUpdatedAt());
        
        // Map department admin
        if (department.getDepartmentAdmin() != null) {
            response.setAdmin(convertToUserSummaryResponse(department.getDepartmentAdmin()));
        }
        
        // Map users
        if (department.getUsers() != null && !department.getUsers().isEmpty()) {
            response.setUsers(department.getUsers().stream()
                .map(this::convertToUserSummaryResponse)
                .collect(Collectors.toList()));
        }
        
        return response;
    }
    
    // Helper method to convert User to UserSummaryResponse
    private UserSummaryResponse convertToUserSummaryResponse(User user) {
        if (user == null) return null;
        
        UserSummaryResponse response = new UserSummaryResponse();
        response.setId(user.getId_user());
        response.setFirstname(user.getFirstname());
        response.setLastname(user.getLastname());
        response.setEmail(user.getEmail());
        return response;
    }
}

package com.ooredoo.report_builder.services;

import com.ooredoo.report_builder.dto.request.CreateEnterpriseRequest;
import com.ooredoo.report_builder.dto.request.UpdateEnterpriseRequest;
import com.ooredoo.report_builder.dto.response.EnterpriseResponse;
import com.ooredoo.report_builder.entity.Enterprise;
import com.ooredoo.report_builder.enums.RoleType;
import com.ooredoo.report_builder.handler.ResourceNotFoundException;
import com.ooredoo.report_builder.repo.EnterpriseRepository;
import com.ooredoo.report_builder.repo.POSRepository;
import com.ooredoo.report_builder.repo.RoleRepository;
import com.ooredoo.report_builder.repo.SectorRepository;
import com.ooredoo.report_builder.repo.UserRepository;
import com.ooredoo.report_builder.user.Role;
import com.ooredoo.report_builder.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EnterpriseServiceTest {

    @Mock
    private EnterpriseRepository enterpriseRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private SectorRepository sectorRepository;

    @Mock
    private POSRepository posRepository;

    @InjectMocks
    private EnterpriseService enterpriseService;

    private Enterprise testEnterprise;
    private User testAdmin;
    private Role testRole;
    private CreateEnterpriseRequest createRequest;
    private UpdateEnterpriseRequest updateRequest;

    @BeforeEach
    void setUp() {
        // Setup test data
        testAdmin = new User();
        testAdmin.setId_user(1);
        testAdmin.setFirstname("Admin");
        testAdmin.setLastname("User");
        testAdmin.setEmail("admin@example.com");

        testRole = new Role();
        testRole.setId(1);
        testRole.setName(RoleType.ENTREPRISE_ADMIN.getValue());

        testAdmin.setRoles(List.of(testRole));

        testEnterprise = new Enterprise();
        testEnterprise.setId(1);
        testEnterprise.setName("Test Enterprise");
        testEnterprise.setDescription("Test Description");
        testEnterprise.setLogo("test-logo.png");
        testEnterprise.setPrimaryColor("#FF0000");
        testEnterprise.setSecondaryColor("#00FF00");
        testEnterprise.setEnterpriseAdmin(testAdmin);
        testEnterprise.setCreatedAt(LocalDateTime.now());

        createRequest = new CreateEnterpriseRequest();
        createRequest.setName("New Enterprise");
        createRequest.setDescription("New Description");
        createRequest.setLogo("new-logo.png");
        createRequest.setPrimaryColor("#0000FF");
        createRequest.setSecondaryColor("#FFFF00");
        createRequest.setEnterpriseAdminId(1);

        updateRequest = new UpdateEnterpriseRequest();
        updateRequest.setName("Updated Enterprise");
        updateRequest.setDescription("Updated Description");
        updateRequest.setEnterpriseAdminId(1);
    }

    @Test
    void createEnterprise_Success() {
        // Arrange
        when(enterpriseRepository.existsByName(anyString())).thenReturn(false);
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(testAdmin));
        when(roleRepository.findByName(anyString())).thenReturn(Optional.of(testRole));
        when(enterpriseRepository.save(any(Enterprise.class))).thenReturn(testEnterprise);

        // Act
        EnterpriseResponse response = enterpriseService.createEnterprise(createRequest);

        // Assert
        assertNotNull(response);
        assertEquals(testEnterprise.getId(), response.getId());
        assertEquals(testEnterprise.getName(), response.getName());
        verify(enterpriseRepository, times(1)).save(any(Enterprise.class));
    }

    @Test
    void createEnterprise_NameExists_ThrowsException() {
        // Arrange
        when(enterpriseRepository.existsByName(anyString())).thenReturn(true);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> enterpriseService.createEnterprise(createRequest));
        verify(enterpriseRepository, never()).save(any(Enterprise.class));
    }

    @Test
    void updateEnterprise_Success() {
        // Arrange
        when(enterpriseRepository.findById(anyInt())).thenReturn(Optional.of(testEnterprise));
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(testAdmin));
        when(roleRepository.findByName(anyString())).thenReturn(Optional.of(testRole));
        when(enterpriseRepository.save(any(Enterprise.class))).thenReturn(testEnterprise);

        // Act
        EnterpriseResponse response = enterpriseService.updateEnterprise(1, updateRequest);

        // Assert
        assertNotNull(response);
        assertEquals(updateRequest.getName(), testEnterprise.getName());
        assertEquals(updateRequest.getDescription(), testEnterprise.getDescription());
        verify(enterpriseRepository, times(1)).save(any(Enterprise.class));
    }

    @Test
    void updateEnterprise_NotFound_ThrowsException() {
        // Arrange
        when(enterpriseRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> enterpriseService.updateEnterprise(1, updateRequest));
        verify(enterpriseRepository, never()).save(any(Enterprise.class));
    }

    @Test
    void deleteEnterprise_Success() {
        // Arrange
        User user1 = new User();
        User user2 = new User();
        Set<User> users = new HashSet<>(Arrays.asList(user1, user2));
        testEnterprise.setUsers(users);

        when(enterpriseRepository.findById(anyInt())).thenReturn(Optional.of(testEnterprise));

        // Act
        enterpriseService.deleteEnterprise(1);

        // Assert
        verify(userRepository, times(2)).save(any(User.class));
        verify(enterpriseRepository, times(1)).delete(any(Enterprise.class));
    }

    @Test
    void getAllEnterprises_Success() {
        // Arrange
        List<Enterprise> enterprises = List.of(testEnterprise);
        when(enterpriseRepository.findAll()).thenReturn(enterprises);

        // Act
        List<EnterpriseResponse> responses = enterpriseService.getAllEnterprises();

        // Assert
        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals(testEnterprise.getId(), responses.get(0).getId());
    }

    @Test
    void getEnterpriseById_Success() {
        // Arrange
        when(enterpriseRepository.findById(anyInt())).thenReturn(Optional.of(testEnterprise));

        // Act
        EnterpriseResponse response = enterpriseService.getEnterpriseById(1);

        // Assert
        assertNotNull(response);
        assertEquals(testEnterprise.getId(), response.getId());
        assertEquals(testEnterprise.getName(), response.getName());
    }

    @Test
    void getEnterpriseById_NotFound_ThrowsException() {
        // Arrange
        when(enterpriseRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> enterpriseService.getEnterpriseById(1));
    }
}
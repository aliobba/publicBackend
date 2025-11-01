package com.ooredoo.report_builder.services;

import com.ooredoo.report_builder.entity.Enterprise;
import com.ooredoo.report_builder.entity.Region;
import com.ooredoo.report_builder.entity.Sector;
import com.ooredoo.report_builder.entity.Zone;
import com.ooredoo.report_builder.handler.ResourceNotFoundException;
import com.ooredoo.report_builder.repo.EnterpriseRepository;
import com.ooredoo.report_builder.repo.RegionRepository;
import com.ooredoo.report_builder.repo.SectorRepository;
import com.ooredoo.report_builder.repo.UserRepository;
import com.ooredoo.report_builder.repo.ZoneRepository;
import com.ooredoo.report_builder.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SectorZoneRegionTest {

  /*  @Mock
    private SectorRepository sectorRepository;

    @Mock
    private ZoneRepository zoneRepository;

    @Mock
    private RegionRepository regionRepository;

    @Mock
    private EnterpriseRepository enterpriseRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private SectorService sectorService;

    @InjectMocks
    private ZoneService zoneService;

    @InjectMocks
    private RegionService regionService;

    private Enterprise testEnterprise;
    private Sector testSector;
    private Zone testZone;
    private Region testRegion;
    private User testUser;

    @BeforeEach
    void setUp() {
        // Setup test data
        testUser = new User();
        testUser.setId(1);
        testUser.setFirstname("Test");
        testUser.setLastname("User");
        testUser.setEmail("test@example.com");

        testEnterprise = new Enterprise();
        testEnterprise.setId(1);
        testEnterprise.setName("Test Enterprise");

        testSector = new Sector();
        testSector.setId(1);
        testSector.setName("Test Sector");
        testSector.setDescription("Test Sector Description");
        testSector.setEnterprise(testEnterprise);
        testSector.setSectorHead(testUser);
        testSector.setCreated_Date(LocalDateTime.now());

        testZone = new Zone();
        testZone.setId(1);
        testZone.setName("Test Zone");
        testZone.setDescription("Test Zone Description");
        testZone.setSector(testSector);
        testZone.setZoneHead(testUser);
        testZone.setCreated_Date(LocalDateTime.now());

        testRegion = new Region();
        testRegion.setId(1);
        testRegion.setName("Test Region");
        testRegion.setDescription("Test Region Description");
        testRegion.setZone(testZone);
        testRegion.setRegionHead(testUser);
        testRegion.setCreated_Date(LocalDateTime.now());
    }

    // Sector Tests
    @Test
    void createSector_Success() {
        // Arrange
        CreateSectorRequest request = new CreateSectorRequest();
        request.setName("New Sector");
        request.setDescription("New Sector Description");
        request.setEnterpriseId(1);
        request.setSectorHeadId(1);

        when(enterpriseRepository.findById(anyInt())).thenReturn(Optional.of(testEnterprise));
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(testUser));
        when(sectorRepository.save(any(Sector.class))).thenReturn(testSector);

        // Act
        SectorResponse response = sectorService.createSector(request);

        // Assert
        assertNotNull(response);
        assertEquals(testSector.getId(), response.getId());
        assertEquals(testSector.getName(), response.getName());
        verify(sectorRepository, times(1)).save(any(Sector.class));
    }

    @Test
    void getSectorById_Success() {
        // Arrange
        when(sectorRepository.findById(anyInt())).thenReturn(Optional.of(testSector));

        // Act
        SectorResponse response = sectorService.getSectorById(1);

        // Assert
        assertNotNull(response);
        assertEquals(testSector.getId(), response.getId());
        assertEquals(testSector.getName(), response.getName());
    }

    @Test
    void getSectorById_NotFound_ThrowsException() {
        // Arrange
        when(sectorRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> sectorService.getSectorById(1));
    }

    // Zone Tests
    @Test
    void createZone_Success() {
        // Arrange
        CreateZoneRequest request = new CreateZoneRequest();
        request.setName("New Zone");
        request.setDescription("New Zone Description");
        request.setSectorId(1);
        request.setZoneHeadId(1);

        when(sectorRepository.findById(anyInt())).thenReturn(Optional.of(testSector));
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(testUser));
        when(zoneRepository.save(any(Zone.class))).thenReturn(testZone);

        // Act
        ZoneResponse response = zoneService.createZone(request);

        // Assert
        assertNotNull(response);
        assertEquals(testZone.getId(), response.getId());
        assertEquals(testZone.getName(), response.getName());
        verify(zoneRepository, times(1)).save(any(Zone.class));
    }

    @Test
    void getZoneById_Success() {
        // Arrange
        when(zoneRepository.findById(anyInt())).thenReturn(Optional.of(testZone));

        // Act
        ZoneResponse response = zoneService.getZoneById(1);

        // Assert
        assertNotNull(response);
        assertEquals(testZone.getId(), response.getId());
        assertEquals(testZone.getName(), response.getName());
    }

    @Test
    void getZoneById_NotFound_ThrowsException() {
        // Arrange
        when(zoneRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> zoneService.getZoneById(1));
    }

    // Region Tests
    @Test
    void createRegion_Success() {
        // Arrange
        CreateRegionRequest request = new CreateRegionRequest();
        request.setName("New Region");
        request.setDescription("New Region Description");
        request.setZoneId(1);
        request.setRegionHeadId(1);

        when(zoneRepository.findById(anyInt())).thenReturn(Optional.of(testZone));
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(testUser));
        when(regionRepository.save(any(Region.class))).thenReturn(testRegion);

        // Act
        RegionResponse response = regionService.createRegion(request);

        // Assert
        assertNotNull(response);
        assertEquals(testRegion.getId(), response.getId());
        assertEquals(testRegion.getName(), response.getName());
        verify(regionRepository, times(1)).save(any(Region.class));
    }

    @Test
    void getRegionById_Success() {
        // Arrange
        when(regionRepository.findById(anyInt())).thenReturn(Optional.of(testRegion));

        // Act
        RegionResponse response = regionService.getRegionById(1);

        // Assert
        assertNotNull(response);
        assertEquals(testRegion.getId(), response.getId());
        assertEquals(testRegion.getName(), response.getName());
    }

    @Test
    void getRegionById_NotFound_ThrowsException() {
        // Arrange
        when(regionRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> regionService.getRegionById(1));
    }

    // Hierarchy Tests
    @Test
    void testHierarchyRelationships() {
        // Arrange
        when(regionRepository.findById(anyInt())).thenReturn(Optional.of(testRegion));

        // Act
        RegionResponse response = regionService.getRegionById(1);

        // Assert
        assertNotNull(response);
        assertEquals(testRegion.getId(), response.getId());
        
        // Verify zone relationship
        assertNotNull(testRegion.getZone());
        assertEquals(testZone.getId(), testRegion.getZone().getId());
        
        // Verify sector relationship
        assertNotNull(testRegion.getZone().getSector());
        assertEquals(testSector.getId(), testRegion.getZone().getSector().getId());
        
        // Verify enterprise relationship
        assertNotNull(testRegion.getZone().getSector().getEnterprise());
        assertEquals(testEnterprise.getId(), testRegion.getZone().getSector().getEnterprise().getId());
    }*/
}
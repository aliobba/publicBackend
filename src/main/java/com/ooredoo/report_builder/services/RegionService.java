package com.ooredoo.report_builder.services;

import com.ooredoo.report_builder.entity.Region;
import com.ooredoo.report_builder.enums.RoleType;
import com.ooredoo.report_builder.handler.ResourceNotFoundException;
import com.ooredoo.report_builder.repo.RegionRepository;
import com.ooredoo.report_builder.repo.SectorRepository;
import com.ooredoo.report_builder.repo.UserRepository;
import com.ooredoo.report_builder.user.User;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SectorRepository sectorRepository;

    public RegionService() {
    }

    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    public Optional<Region> findById(Integer id) {
        return regionRepository.findById(id);
    }

    public Region save(Region region) {
        // Validate required fields
        if (region.getRegionName() == null || region.getRegionName().trim().isEmpty()) {
            throw new IllegalArgumentException("Region name is required");
        }
        
        User manager = null;
        if (region.getHeadOfRegion() != null && region.getHeadOfRegion().getId() != null) {
            manager = userRepository.findById(region.getHeadOfRegion().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
        }
        
        // Check if it's an update or create
        if (region.getId() != null) {
            // Update existing region
            Region existingRegion = regionRepository.findById(region.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Region not found with id: " + region.getId()));
            
            existingRegion.setRegionName(region.getRegionName());
            existingRegion.setHeadOfRegion(manager);
            
            if (manager != null) {
                validateRegionHead(existingRegion);
            }
            
            log.info("Updating region: {}", region.getRegionName());
            return regionRepository.save(existingRegion);
        } else {
            // Create new region
            Region newRegion = Region.builder()
                    .region_Name(region.getRegionName())
                    .headOfRegion(manager)
                    .build();
            
            if (manager != null) {
                validateRegionHead(newRegion);
            }
            
            log.info("Creating region: {}", region.getRegionName());
            return regionRepository.save(newRegion);
        }
    }

    public void deleteById(Integer id) {
        validateRegionDeletion(id);
        regionRepository.deleteById(id);
    }

   /* public List<Region> findRegionsWithoutHead() {
        return regionRepository.findByHeadOfRegionIsNull();
    }*/

    private void validateRegionHead(Region region) {
        if (region.getHeadOfRegion().getId() != null) {
            User manager = userRepository.findById(region.getHeadOfRegion().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
            if (!manager.hasRole(RoleType.HEAD_OF_REGION.getValue())) {
                throw new IllegalArgumentException("User must have HEAD_OF_REGION type to be assigned as head of region");
            }

            if (region.getId() == null || !region.getId().equals(getCurrentRegionIdForHead(region.getHeadOfRegion().getId()))) {
                if (regionRepository.existsByHeadOfRegionId(region.getHeadOfRegion().getId())) {
                    throw new IllegalStateException("User is already assigned as head of another region");
                }
            }
        }
    }

    private Integer getCurrentRegionIdForHead(Integer headId) {
        List<Region> regions = regionRepository.findAll();
        return regions.stream()
                .filter(r -> r.getHeadOfRegion() != null && r.getHeadOfRegion().getId().equals(headId))
                .map(Region::getId)
                .findFirst()
                .orElse(null);
    }

    private void validateRegionDeletion(Integer regionId) {
        Optional<Region> region = findById(regionId);
        if (region.isPresent() && !region.get().getZones().isEmpty()) {
            throw new IllegalStateException("Cannot delete region: has Zone assigned. Reassign or delete Zone first.");
        }
    }

/*
    private final RegionRepository regionRepository;
    private final sectorRepository sectorRepository;
    private final UserRepository userRepository;
    private final RegionMapper regionMapper;

    // 🔹 Create region under zone
    @Transactional
    public RegionResponseDTO createRegion(Integer zoneId, RegionRequestDTO request) {
        Zone zone = sectorRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

        Region region = regionMapper.toEntity(request);
        region.setZone(zone);

        if (request.getManagerId() != null) {
            User manager = userRepository.findById(request.getManagerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
            region.setHeadOfPOS(manager);
        }

        return regionMapper.toDto(regionRepository.save(region));
    }

    // 🔹 Update region
    @Transactional
    public RegionResponseDTO updateRegion(Integer id, RegionRequestDTO request) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Region not found"));

        region.setName(request.getName());

        if (request.getManagerId() != null) {
            User manager = userRepository.findById(request.getManagerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
            region.setHeadOfPOS(manager);
        }

        return regionMapper.toDto(regionRepository.save(region));
    }

    // 🔹 Delete region
    @Transactional
    public void deleteRegion(Integer id) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Region not found"));
        regionRepository.delete(region);
    }

    // 🔹 Get one region
    public RegionResponseDTO getRegion(Integer id) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Region not found"));
        return regionMapper.toDto(region);
    }

    // 🔹 Get all regions
    public List<RegionResponseDTO> getAllRegions() {
        return regionRepository.findAll().stream()
                .map(regionMapper::toDto)
                .collect(Collectors.toList());
    }

    // 🔹 Assign user
    @Transactional
    public RegionResponseDTO assignUser(Integer regionId, Integer userId) {
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new ResourceNotFoundException("Region not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        region.getUsersInRegion().add(user);
        return regionMapper.toDto(regionRepository.save(region));
    }

    // 🔹 Unassign user
    @Transactional
    public RegionResponseDTO unassignUser(Integer regionId, Integer userId) {
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new ResourceNotFoundException("Region not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        region.getUsersInRegion().remove(user);
        return regionMapper.toDto(regionRepository.save(region));
    }*/
}

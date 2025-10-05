package com.ooredoo.report_builder.services;

import com.ooredoo.report_builder.entity.Region;
import com.ooredoo.report_builder.entity.Sector;
import com.ooredoo.report_builder.entity.Zone;
import com.ooredoo.report_builder.enums.UserType;
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
    private UserRepository  userRepository;
    @Autowired
    private SectorRepository sectorRepository ;

    public RegionService() {
    }

    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    public Optional<Region> findById(Integer id) {
        return regionRepository.findById(id);
    }

    public Region save(Region region) {
        User manager = userRepository.findById(region.getHeadOfRegion().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
        Region newRegion = Region.builder()
                .name(region.getName())
                .headOfRegion(manager)
                .build();
        validateRegionHead(newRegion);
        return regionRepository.save(newRegion);
    }

    public void deleteById(Integer id) {
        validateRegionDeletion(id);
        regionRepository.deleteById(id);
    }

    /*public List<Region> findRegionsWithoutHead() {
        return regionRepository.findByHeadOfRegionIsNull();
    }*/

    private void validateRegionHead(Region region) {
        if (region.getHeadOfRegion().getId() != null) {
            User manager = userRepository.findById(region.getHeadOfRegion().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
            if (!manager.getUserType().equals(UserType.HEAD_OF_REGION)) {
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

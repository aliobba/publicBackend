package com.ooredoo.report_builder.services;

import com.ooredoo.report_builder.entity.Region;
import com.ooredoo.report_builder.entity.Sector;
import com.ooredoo.report_builder.entity.Zone;
import com.ooredoo.report_builder.enums.UserType;
import com.ooredoo.report_builder.handler.ResourceNotFoundException;
import com.ooredoo.report_builder.mapper.ZoneMapper;
import com.ooredoo.report_builder.repo.SectorRepository;
import com.ooredoo.report_builder.repo.UserRepository;
import com.ooredoo.report_builder.repo.ZoneRepository;
import com.ooredoo.report_builder.user.User;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ZoneService {


    private final SectorRepository sectorRepository;
    private final UserRepository userRepository;
    private final ZoneMapper zoneMapper;


    @Autowired
    private ZoneRepository zoneRepository;

    public ZoneService(SectorRepository sectorRepository, UserRepository userRepository, ZoneMapper zoneMapper) {
        this.sectorRepository = sectorRepository;
        this.userRepository = userRepository;
        this.zoneMapper = zoneMapper;
    }

    public List<Zone> findAll() {
        return zoneRepository.findAll();
    }

    public Optional<Zone> findById(Integer id) {
        return zoneRepository.findById(id);
    }

    public List<Zone> findBySectorId(Integer sectorId) {
        return zoneRepository.findBySectorId(sectorId);
    }

    public Zone save(Zone zone) {
        User manager = userRepository.findById(zone.getHeadOfZone().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
        Sector  sector = sectorRepository.findById(zone.getSector().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Sector not found"));
        Zone zone1 = Zone.builder()
                .name(zone.getName())
                .headOfZone(manager)
                .sector(sector)
                .build();
        validateZoneHead(zone1);
        return zoneRepository.save(zone1);
    }

    public void deleteById(Integer id) {
        validateZoneDeletion(id);
        zoneRepository.deleteById(id);
    }

    /*public List<Zone> findZonesWithoutHead() {
        return zoneRepository.findByHeadOfZoneIsNull();
    }*/



    private void validateZoneHead(Zone zone) {

        if (zone.getHeadOfZone().getId() != null) {
            User manager = userRepository.findById(zone.getHeadOfZone().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
            // Check if user has correct type
            if (!manager.getUserType().equals(UserType.HEAD_OF_ZONE)) {
                throw new IllegalArgumentException("User must have HEAD_OF_ZONE type to be assigned as head of zone");
            }

            // Check if user is already head of another sector
            if (zone.getId() == null || !zone.getId().equals(getCurrentZoneIdForHead(zone.getHeadOfZone().getId()))) {
                if (sectorRepository.existsByHeadOfSectorId(zone.getHeadOfZone().getId())) {
                    throw new IllegalStateException("User is already assigned as head of another zone");
                }
            }
        }
    }


    private Integer getCurrentZoneIdForHead(Integer headId) {
        List<Zone> zones = zoneRepository.findAll();
        return zones.stream()
                .filter(z -> z.getHeadOfZone() != null && z.getHeadOfZone().getId().equals(headId))
                .map(Zone::getId)
                .findFirst()
                .orElse(null);
    }

    private void validateZoneDeletion(Integer zoneId) {
        Optional<Zone> zone = findById(zoneId);
        if (zone.isPresent() && !zone.get().getRegions().isEmpty()) {
            throw new IllegalStateException("Cannot delete zone: has regions assigned. Reassign or delete regions first.");
        }
    }

/*
    // 🔹 Create zone under sector
    @Transactional
    public ZoneResponseDTO createZone(Integer sectorId, ZoneRequestDTO request) {
        Sector sector = sectorRepository.findById(sectorId)
                .orElseThrow(() -> new ResourceNotFoundException("Sector not found"));

        Zone zone = zoneMapper.toEntity(request);
        zone.setSector(sector);

        if (request.getManagerId() != null) {
            User manager = userRepository.findById(request.getManagerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
            zone.setHeadOfPOS(manager);
        }

        return zoneMapper.toDto(zoneRepository.save(zone));
    }

    // 🔹 Update zone
    @Transactional
    public ZoneResponseDTO updateZone(Integer id, ZoneRequestDTO request) {
        Zone zone = zoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

        zone.setName(request.getName());

        if (request.getManagerId() != null) {
            User manager = userRepository.findById(request.getManagerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
            zone.setHeadOfPOS(manager);
        }

        return zoneMapper.toDto(zoneRepository.save(zone));
    }

    // 🔹 Delete zone
    @Transactional
    public void deleteZone(Integer id) {
        Zone zone = zoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
        zoneRepository.delete(zone);
    }

    // 🔹 Get one zone
    public ZoneResponseDTO getZone(Integer id) {
        Zone zone = zoneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
        return zoneMapper.toDto(zone);
    }

    // 🔹 Get all zones
    public List<ZoneResponseDTO> getAllZones() {
        return zoneRepository.findAll().stream()
                .map(zoneMapper::toDto)
                .collect(Collectors.toList());
    }

    // 🔹 Assign user
    @Transactional
    public ZoneResponseDTO assignUser(Integer zoneId, Integer userId) {
        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        zone.getUsersInZone().add(user);
        return zoneMapper.toDto(zoneRepository.save(zone));
    }

    // 🔹 Unassign user
    @Transactional
    public ZoneResponseDTO unassignUser(Integer zoneId, Integer userId) {
        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        zone.getUsersInZone().remove(user);
        return zoneMapper.toDto(zoneRepository.save(zone));
    }*/

    // 🔹 Get all regions in zone
    public List<String> getZoneRegions(Integer zoneId) {
        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));

        Set<Region> regions = zone.getRegions();
        return regions.stream().map(Region::getName).collect(Collectors.toList());
    }
}

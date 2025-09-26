package com.ooredoo.report_builder.services;

import com.ooredoo.report_builder.entity.Sector;
import com.ooredoo.report_builder.enums.UserType;
import com.ooredoo.report_builder.handler.ResourceNotFoundException;
import com.ooredoo.report_builder.repo.SectorRepository;
import com.ooredoo.report_builder.repo.UserRepository;
import com.ooredoo.report_builder.user.User;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@Slf4j
public class SectorService {
    @Autowired
    private SectorRepository sectorRepository;

    @Autowired
    private UserRepository userRepository;

    public SectorService() {
    }

    public List<Sector> findAll() {
        return sectorRepository.findAll();
    }

    public Optional<Sector> findById(Integer id) {
        return sectorRepository.findById(id);
    }

   /* public List<Sector> findByEnterpriseId(Integer enterpriseId) {
        return sectorRepository.findByEnterpriseId(enterpriseId);
    }*/


    public Sector save(Sector sector) {

        validateSectorHead(sector);
        return sectorRepository.save(sector);
    }

    public void deleteById(Integer id) {
        validateSectorDeletion(id);
        sectorRepository.deleteById(id);
    }

    public List<Sector> findSectorsWithoutHead() {
        return sectorRepository.findByHeadOfSectorIsNull();
    }

    private void validateSectorHead(Sector sector) {

        if (sector.getHeadOfSector().getId() != null) {

            User manager = userRepository.findById(sector.getHeadOfSector().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
            // Check if user has correct type
            if (!manager.getUserType().equals(UserType.HEAD_OF_SECTOR)) {
                throw new IllegalArgumentException("User must have HEAD_OF_SECTOR type to be assigned as head of sector");
            }

            // Check if user is already head of another sector
            if (sector.getId() == null || !sector.getId().equals(getCurrentSectorIdForHead(sector.getHeadOfSector().getId()))) {
                if (sectorRepository.existsByHeadOfSectorId(sector.getHeadOfSector().getId())) {
                    throw new IllegalStateException("User is already assigned as head of another sector");
                }
            }
        }
    }

    private Integer getCurrentSectorIdForHead(Integer headId) {
        List<Sector> sectors = sectorRepository.findAll();
        return sectors.stream()
                .filter(s -> s.getHeadOfSector() != null && s.getHeadOfSector().getId().equals(headId))
                .map(Sector::getId)
                .findFirst()
                .orElse(null);
    }

    private void validateSectorDeletion(Integer sectorId) {
        Optional<Sector> sector = findById(sectorId);
        if (sector.isPresent() && !sector.get().getZones().isEmpty()) {
            throw new IllegalStateException("Cannot delete sector: has zones assigned. Reassign or delete zones first.");
        }
    }
/*
    private final SectorRepository sectorRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final UserRepository userRepository;
    private final SectorMapper sectorMapper;

    // 🔹 Create sector under enterprise
    @Transactional
    public SectorResponseDTO createSector(Integer enterpriseId, SectorRequestDTO request) {
        Enterprise enterprise = enterpriseRepository.findById(enterpriseId)
                .orElseThrow(() -> new ResourceNotFoundException("Enterprise not found"));

        Sector sector = sectorMapper.toEntity(request);
        sector.setEnterprise(enterprise);

        if (request.getManagerId() != null) {
            User manager = userRepository.findById(request.getManagerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
            sector.setHeadOfPOS(manager);
        }

        return sectorMapper.toDto(sectorRepository.save(sector));
    }

    // 🔹 Update sector
    @Transactional
    public SectorResponseDTO updateSector(Integer id, SectorRequestDTO request) {
        Sector sector = sectorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sector not found"));

        sector.setName(request.getName());

        if (request.getManagerId() != null) {
            User manager = userRepository.findById(request.getManagerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
            sector.setHeadOfPOS(manager);
        }

        return sectorMapper.toDto(sectorRepository.save(sector));
    }

    // 🔹 Delete sector
    @Transactional
    public void deleteSector(Integer id) {
        Sector sector = sectorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sector not found"));
        sectorRepository.delete(sector);
    }

    // 🔹 Get one sector
    public SectorResponseDTO getSector(Integer id) {
        Sector sector = sectorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sector not found"));
        return sectorMapper.toDto(sector);
    }

    // 🔹 Get all sectors
    public List<SectorResponseDTO> getAllSectors() {
        return sectorRepository.findAll().stream()
                .map(sectorMapper::toDto)
                .collect(Collectors.toList());
    }

    // 🔹 Assign user
    @Transactional
    public SectorResponseDTO assignUser(Integer sectorId, Integer userId) {
        Sector sector = sectorRepository.findById(sectorId)
                .orElseThrow(() -> new ResourceNotFoundException("Sector not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        sector.getUsersInSector().add(user);
        return sectorMapper.toDto(sectorRepository.save(sector));
    }

    // 🔹 Unassign user
    @Transactional
    public SectorResponseDTO unassignUser(Integer sectorId, Integer userId) {
        Sector sector = sectorRepository.findById(sectorId)
                .orElseThrow(() -> new ResourceNotFoundException("Sector not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        sector.getUsersInSector().remove(user);
        return sectorMapper.toDto(sectorRepository.save(sector));
    }

    // 🔹 Get all zones in sector
    public List<String> getSectorZones(Integer sectorId) {
        Sector sector = sectorRepository.findById(sectorId)
                .orElseThrow(() -> new ResourceNotFoundException("Sector not found"));

        Set<Zone> zones = sector.getZones();
        return zones.stream().map(Zone::getName).collect(Collectors.toList());
    }
*/
}

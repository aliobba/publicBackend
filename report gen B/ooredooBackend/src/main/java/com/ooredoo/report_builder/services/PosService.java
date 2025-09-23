package com.ooredoo.report_builder.services;

import com.ooredoo.report_builder.entity.POS;
import com.ooredoo.report_builder.entity.Region;
import com.ooredoo.report_builder.entity.Zone;
import com.ooredoo.report_builder.enums.UserType;
import com.ooredoo.report_builder.handler.ResourceNotFoundException;
import com.ooredoo.report_builder.repo.POSRepository;
import com.ooredoo.report_builder.repo.RegionRepository;
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
public class PosService {
    @Autowired
    private POSRepository posRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RegionRepository regionRepository;

    public PosService() {
    }

    public List<POS> findAll() {
        return posRepository.findAll();
    }

    public Optional<POS> findById(Integer id) {
        return posRepository.findById(id);
    }

    public List<POS> findByRegionId(Integer regionId) {
        return posRepository.findByRegionId(regionId);
    }

    public Optional<POS> findByHeadOfPOSId(Integer headOfPOSId) {
        return posRepository.findByHeadOfPOSId(headOfPOSId);
    }

    public POS save(POS pos) {
        User manager = userRepository.findById(pos.getHeadOfPOS().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
        Region region = regionRepository.findById(pos.getRegion().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Region not found"));
        POS pos1  = POS.builder()
                .name(pos.getName())
                .headOfPOS(manager)
                .region(region)
                .build();
        validatePOSHead(pos1);
        return posRepository.save(pos1);
    }

    public void deleteById(Integer id) {
        validatePOSDeletion(id);
        posRepository.deleteById(id);
    }

    public List<POS> findPOSWithoutHead() {
        return posRepository.findByHeadOfPOSIsNull();
    }

    private void validatePOSHead(POS pos) {
        if (pos.getHeadOfPOS().getId() != null) {
            User manager = userRepository.findById(pos.getHeadOfPOS().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Manager not found"));
            if (!manager.getUserType().equals(UserType.HEAD_OF_POS)) {
                throw new IllegalArgumentException("User must have HEAD_OF_POS type to be assigned as head of POS");
            }

            if (pos.getId() == null || !pos.getId().equals(getCurrentPOSIdForHead(pos.getHeadOfPOS().getId()))) {
                if (posRepository.existsByHeadOfPOSId(pos.getHeadOfPOS().getId())) {
                    throw new IllegalStateException("User is already assigned as head of another POS");
                }
            }
        }
    }

    private Integer getCurrentPOSIdForHead(Integer headId) {
        List<POS> posList = posRepository.findAll();
        return posList.stream()
                .filter(p -> p.getHeadOfPOS() != null && p.getHeadOfPOS().getId().equals(headId))
                .map(POS::getId)
                .findFirst()
                .orElse(null);
    }

    private void validatePOSDeletion(Integer posId) {
        Optional<POS> pos = findById(posId);
        if (pos.isPresent() && pos.get().getHeadOfPOS()!=null) {
            throw new IllegalStateException("Cannot delete POS: has users assigned. Reassign users first.");
        }
    }
}

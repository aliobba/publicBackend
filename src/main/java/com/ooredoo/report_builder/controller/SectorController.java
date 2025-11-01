package com.ooredoo.report_builder.controller;

import com.ooredoo.report_builder.entity.POS;
import com.ooredoo.report_builder.entity.Sector;
import com.ooredoo.report_builder.services.PosService;
import com.ooredoo.report_builder.services.SectorService;
import com.ooredoo.report_builder.services.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sectors")
@CrossOrigin(origins = "*")
public class SectorController {

    @Autowired
    private SectorService sectorService;
    @Autowired
    private ZoneService zoneService;

    @Autowired
    private PosService posService;

    @GetMapping
    public ResponseEntity<List<Sector>> getAllSectors() {
        return ResponseEntity.ok(sectorService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Sector> getSectorById(@PathVariable Integer id) {
        Optional<Sector> sector = sectorService.findById(id);
        return sector.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*@GetMapping("/enterprise/{enterpriseId}")
    public ResponseEntity<List<Sector>> getSectorsByEnterpriseId(@PathVariable Integer enterpriseId) {
        return ResponseEntity.ok(sectorService.findByEnterpriseId(enterpriseId));
    }*/

    @GetMapping("/without-head")
    public ResponseEntity<List<Sector>> getSectorsWithoutHead() {
        return ResponseEntity.ok(sectorService.findSectorsWithoutHead());
    }

    @GetMapping("/SectorByZone/{zoneId}")
    public ResponseEntity<List<Sector>> getSectorByZoneId(@PathVariable Integer zoneId) {
        return ResponseEntity.ok(sectorService.findByZoneId(zoneId));
    }

    @GetMapping("/posBySector/{sectorId}")
    public ResponseEntity<List<POS>> getPOSBySectorId(@PathVariable Integer sectorId) {
        return ResponseEntity.ok(posService.findBySectorId(sectorId));
    }

    @PostMapping("/create-Sector")
    public ResponseEntity<?> createSector(@RequestBody Sector sector) {
        try {
            Sector savedSector = sectorService.save(sector);
            return ResponseEntity.ok(savedSector);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest()
                    .body(java.util.Map.of("error", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(java.util.Map.of("error", "Error creating sector: " + e.getMessage()));
        }
    }


    @PutMapping("/updateSector/{id}")
    public ResponseEntity<?> updateSector(@PathVariable Integer id, @RequestBody Sector sector) {
        try {
            if (!sectorService.findById(id).isPresent()) {
                return ResponseEntity.notFound().build();
            }
            sector.setId(id);
            Sector updatedSector = sectorService.save(sector);
            return ResponseEntity.ok(updatedSector);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest()
                    .body(java.util.Map.of("error", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(java.util.Map.of("error", "Error updating sector: " + e.getMessage()));
        }
    }

    @DeleteMapping("/deleteSector/{id}")
    public ResponseEntity<Void> deleteSector(@PathVariable Integer id) {
        try {
            if (!sectorService.findById(id).isPresent()) {
                return ResponseEntity.notFound().build();
            }
            sectorService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

package com.ooredoo.report_builder.controller;

import com.ooredoo.report_builder.entity.Zone;
import com.ooredoo.report_builder.services.RegionService;
import com.ooredoo.report_builder.services.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/zones")
@CrossOrigin(origins = "*")
public class ZoneController {

    @Autowired
    private ZoneService zoneService;
    @Autowired
    private RegionService regionService;

    @GetMapping
    public ResponseEntity<List<Zone>> getAllZones() {
        return ResponseEntity.ok(zoneService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Zone> getZoneById(@PathVariable Integer id) {
        Optional<Zone> zone = zoneService.findById(id);
        return zone.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/zoneByRegion/regions/{regionId}")
    public ResponseEntity<List<Zone>> getZonesByRegionId(@PathVariable Integer regionId) {
        return ResponseEntity.ok(zoneService.findByRegionId(regionId));
    }

    /*@GetMapping("/without-head")
    public ResponseEntity<List<Zone>> getZonesWithoutHead() {
        return ResponseEntity.ok(zoneService.findZonesWithoutHead());
    }*/

    @PostMapping("/create-Zone")
    public ResponseEntity<?> createZone(@RequestBody Zone zone) {
        try {
            Zone savedZone = zoneService.save(zone);
            return ResponseEntity.ok(savedZone);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest()
                    .body(java.util.Map.of("error", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(java.util.Map.of("error", "Error creating zone: " + e.getMessage()));
        }
    }

    @PutMapping("/updateZone/{id}")
    public ResponseEntity<?> updateZone(@PathVariable Integer id, @RequestBody Zone zone) {
        try {
            if (!zoneService.findById(id).isPresent()) {
                return ResponseEntity.notFound().build();
            }
            zone.setId(id);
            Zone updatedZone = zoneService.save(zone);
            return ResponseEntity.ok(updatedZone);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest()
                    .body(java.util.Map.of("error", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(java.util.Map.of("error", "Error updating zone: " + e.getMessage()));
        }
    }

    @DeleteMapping("/deleteZone/{id}")
    public ResponseEntity<Void> deleteZone(@PathVariable Integer id) {
        try {
            if (!zoneService.findById(id).isPresent()) {
                return ResponseEntity.notFound().build();
            }
            zoneService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

package com.ooredoo.report_builder.controller;

import com.ooredoo.report_builder.entity.POS;
import com.ooredoo.report_builder.services.PosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pos")
@CrossOrigin(origins = "*")
public class POSController {

    @Autowired
    private PosService posService;

    @GetMapping
    public ResponseEntity<List<POS>> getAllPOS() {
        return ResponseEntity.ok(posService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<POS> getPOSById(@PathVariable Integer id) {
        Optional<POS> pos = posService.findById(id);
        return pos.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/posBySector/sector/{sectorId}")
    public ResponseEntity<List<POS>> getPOSBySectorId(@PathVariable Integer sectorId) {
        return ResponseEntity.ok(posService.findBySectorId(sectorId));
    }

    @GetMapping("/users/{headOfPOSId}")
    public ResponseEntity<Optional<POS>> getPOSByHeadId(@PathVariable Integer headOfPOSId) {
        return ResponseEntity.ok(posService.findByHeadOfPOSId(headOfPOSId));
    }

    @GetMapping("/without-head")
    public ResponseEntity<List<POS>> getPOSWithoutHead() {
        return ResponseEntity.ok(posService.findPOSWithoutHead());
    }

    @PostMapping("/create-POS")
    public ResponseEntity<?> createPOS(@RequestBody POS pos) {
        try {
            POS savedPOS = posService.save(pos);
            return ResponseEntity.ok(savedPOS);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest()
                    .body(java.util.Map.of("error", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(java.util.Map.of("error", "Error creating POS: " + e.getMessage()));
        }
    }

    @PutMapping("/updatePOS/{id}")
    public ResponseEntity<?> updatePOS(@PathVariable Integer id, @RequestBody POS pos) {
        try {
            if (!posService.findById(id).isPresent()) {
                return ResponseEntity.notFound().build();
            }
            pos.setId(id);
            POS updatedPOS = posService.save(pos);
            return ResponseEntity.ok(updatedPOS);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest()
                    .body(java.util.Map.of("error", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(java.util.Map.of("error", "Error updating POS: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePOS(@PathVariable Integer id) {
        try {
            if (!posService.findById(id).isPresent()) {
                return ResponseEntity.notFound().build();
            }
            posService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

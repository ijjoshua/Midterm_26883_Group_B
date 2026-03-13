package com.example.nursery.controller;

import com.example.nursery.model.Province;
import com.example.nursery.service.ProvinceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/provinces")
public class ProvinceController {

    private final ProvinceService provinceService;

    public ProvinceController(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @PostMapping
    public ResponseEntity<Province> createProvince(@RequestBody Province province) {
        return ResponseEntity.ok(provinceService.saveProvince(province));
    }

    @GetMapping
    public ResponseEntity<List<Province>> getAllProvinces() {
        return ResponseEntity.ok(provinceService.getAllProvinces());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProvinceById(@PathVariable Long id) {
        Optional<Province> province = provinceService.getProvinceById(id);
        return province.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProvince(@PathVariable Long id, @RequestBody Province province) {
        try {
            return ResponseEntity.ok(provinceService.updateProvince(id, province));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProvince(@PathVariable Long id) {
        provinceService.deleteProvince(id);
        return ResponseEntity.noContent().build();
    }
}


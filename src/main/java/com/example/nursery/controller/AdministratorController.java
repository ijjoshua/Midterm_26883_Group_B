package com.example.nursery.controller;

import com.example.nursery.model.Administrator;
import com.example.nursery.service.AdministratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/administrators")
public class AdministratorController {

    private final AdministratorService administratorService;

    public AdministratorController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @PostMapping
    public ResponseEntity<Administrator> createAdministrator(@RequestBody Administrator administrator) {
        return ResponseEntity.ok(administratorService.saveAdministrator(administrator));
    }

    @GetMapping
    public ResponseEntity<List<Administrator>> getAllAdministrators() {
        return ResponseEntity.ok(administratorService.getAllAdministrators());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAdministratorById(@PathVariable Long id) {
        Optional<Administrator> admin = administratorService.getAdministratorById(id);
        return admin.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAdministrator(@PathVariable Long id, @RequestBody Administrator administrator) {
        try {
            return ResponseEntity.ok(administratorService.updateAdministrator(id, administrator));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministrator(@PathVariable Long id) {
        administratorService.deleteAdministrator(id);
        return ResponseEntity.noContent().build();
    }
}


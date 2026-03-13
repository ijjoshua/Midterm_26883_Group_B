package com.example.nursery.service;

import com.example.nursery.model.Administrator;
import com.example.nursery.repository.AdministratorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AdministratorService {

    private final AdministratorRepository administratorRepository;

    public AdministratorService(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    public Administrator saveAdministrator(Administrator administrator) {
        return administratorRepository.save(administrator);
    }

    public List<Administrator> getAllAdministrators() {
        return administratorRepository.findAll();
    }

    public Optional<Administrator> getAdministratorById(Long id) {
        return administratorRepository.findById(id);
    }

    @Transactional
    public Administrator updateAdministrator(Long id, Administrator updatedAdmin) {
        return administratorRepository.findById(id)
                .map(existing -> {
                    existing.setUsername(updatedAdmin.getUsername());
                    existing.setEmail(updatedAdmin.getEmail());
                    existing.setPassword(updatedAdmin.getPassword());
                    existing.setRole(updatedAdmin.getRole());
                    return administratorRepository.save(existing);
                })
                .orElseThrow(() -> new IllegalArgumentException("Administrator not found"));
    }

    @Transactional
    public void deleteAdministrator(Long id) {
        administratorRepository.deleteById(id);
    }
}


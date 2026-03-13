package com.example.nursery.service;

import com.example.nursery.model.Province;
import com.example.nursery.repository.ProvinceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinceService {

    private final ProvinceRepository provinceRepository;

    public ProvinceService(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    public Province saveProvince(Province province) {
        return provinceRepository.save(province);
    }

    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }

    public Optional<Province> getProvinceById(Long id) {
        return provinceRepository.findById(id);
    }

    @Transactional
    public Province updateProvince(Long id, Province updatedProvince) {
        return provinceRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedProvince.getName());
                    existing.setCode(updatedProvince.getCode());
                    return provinceRepository.save(existing);
                })
                .orElseThrow(() -> new IllegalArgumentException("Province not found"));
    }

    @Transactional
    public void deleteProvince(Long id) {
        provinceRepository.deleteById(id);
    }
}


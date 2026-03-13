package com.example.nursery.service;

import com.example.nursery.model.Location;
import com.example.nursery.model.Province;
import com.example.nursery.repository.LocationRepository;
import com.example.nursery.repository.ProvinceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private final LocationRepository locationRepository;
    private final ProvinceRepository provinceRepository;

    public LocationService(LocationRepository locationRepository, ProvinceRepository provinceRepository) {
        this.locationRepository = locationRepository;
        this.provinceRepository = provinceRepository;
    }

    @Transactional
    public Location saveLocationWithProvince(Location location, Long provinceId) {
        Province province = provinceRepository.findById(provinceId)
                .orElseThrow(() -> new IllegalArgumentException("Province not found"));
                
        location.setProvince(province);
        return locationRepository.save(location);
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Optional<Location> getLocationById(Long id) {
        return locationRepository.findById(id);
    }

    @Transactional
    public Location updateLocation(Long id, Location updatedLocation) {
        return locationRepository.findById(id)
                .map(existing -> {
                    existing.setDistrict(updatedLocation.getDistrict());
                    existing.setSector(updatedLocation.getSector());
                    existing.setCell(updatedLocation.getCell());
                    existing.setVillage(updatedLocation.getVillage());
                    existing.setProvince(updatedLocation.getProvince());
                    return locationRepository.save(existing);
                })
                .orElseThrow(() -> new IllegalArgumentException("Location not found"));
    }

    @Transactional
    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }
}

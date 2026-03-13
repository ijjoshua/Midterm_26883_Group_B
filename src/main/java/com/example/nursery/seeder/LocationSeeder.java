package com.example.nursery.seeder;

import com.example.nursery.model.Location;
import com.example.nursery.model.Province;
import com.example.nursery.repository.LocationRepository;
import com.example.nursery.repository.ProvinceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
public class LocationSeeder implements CommandLineRunner {

    private final ProvinceRepository provinceRepository;
    private final LocationRepository locationRepository;

    public LocationSeeder(ProvinceRepository provinceRepository, LocationRepository locationRepository) {
        this.provinceRepository = provinceRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (provinceRepository.count() == 0) {
            System.out.println("Seeding Rwanda Location Data...");

            Province kigali = new Province();
            kigali.setName("Kigali City");
            kigali.setCode("KGL");
            kigali = provinceRepository.save(kigali);

            seedGasabo(kigali);
            seedKicukiro(kigali);
            seedNyarugenge(kigali);

            System.out.println("Seeding Complete.");
        }
    }

    private void seedGasabo(Province kigali) {
        // Remera Sector
        createVillage(kigali, "Gasabo", "Remera", "Rukiri I", "Village A");
        createVillage(kigali, "Gasabo", "Remera", "Rukiri I", "Village B");
        createVillage(kigali, "Gasabo", "Remera", "Rukiri I", "Village C");

        createVillage(kigali, "Gasabo", "Remera", "Rukiri II", "Village A");
        createVillage(kigali, "Gasabo", "Remera", "Rukiri II", "Village B");

        createVillage(kigali, "Gasabo", "Remera", "Nyabisindu", "Village A");
        createVillage(kigali, "Gasabo", "Remera", "Nyabisindu", "Village B");

        // Kacyiru Sector
        createVillage(kigali, "Gasabo", "Kacyiru", "Kamatamu", "Village A");
        createVillage(kigali, "Gasabo", "Kacyiru", "Kamatamu", "Village B");

        createVillage(kigali, "Gasabo", "Kacyiru", "Kagugu", "Village A");
        createVillage(kigali, "Gasabo", "Kacyiru", "Kagugu", "Village B");
    }

    private void seedKicukiro(Province kigali) {
        // Kagarama Sector
        createVillage(kigali, "Kicukiro", "Kagarama", "Kanserege", "Village A");
        createVillage(kigali, "Kicukiro", "Kagarama", "Kanserege", "Village B");

        createVillage(kigali, "Kicukiro", "Kagarama", "Muyange", "Village A");
        createVillage(kigali, "Kicukiro", "Kagarama", "Muyange", "Village B");

        // Niboye Sector
        createVillage(kigali, "Kicukiro", "Niboye", "Niboye", "Village A");
        createVillage(kigali, "Kicukiro", "Niboye", "Niboye", "Village B");

        createVillage(kigali, "Kicukiro", "Niboye", "Nyakabanda", "Village A");
        createVillage(kigali, "Kicukiro", "Niboye", "Nyakabanda", "Village B");
    }

    private void seedNyarugenge(Province kigali) {
        // Muhima Sector
        createVillage(kigali, "Nyarugenge", "Muhima", "Nyabugogo", "Village A");
        createVillage(kigali, "Nyarugenge", "Muhima", "Nyabugogo", "Village B");

        createVillage(kigali, "Nyarugenge", "Muhima", "Kabasengerezi", "Village A");
        createVillage(kigali, "Nyarugenge", "Muhima", "Kabasengerezi", "Village B");

        // Nyamirambo Sector
        createVillage(kigali, "Nyarugenge", "Nyamirambo", "Cyivugiza", "Village A");
        createVillage(kigali, "Nyarugenge", "Nyamirambo", "Cyivugiza", "Village B");

        createVillage(kigali, "Nyarugenge", "Nyamirambo", "Rugarama", "Village A");
        createVillage(kigali, "Nyarugenge", "Nyamirambo", "Rugarama", "Village B");
    }

    private void createVillage(Province province, String district, String sector, String cell, String village) {
        Location location = new Location();
        location.setProvince(province);
        location.setDistrict(district);
        location.setSector(sector);
        location.setCell(cell);
        location.setVillage(village);
        locationRepository.save(location);
    }
}

package com.example.nursery.service;

import com.example.nursery.model.SchoolClass;
import com.example.nursery.repository.SchoolClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolClassService {

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    public List<SchoolClass> getAllSchoolClasses() {
        return schoolClassRepository.findAll();
    }

    public Optional<SchoolClass> getSchoolClassById(Long id) {
        return schoolClassRepository.findById(id);
    }

    public SchoolClass saveSchoolClass(SchoolClass schoolClass) {
        return schoolClassRepository.save(schoolClass);
    }

    public void deleteSchoolClass(Long id) {
        schoolClassRepository.deleteById(id);
    }
}

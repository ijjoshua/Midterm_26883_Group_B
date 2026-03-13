package com.example.nursery.controller;

import com.example.nursery.model.SchoolClass;
import com.example.nursery.service.SchoolClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/classes")
@CrossOrigin(origins = "*")
public class SchoolClassController {

    @Autowired
    private SchoolClassService schoolClassService;

    @GetMapping
    public List<SchoolClass> getAllSchoolClasses() {
        return schoolClassService.getAllSchoolClasses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchoolClass> getSchoolClassById(@PathVariable Long id) {
        Optional<SchoolClass> schoolClass = schoolClassService.getSchoolClassById(id);
        return schoolClass.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public SchoolClass createSchoolClass(@RequestBody SchoolClass schoolClass) {
        return schoolClassService.saveSchoolClass(schoolClass);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SchoolClass> updateSchoolClass(@PathVariable Long id, @RequestBody SchoolClass schoolClassDetails) {
        Optional<SchoolClass> schoolClassOptional = schoolClassService.getSchoolClassById(id);
        
        if (schoolClassOptional.isPresent()) {
            SchoolClass schoolClass = schoolClassOptional.get();
            schoolClass.setName(schoolClassDetails.getName());
            schoolClass.setDescription(schoolClassDetails.getDescription());
            SchoolClass updatedSchoolClass = schoolClassService.saveSchoolClass(schoolClass);
            return ResponseEntity.ok(updatedSchoolClass);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchoolClass(@PathVariable Long id) {
        schoolClassService.deleteSchoolClass(id);
        return ResponseEntity.noContent().build();
    }
}

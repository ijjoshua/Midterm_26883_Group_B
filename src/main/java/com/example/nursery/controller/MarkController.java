package com.example.nursery.controller;

import com.example.nursery.model.Mark;
import com.example.nursery.service.MarkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/marks")
public class MarkController {

    private final MarkService markService;

    public MarkController(MarkService markService) {
        this.markService = markService;
    }

    @PostMapping
    public ResponseEntity<Mark> createMark(@RequestBody Mark mark) {
        return ResponseEntity.ok(markService.saveMark(mark));
    }

    @GetMapping
    public ResponseEntity<List<Mark>> getAllMarks() {
        return ResponseEntity.ok(markService.getAllMarks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMarkById(@PathVariable Long id) {
        Optional<Mark> mark = markService.getMarkById(id);
        return mark.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMark(@PathVariable Long id, @RequestBody Mark mark) {
        try {
            return ResponseEntity.ok(markService.updateMark(id, mark));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMark(@PathVariable Long id) {
        markService.deleteMark(id);
        return ResponseEntity.noContent().build();
    }
}


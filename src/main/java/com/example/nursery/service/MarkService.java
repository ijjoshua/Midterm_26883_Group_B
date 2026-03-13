package com.example.nursery.service;

import com.example.nursery.model.Mark;
import com.example.nursery.repository.MarkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MarkService {

    private final MarkRepository markRepository;

    public MarkService(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    public Mark saveMark(Mark mark) {
        return markRepository.save(mark);
    }

    public List<Mark> getAllMarks() {
        return markRepository.findAll();
    }

    public Optional<Mark> getMarkById(Long id) {
        return markRepository.findById(id);
    }

    @Transactional
    public Mark updateMark(Long id, Mark updatedMark) {
        return markRepository.findById(id)
                .map(existing -> {
                    existing.setScore(updatedMark.getScore());
                    existing.setStudent(updatedMark.getStudent());
                    return markRepository.save(existing);
                })
                .orElseThrow(() -> new IllegalArgumentException("Mark not found"));
    }

    @Transactional
    public void deleteMark(Long id) {
        markRepository.deleteById(id);
    }
}


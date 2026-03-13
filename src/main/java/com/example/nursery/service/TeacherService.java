package com.example.nursery.service;

import com.example.nursery.model.Teacher;
import com.example.nursery.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Optional<Teacher> getTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

    @Transactional
    public Teacher updateTeacher(Long id, Teacher updatedTeacher) {
        return teacherRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedTeacher.getName());
                    existing.setEmail(updatedTeacher.getEmail());
                    existing.setSubject(updatedTeacher.getSubject());
                    return teacherRepository.save(existing);
                })
                .orElseThrow(() -> new IllegalArgumentException("Teacher not found"));
    }

    @Transactional
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }
}


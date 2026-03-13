package com.example.nursery.service;

import com.example.nursery.model.Student;
import com.example.nursery.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student saveStudent(Student student) {
        validateAge(student.getAge());
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Transactional
    public Student updateStudent(Long id, Student updatedStudent) {
        validateAge(updatedStudent.getAge());
        return studentRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedStudent.getName());
                    existing.setAge(updatedStudent.getAge());
                    existing.setParentName(updatedStudent.getParentName());
                    existing.setParentPhoneNumber(updatedStudent.getParentPhoneNumber());
                    existing.setUser(updatedStudent.getUser());
                    existing.setCourses(updatedStudent.getCourses());
                    return studentRepository.save(existing);
                })
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
    }

    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    private void validateAge(int age) {
        if (age < 3 || age > 5) {
            throw new IllegalArgumentException("Student age must be between 3 and 5 years to register.");
        }
    }
}


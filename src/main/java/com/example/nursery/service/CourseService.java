package com.example.nursery.service;

import com.example.nursery.model.Course;
import com.example.nursery.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    @Transactional
    public Course updateCourse(Long id, Course updatedCourse) {
        return courseRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(updatedCourse.getTitle());
                    existing.setDescription(updatedCourse.getDescription());
                    return courseRepository.save(existing);
                })
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));
    }

    @Transactional
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}


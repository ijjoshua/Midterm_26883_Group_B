package com.example.nursery.repository;

import com.example.nursery.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByStudentId(Long studentId);
    List<Attendance> findByTeacherId(Long teacherId);
    List<Attendance> findByDate(LocalDate date);
}

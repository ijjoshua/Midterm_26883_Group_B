package com.example.nursery.controller;

import com.example.nursery.model.Attendance;
import com.example.nursery.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attendances")
@CrossOrigin(origins = "*")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping
    public List<Attendance> getAllAttendances() {
        return attendanceService.getAllAttendances();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attendance> getAttendanceById(@PathVariable Long id) {
        Optional<Attendance> attendance = attendanceService.getAttendanceById(id);
        return attendance.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/student/{studentId}")
    public List<Attendance> getAttendancesByStudentId(@PathVariable Long studentId) {
        return attendanceService.getAttendancesByStudentId(studentId);
    }

    @GetMapping("/teacher/{teacherId}")
    public List<Attendance> getAttendancesByTeacherId(@PathVariable Long teacherId) {
        return attendanceService.getAttendancesByTeacherId(teacherId);
    }

    @GetMapping("/date")
    public List<Attendance> getAttendancesByDate(@RequestParam("date") String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        return attendanceService.getAttendancesByDate(date);
    }

    @PostMapping
    public Attendance createAttendance(@RequestBody Attendance attendance) {
        return attendanceService.saveAttendance(attendance);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attendance> updateAttendance(@PathVariable Long id, @RequestBody Attendance attendanceDetails) {
        Optional<Attendance> attendanceOptional = attendanceService.getAttendanceById(id);
        
        if (attendanceOptional.isPresent()) {
            Attendance attendance = attendanceOptional.get();
            attendance.setDate(attendanceDetails.getDate());
            attendance.setStatus(attendanceDetails.getStatus());
            attendance.setRemarks(attendanceDetails.getRemarks());
            attendance.setStudent(attendanceDetails.getStudent());
            attendance.setTeacher(attendanceDetails.getTeacher());
            
            Attendance updatedAttendance = attendanceService.saveAttendance(attendance);
            return ResponseEntity.ok(updatedAttendance);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
        return ResponseEntity.noContent().build();
    }
}

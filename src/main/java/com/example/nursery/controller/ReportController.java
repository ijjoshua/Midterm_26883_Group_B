package com.example.nursery.controller;

import com.example.nursery.model.Report;
import com.example.nursery.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "*")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable Long id) {
        Optional<Report> report = reportService.getReportById(id);
        return report.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/student/{studentId}")
    public List<Report> getReportsByStudentId(@PathVariable Long studentId) {
        return reportService.getReportsByStudentId(studentId);
    }

    @PostMapping
    public Report createReport(@RequestBody Report report) {
        return reportService.saveReport(report);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Report> updateReport(@PathVariable Long id, @RequestBody Report reportDetails) {
        Optional<Report> reportOptional = reportService.getReportById(id);
        
        if (reportOptional.isPresent()) {
            Report report = reportOptional.get();
            report.setTitle(reportDetails.getTitle());
            report.setContent(reportDetails.getContent());
            report.setDate(reportDetails.getDate());
            report.setStudent(reportDetails.getStudent());
            
            Report updatedReport = reportService.saveReport(report);
            return ResponseEntity.ok(updatedReport);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);
        return ResponseEntity.noContent().build();
    }
}

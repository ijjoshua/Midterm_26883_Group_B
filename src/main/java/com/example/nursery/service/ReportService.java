package com.example.nursery.service;

import com.example.nursery.model.Report;
import com.example.nursery.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Optional<Report> getReportById(Long id) {
        return reportRepository.findById(id);
    }

    public List<Report> getReportsByStudentId(Long studentId) {
        return reportRepository.findByStudentId(studentId);
    }

    public Report saveReport(Report report) {
        if (report.getDate() == null) {
            report.setDate(LocalDate.now());
        }
        return reportRepository.save(report);
    }

    public void deleteReport(Long id) {
        reportRepository.deleteById(id);
    }
}

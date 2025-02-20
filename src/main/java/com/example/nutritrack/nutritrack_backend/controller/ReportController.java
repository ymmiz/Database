package com.example.nutritrack.nutritrack_backend.controller;


import com.example.nutritrack.nutritrack_backend.dto.report.ReportRequestDTO;
import com.example.nutritrack.nutritrack_backend.dto.report.ReportResponseDTO;
import com.example.nutritrack.nutritrack_backend.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<ReportResponseDTO> createReport(@RequestBody ReportRequestDTO reportRequestDTO) {
        return ResponseEntity.ok(reportService.createReport(reportRequestDTO));
    }

    @GetMapping("/{reportId}")
    public ResponseEntity<ReportResponseDTO> getReportById(@PathVariable("reportId") Long reportId) {
        return ResponseEntity.ok(reportService.getReportById(reportId));
    }

    @GetMapping
    public ResponseEntity<List<ReportResponseDTO>> getAllReports() {
        return ResponseEntity.ok(reportService.getAllReports());
    }

    @GetMapping("/admin/{adminId}")
    public ResponseEntity<List<ReportResponseDTO>> getReportsByAdmin(@PathVariable("adminId") Long adminId) {
        return ResponseEntity.ok(reportService.getReportsByAdmin(adminId));
    }

    @DeleteMapping("/{reportId}")
    public ResponseEntity<Void> deleteReport(@PathVariable("reportId") Long reportId) {
        reportService.deleteReport(reportId);
        return ResponseEntity.noContent().build();
    }
}

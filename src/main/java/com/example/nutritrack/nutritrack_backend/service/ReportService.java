package com.example.nutritrack.nutritrack_backend.service;

import com.example.nutritrack.nutritrack_backend.dto.report.ReportRequestDTO;
import com.example.nutritrack.nutritrack_backend.dto.report.ReportResponseDTO;

import java.util.List;

public interface ReportService {
    ReportResponseDTO createReport(ReportRequestDTO reportRequestDTO);
    ReportResponseDTO getReportById(Long reportId);
    List<ReportResponseDTO> getAllReports();
    List<ReportResponseDTO> getReportsByAdmin(Long adminId);
    void deleteReport(Long reportId);
}

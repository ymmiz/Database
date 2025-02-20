package com.example.nutritrack.nutritrack_backend.service.impl;

import com.example.nutritrack.nutritrack_backend.dto.report.ReportRequestDTO;
import com.example.nutritrack.nutritrack_backend.dto.report.ReportResponseDTO;
import com.example.nutritrack.nutritrack_backend.mapper.ReportMapper;
import com.example.nutritrack.nutritrack_backend.model.Admin;
import com.example.nutritrack.nutritrack_backend.model.Report;
import com.example.nutritrack.nutritrack_backend.repository.AdminRepository;
import com.example.nutritrack.nutritrack_backend.repository.ReportRepository;
import com.example.nutritrack.nutritrack_backend.service.ReportService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final AdminRepository adminRepository;
    private final ReportMapper reportMapper;

    public ReportServiceImpl(ReportRepository reportRepository, AdminRepository adminRepository, ReportMapper reportMapper) {
        this.reportRepository = reportRepository;
        this.adminRepository = adminRepository;
        this.reportMapper = reportMapper;
    }

    @Override
    public ReportResponseDTO createReport(ReportRequestDTO reportRequestDTO) {
        Admin admin = adminRepository.findById(reportRequestDTO.getGeneratedById())
                .orElseThrow(() -> new EntityNotFoundException("Admin not found"));

        Report report = reportMapper.toEntity(reportRequestDTO, admin);
        report = reportRepository.save(report);
        return reportMapper.toResponseDto(report);
    }

    @Override
    public ReportResponseDTO getReportById(Long reportId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new EntityNotFoundException("Report not found"));
        return reportMapper.toResponseDto(report);
    }

    @Override
    public List<ReportResponseDTO> getAllReports() {
        List<Report> reports = reportRepository.findAll();
        return reportMapper.toResponseDtoList(reports);
    }

    @Override
    public List<ReportResponseDTO> getReportsByAdmin(Long adminId) {
        List<Report> reports = reportRepository.findReportsByAdmin(adminId);
        return reportMapper.toResponseDtoList(reports);
    }

    @Override
    public void deleteReport(Long reportId) {
        if (!reportRepository.existsById(reportId)) {
            throw new EntityNotFoundException("Report not found");
        }
        reportRepository.deleteById(reportId);
    }
}

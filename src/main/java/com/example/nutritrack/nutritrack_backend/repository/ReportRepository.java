package com.example.nutritrack.nutritrack_backend.repository;

import com.example.nutritrack.nutritrack_backend.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long>{

    @Transactional
    @Query(value = "INSERT INTO reports (date, content, type, generated_by) " +
            "VALUES (:date, :content, :type, :generatedBy) RETURNING report_id", nativeQuery = true)
    Long createReport(@Param("date") String date,
                      @Param("content") String content,
                      @Param("type") String type,
                      @Param("generatedBy") Long generatedBy);

    // Find Report by ID
    @Query(value = "SELECT * FROM reports WHERE report_id = :reportId", nativeQuery = true)
    Optional<Report> findByReportId(@Param("reportId") Long reportId);

    // Get All Reports
    @Query(value = "SELECT * FROM reports", nativeQuery = true)
    List<Report> getAllReports();

    // Get Reports by Admin
    @Query(value = "SELECT * FROM reports WHERE generated_by = :adminId", nativeQuery = true)
    List<Report> findReportsByAdmin(@Param("adminId") Long adminId);

    // Delete Report
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM reports WHERE report_id = :reportId", nativeQuery = true)
    void deleteReport(@Param("reportId") Long reportId);
}

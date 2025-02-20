package com.example.nutritrack.nutritrack_backend.dto.report;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReportRequestDTO {
    private LocalDate date;
    private String content;
    private String type;
    private Long generatedById;
}

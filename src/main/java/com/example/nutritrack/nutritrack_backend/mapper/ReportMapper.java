package com.example.nutritrack.nutritrack_backend.mapper;

import com.example.nutritrack.nutritrack_backend.dto.report.ReportRequestDTO;
import com.example.nutritrack.nutritrack_backend.dto.report.ReportResponseDTO;
import com.example.nutritrack.nutritrack_backend.model.Admin;
import com.example.nutritrack.nutritrack_backend.model.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    @Mapping(target = "generatedBy", source = "admin")
    Report toEntity(ReportRequestDTO dto, Admin admin);

    @Mapping(target = "generatedById", source = "generatedBy.adminId")
    ReportResponseDTO toResponseDto(Report report);

    List<ReportResponseDTO> toResponseDtoList(List<Report> reports);
}

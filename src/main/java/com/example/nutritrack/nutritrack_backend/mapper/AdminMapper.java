package com.example.nutritrack.nutritrack_backend.mapper;

import com.example.nutritrack.nutritrack_backend.dto.admin.AdminRequestDTO;
import com.example.nutritrack.nutritrack_backend.dto.admin.AdminResponseDTO;
import com.example.nutritrack.nutritrack_backend.dto.admin.MasterAdminDTO;
import com.example.nutritrack.nutritrack_backend.model.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);

    AdminResponseDTO toDto(Admin admin);
    Admin toEntity(AdminRequestDTO adminRequestDto);
    Admin toEntity(MasterAdminDTO masterAdminDTO);
}

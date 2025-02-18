package com.example.nutritrack.nutritrack_backend.service;

import com.example.nutritrack.nutritrack_backend.dto.admin.AdminRequestDTO;
import com.example.nutritrack.nutritrack_backend.dto.admin.AdminResponseDTO;
import com.example.nutritrack.nutritrack_backend.dto.admin.MasterAdminDTO;

public interface AdminService {

    AdminResponseDTO createAdmin(MasterAdminDTO masterAdminDTO);

    AdminResponseDTO getAdminById(Long adminId);

    AdminResponseDTO updateAdmin(Long adminId, AdminRequestDTO updatedAdmin);
}

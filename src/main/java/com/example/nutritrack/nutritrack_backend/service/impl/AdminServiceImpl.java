package com.example.nutritrack.nutritrack_backend.service.impl;

import com.example.nutritrack.nutritrack_backend.dto.admin.AdminRequestDTO;
import com.example.nutritrack.nutritrack_backend.dto.admin.AdminResponseDTO;
import com.example.nutritrack.nutritrack_backend.dto.admin.MasterAdminDTO;
import com.example.nutritrack.nutritrack_backend.exception.ResourceNotFoundException;
import com.example.nutritrack.nutritrack_backend.mapper.AdminMapper;
import com.example.nutritrack.nutritrack_backend.model.Admin;
import com.example.nutritrack.nutritrack_backend.repository.AdminRepository;
import com.example.nutritrack.nutritrack_backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, AdminMapper adminMapper) {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
    }

    @Override
    public AdminResponseDTO createAdmin(MasterAdminDTO masterAdminDTO) {
        Admin admin = adminMapper.toEntity((AdminRequestDTO) masterAdminDTO);
        Admin savedAdmin = adminRepository.save(admin);
        return adminMapper.toDto(savedAdmin);
    }

    @Override
    public AdminResponseDTO getAdminById(Long adminId) {
        Admin admin = adminRepository.findById(adminId).orElseThrow( () -> new ResourceNotFoundException("Admin is not exist with given id : " +adminId));
        return adminMapper.toDto(admin);
    }

    @Override
    public AdminResponseDTO updateAdmin(Long adminId, AdminRequestDTO updatedAdmin) {
        Admin existingAdmin = adminRepository.findById(adminId).orElseThrow(() -> new ResourceNotFoundException("Admin is not exist with given id : " +adminId));
        existingAdmin.setName(updatedAdmin.getName());
        existingAdmin.setEmail(updatedAdmin.getEmail());
        existingAdmin = adminRepository.save(existingAdmin);
        return adminMapper.toDto(existingAdmin);
    }
}

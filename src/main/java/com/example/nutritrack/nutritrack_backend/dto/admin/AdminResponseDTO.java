package com.example.nutritrack.nutritrack_backend.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponseDTO {

    private Long adminId;
    private String name;
    private String email;
    private String recentActivity;
}

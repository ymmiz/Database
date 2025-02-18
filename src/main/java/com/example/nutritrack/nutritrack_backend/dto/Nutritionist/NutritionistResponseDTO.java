package com.example.nutritrack.nutritrack_backend.dto.Nutritionist;


import com.example.nutritrack.nutritrack_backend.dto.user.MasterUserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NutritionistResponseDTO {

    private String name;
    private String email;
    private String specialization;
    private List<MasterUserDTO> assignedUsers;
}

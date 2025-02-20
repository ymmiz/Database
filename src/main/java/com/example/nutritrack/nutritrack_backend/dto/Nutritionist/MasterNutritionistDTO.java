package com.example.nutritrack.nutritrack_backend.dto.Nutritionist;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MasterNutritionistDTO {
    private Long nutritionistId;
    private String name;
    private String email;
    private String specialization;
}

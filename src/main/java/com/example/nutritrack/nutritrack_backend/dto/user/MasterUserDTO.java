package com.example.nutritrack.nutritrack_backend.dto.user;

import com.example.nutritrack.nutritrack_backend.dto.Nutritionist.MasterNutritionistDTO;
import com.example.nutritrack.nutritrack_backend.model.Nutritionist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
public class MasterUserDTO {
    private String name;
    private String email;
    private int age;
    private double weight;
    private String goal;
    private int totalMealLogs;
    private MasterNutritionistDTO nutritionist;
}

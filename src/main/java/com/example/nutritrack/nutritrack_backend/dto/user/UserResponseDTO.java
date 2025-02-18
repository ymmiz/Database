package com.example.nutritrack.nutritrack_backend.dto.user;

import com.example.nutritrack.nutritrack_backend.dto.MealPlan.MealPlanResponseDTO;
import com.example.nutritrack.nutritrack_backend.dto.Nutritionist.MasterNutritionistDTO;
import com.example.nutritrack.nutritrack_backend.dto.Nutritionist.NutritionistResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserResponseDTO{

    private String name;
    private String email;
    private int age;
    private double weight;
    private String goal;
    private MealPlanResponseDTO mealPlan;
    private MasterNutritionistDTO assignedByNutritionist;;
}

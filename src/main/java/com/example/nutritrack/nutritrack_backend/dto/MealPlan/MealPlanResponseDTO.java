package com.example.nutritrack.nutritrack_backend.dto.MealPlan;

import com.example.nutritrack.nutritrack_backend.dto.Nutritionist.MasterNutritionistDTO;
import com.example.nutritrack.nutritrack_backend.dto.user.MasterUserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealPlanResponseDTO {

    private Long planId;
    private String title;
    private String description;
    private String weeklyMeals;
    private String goal;
    private int calories;
    private LocalDate createdDate;
    private int planDuration;
    private MasterNutritionistDTO createdBy;
    private MasterUserDTO assignedUser;
    private MasterUserDTO nutritionist;

}

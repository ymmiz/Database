package com.example.nutritrack.nutritrack_backend.dto.MealPlan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealPlanRequestDTO {
    private String title;
    private String description;
    private String weeklyMeals;
    private String goal;
    private int calories;
    private int planDuration;
    private Long nutritionistId;
    private Long assignedUserId;
}

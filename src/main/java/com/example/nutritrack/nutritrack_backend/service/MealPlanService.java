package com.example.nutritrack.nutritrack_backend.service;

import com.example.nutritrack.nutritrack_backend.dto.MealPlan.MealPlanRequestDTO;
import com.example.nutritrack.nutritrack_backend.dto.MealPlan.MealPlanResponseDTO;

import java.util.List;

public interface MealPlanService {

    MealPlanResponseDTO createMealPlan(Long nutritionistId, MealPlanRequestDTO mealPlanRequestDTO);

    List<MealPlanResponseDTO> getMealPlansByUser(Long userId);

    List<MealPlanResponseDTO> getMealPlansByNutritionist(Long nutritionistId);

    MealPlanResponseDTO getMealPlanById(Long planId);

    MealPlanResponseDTO updateMealPlan(Long planId, MealPlanRequestDTO mealPlanRequestDTO);

    MealPlanResponseDTO assignMealPlanToUser(Long mealPlanId, Long userId);

    void deleteMealPlan(Long planId);

}

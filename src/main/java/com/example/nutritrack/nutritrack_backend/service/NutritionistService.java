package com.example.nutritrack.nutritrack_backend.service;

import com.example.nutritrack.nutritrack_backend.dto.MealPlan.MealPlanResponseDTO;
import com.example.nutritrack.nutritrack_backend.dto.Nutritionist.NutritionistRequestDTO;
import com.example.nutritrack.nutritrack_backend.dto.Nutritionist.NutritionistResponseDTO;
import com.example.nutritrack.nutritrack_backend.dto.user.UserResponseDTO;

import java.util.List;

public interface NutritionistService {

    NutritionistResponseDTO createNutritionist(NutritionistRequestDTO nutritionistRequestDTO);

    NutritionistResponseDTO getNutritionistById(Long nutritionistId);

    List<NutritionistResponseDTO> getAllNutritionists();

    List<MealPlanResponseDTO> getMealPlansByNutritionist(Long nutritionistId);

    List<UserResponseDTO> getUsersAssignedByNutritionist(Long nutritionistId);

    NutritionistResponseDTO updateNutritionist(Long nutritionistId, NutritionistRequestDTO nutritionistRequestDTO);

    void deleteNutritionist(Long nutritionistId);
}

package com.example.nutritrack.nutritrack_backend.service;

import com.example.nutritrack.nutritrack_backend.dto.meallog.MealLogRequestDTO;
import com.example.nutritrack.nutritrack_backend.dto.meallog.MealLogResponseDTO;
import com.example.nutritrack.nutritrack_backend.model.MealLog;
import com.example.nutritrack.nutritrack_backend.model.constant.MealTypeStatus;

import java.util.List;

public interface MealLogService {

    MealLogResponseDTO createMealLog(MealLogRequestDTO requestDTO);

    List<MealLogResponseDTO> getMealLogsByUser(Long userId);

    List<MealLogResponseDTO> getAllMealLogs();

    void deleteMealLog(Long mealLogId);

}

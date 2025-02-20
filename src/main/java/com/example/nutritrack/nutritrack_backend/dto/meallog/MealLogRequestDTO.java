package com.example.nutritrack.nutritrack_backend.dto.meallog;

import com.example.nutritrack.nutritrack_backend.model.constant.MealTypeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealLogRequestDTO {

    private Long userId;
    private LocalDate logDate;
    private LocalTime logTime;
    private MealTypeStatus mealTypeStatus;
    private boolean planned;
    private int estimatedCalorieCount;
    private String mealDetails;
}

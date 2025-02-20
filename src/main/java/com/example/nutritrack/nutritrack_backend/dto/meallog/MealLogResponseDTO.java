package com.example.nutritrack.nutritrack_backend.dto.meallog;

import com.example.nutritrack.nutritrack_backend.model.constant.MealTypeStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealLogResponseDTO {

    private String userName;
    private LocalDate logDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime logTime;
    private MealTypeStatus mealTypeStatus;
    private boolean planned;
    private int estimatedCalorieCount;
    private String mealDetails;
}

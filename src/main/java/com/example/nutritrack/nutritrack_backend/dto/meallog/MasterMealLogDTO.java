package com.example.nutritrack.nutritrack_backend.dto.meallog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MasterMealLogDTO {
    private Long userId;
    private String userName;
}

package com.example.nutritrack.nutritrack_backend.dto.Nutritionist;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NutritionistRequestDTO{

        private String name;
        private String email;
        private String password;
        private String specialization;
}

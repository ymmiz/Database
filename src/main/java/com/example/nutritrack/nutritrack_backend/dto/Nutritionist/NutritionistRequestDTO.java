package com.example.nutritrack.nutritrack_backend.dto.Nutritionist;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NutritionistRequestDTO extends MasterNutritionistDTO{
        private String password;
}

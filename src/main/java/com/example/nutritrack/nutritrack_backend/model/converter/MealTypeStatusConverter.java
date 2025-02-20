package com.example.nutritrack.nutritrack_backend.model.converter;

import com.example.nutritrack.nutritrack_backend.model.constant.MealTypeStatus;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MealTypeStatusConverter  extends BaseEnumConverter<MealTypeStatus, Integer>{
    public MealTypeStatusConverter() {
        super(MealTypeStatus.class);
    }
}

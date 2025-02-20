package com.example.nutritrack.nutritrack_backend.model.constant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MealTypeStatus implements BaseEnum<Integer> {
    Breakfast(1),
    Lunch(2),
    Dinner(3),
    Snack(4);

    private final int value;

    @Override
    public Integer getValue() {
        return this.value;
    }
}

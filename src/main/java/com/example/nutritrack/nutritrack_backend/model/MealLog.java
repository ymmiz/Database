package com.example.nutritrack.nutritrack_backend.model;

import com.example.nutritrack.nutritrack_backend.model.constant.MealTypeStatus;
import com.example.nutritrack.nutritrack_backend.model.converter.MealTypeStatusConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "meallogs")
public class MealLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_meal_log_user"))
    private User user;

    @Column(nullable = false)
    private LocalDate logDate;

    @Column(nullable = false)
    private LocalTime logTime;

    @Column(nullable = true)
    @Convert(converter = MealTypeStatusConverter.class)
    private MealTypeStatus mealTypeStatus; // e.g., Breakfast, Lunch, Dinner, Snack

    @Column(nullable = false)
    private boolean planned = false;

    @Column(nullable = false)
    private int estimatedCalorieCount;

    @Column(columnDefinition = "TEXT")
    private String mealDetails;
}

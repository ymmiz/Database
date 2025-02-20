package com.example.nutritrack.nutritrack_backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    @Min( value = 1, message = "Age must be greater than 0")
    private int age;

    @Column(nullable = false, precision = 10)
    @DecimalMin(value = "0.1", message = "Weight must be greater than 0")
    private double weight;

    @Column(length = 100)
    private String goal;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    private int totalMealLogs;

    @Column(columnDefinition = "DECIMAL(5,2) DEFAULT 0.0")
    private double weeklyPlanProgress;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nutritionist_id",foreignKey = @ForeignKey(name = "fk_user_nutritionist"))
    @JsonBackReference
    private Nutritionist assignedByNutritionist;

    @OneToOne(mappedBy = "assignedUser")
    private MealPlan mealPlan;
}

package com.example.nutritrack.nutritrack_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mealplans")
public class MealPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false, foreignKey = @ForeignKey(name = "fk_mealplan_nutritionist"))
    private Nutritionist createdBy; // Nutritionist who created the plan

    @OneToOne
    @JoinColumn(name = "assigned_user_id", foreignKey = @ForeignKey(name = "fk_mealplan_user"))
    private User assignedUser; // User assigned to follow the plan

    @Column(columnDefinition = "TEXT")
    private String weeklyMeals;

    @Column(length = 100)
    private String goal;

    @Column(nullable = false)
    private int calories;

    @Column(nullable = false, updatable = false)
    private LocalDate createdDate = LocalDate.now();

    @Column(nullable = false)
    private int planDuration;
}

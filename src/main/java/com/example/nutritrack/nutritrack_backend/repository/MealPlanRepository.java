package com.example.nutritrack.nutritrack_backend.repository;

import com.example.nutritrack.nutritrack_backend.model.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MealPlanRepository extends JpaRepository<MealPlan, Long> {

    @Query(value = "SELECT * FROM mealplans", nativeQuery = true)
    List<MealPlan> getAllMealPlans();

    // Retrieve meal plans assigned to a specific user
    @Query(value = "SELECT * FROM mealplans WHERE assigned_user_id = :userId", nativeQuery = true)
    List<MealPlan> findByAssignedUserId(@Param("userId") Long userId);

    // Retrieve meal plans created by a specific nutritionist
    @Query(value = "SELECT * FROM mealplans WHERE created_by = :nutritionistId", nativeQuery = true)
    List<MealPlan> findByCreatedByNutritionistId(@Param("nutritionistId") Long nutritionistId);

    // Insert a new meal plan (Handled automatically by JpaRepository `save()` method)

    // Assign a meal plan to a different user
    @Transactional
    @Modifying
    @Query(value = "UPDATE mealplans SET assigned_user_id = :assignedUserId WHERE plan_id = :planId", nativeQuery = true)
    int assignMealPlanToUser(@Param("planId") Long planId, @Param("assignedUserId") Long assignedUserId);

    // Update an existing meal plan
    @Modifying
    @Transactional
    @Query(value = "UPDATE mealplans SET title = :title, description = :description, assigned_user_id = :assignedUserId, weekly_meals = :weeklyMeals, " +
            "goal = :goal, calories = :calories, plan_duration = :planDuration WHERE plan_id = :planId", nativeQuery = true)
    int updateMealPlanById(
            @Param("planId") Long planId,
            @Param("title") String title,
            @Param("description") String description,
            @Param("assignedUserId") Long assignedUserId,
            @Param("weeklyMeals") String weeklyMeals,
            @Param("goal") String goal,
            @Param("calories") int calories,
            @Param("planDuration") int planDuration
    );

    // Retrieve a specific meal plan by ID
    @Query(value = "SELECT * FROM mealplans WHERE plan_id = :planId", nativeQuery = true)
    MealPlan findByPlanId(@Param("planId") Long planId);

    // Delete a meal plan by ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM mealplans WHERE plan_id = :planId", nativeQuery = true)
    int deleteMealPlanById(@Param("planId") Long planId);
}

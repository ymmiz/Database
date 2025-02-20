package com.example.nutritrack.nutritrack_backend.repository;

import com.example.nutritrack.nutritrack_backend.model.Nutritionist;
import com.example.nutritrack.nutritrack_backend.model.User;
import com.example.nutritrack.nutritrack_backend.model.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface NutritionistRepository extends JpaRepository<Nutritionist, Long> {

    // Retrieve all nutritionists
    @Query(value = "SELECT * FROM nutritionists", nativeQuery = true)
    List<Nutritionist> getAllNutritionists();

    // Retrieve a specific nutritionist by ID
    @Query(value = "SELECT * FROM nutritionists WHERE nutritionist_id = :nutritionistId", nativeQuery = true)
    Optional<Nutritionist> findByNutritionistId(@Param("nutritionistId") Long nutritionistId);

    // Retrieve users assigned to a specific nutritionist
    @Query(value = "SELECT * FROM users WHERE nutritionist_id = :nutritionistId", nativeQuery = true)
    List<User> findUsersAssignedByNutritionist(@Param("nutritionistId") Long nutritionistId);

    // Retrieve meal plans created by a specific nutritionist
    @Query(value = "SELECT * FROM mealplans WHERE created_by = :nutritionistId", nativeQuery = true)
    List<MealPlan> findMealPlansByNutritionist(@Param("nutritionistId") Long nutritionistId);

    // Insert a new nutritionist (Handled automatically by JpaRepository `save()` method)
    @Transactional
    @Query(value = "INSERT INTO nutritionists (name, email, specialization, password) " +
            "VALUES (:name, :email, :specialization, :password) RETURNING nutritionist_id", nativeQuery = true)
    Long createNutritionist(
            @Param("name") String name,
            @Param("email") String email,
            @Param("specialization") String specialization,
            @Param("password") String password
    );

    // Update an existing nutritionist
    @Modifying
    @Transactional
    @Query(value = "UPDATE nutritionists SET name = :name, email = :email, specialization = :specialization, password = :password " +
            "WHERE nutritionist_id = :nutritionistId", nativeQuery = true)
    int updateNutritionistById(
            @Param("nutritionistId") Long nutritionistId,
            @Param("name") String name,
            @Param("email") String email,
            @Param("specialization") String specialization,
            @Param("password") String password
    );

    // Delete a nutritionist by ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM nutritionists WHERE nutritionist_id = :nutritionistId", nativeQuery = true)
    int deleteNutritionistById(@Param("nutritionistId") Long nutritionistId);
}
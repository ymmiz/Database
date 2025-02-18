package com.example.nutritrack.nutritrack_backend.repository;

import com.example.nutritrack.nutritrack_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u JOIN u.assignedByNutritionist n WHERE n.nutritionistId = :nutritionistId")
    List<User> findUsersAssignedByNutritionist(@Param("nutritionistId") Long nutritionistId);

    @Query("SELECT u FROM User u WHERE u.mealPlan.planId = :mealPlanId")
    Optional<User> findUserByMealPlanId(@Param("mealPlanId") Long mealPlanId);

    @Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<User> findByNameContainingIgnoreCase(String name);
}

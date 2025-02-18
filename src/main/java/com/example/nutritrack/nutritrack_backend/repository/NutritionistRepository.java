package com.example.nutritrack.nutritrack_backend.repository;

import com.example.nutritrack.nutritrack_backend.model.MealPlan;
import com.example.nutritrack.nutritrack_backend.model.Nutritionist;
import com.example.nutritrack.nutritrack_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface NutritionistRepository  extends JpaRepository<Nutritionist, Long> {

    @Query("SELECT u FROM User u WHERE u.assignedByNutritionist.nutritionistId= :nutritionistId")
    List<User> findUsersAssignedByNutritionist(@Param("nutritionistId") Long nutritionistId);

}
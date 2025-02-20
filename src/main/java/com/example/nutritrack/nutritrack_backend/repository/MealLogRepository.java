package com.example.nutritrack.nutritrack_backend.repository;

import com.example.nutritrack.nutritrack_backend.model.MealLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MealLogRepository extends JpaRepository<MealLog, Long> {

    // Retrieve meal logs by user ID
    @Query(value = "SELECT * FROM meallogs WHERE user_id = :userId", nativeQuery = true)
    List<MealLog> findByUserId(@Param("userId") Long userId);


    // Manually insert a new meal log
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO meallogs (user_id, log_date, log_time, meal_type, planned, estimated_calorie_count, meal_details) " +
            "VALUES (:userId, :logDate, :logTime, :mealType, :planned, :estimatedCalorieCount, :mealDetails)", nativeQuery = true)
    void createMealLog(
            @Param("userId") Long userId,
            @Param("logDate") LocalDate logDate,
            @Param("logTime") LocalTime logTime,
            @Param("mealType") String mealType,
            @Param("planned") boolean planned,
            @Param("estimatedCalorieCount") int estimatedCalorieCount,
            @Param("mealDetails") String mealDetails
    );

    // Manually delete a meal log by ID
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM meallogs WHERE log_id = :logId", nativeQuery = true)
    int deleteMealLogById(@Param("logId") Long logId);

    // Retrieve all meal logs
    @Query(value = "SELECT * FROM meallogs", nativeQuery = true)
    List<MealLog> getAllMealLogs();

    @Query(value = "SELECT * FROM meallogs WHERE user_id = :userId", nativeQuery = true)
    List<MealLog> findMealLogsByUserId(Long userId);
}
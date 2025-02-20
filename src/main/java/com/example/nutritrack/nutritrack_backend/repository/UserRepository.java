package com.example.nutritrack.nutritrack_backend.repository;

import com.example.nutritrack.nutritrack_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Retrieve all users
    @Query("SELECT u FROM User u")
    List<User> getAllUsers();

    // Manually insert a user using JPA Query
    @Transactional
    @Query(value = "INSERT INTO users (name, email, password, age, weight, goal, nutritionist_id) " +
            "VALUES (:name, :email, :password, :age, :weight, :goal, :nutritionistId)",
            nativeQuery = true)
    int createUser(
            @Param("name") String name,
            @Param("email") String email,
            @Param("password") String password,
            @Param("age") int age,
            @Param("weight") double weight,
            @Param("goal") String goal,
            @Param("nutritionistId") Long nutritionistId
    );

    // Manually update user details using JPA Query
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.name = :name, u.email = :email, u.password = :password, " +
            "u.age = :age, u.weight = :weight, u.goal = :goal, " +
            "u.assignedByNutritionist = (SELECT n FROM Nutritionist n WHERE n.nutritionistId = :nutritionistId) " +
            "WHERE u.userId = :userId")
    int updateUserById(
            @Param("userId") Long userId,
            @Param("name") String name,
            @Param("email") String email,
            @Param("password") String password,
            @Param("age") int age,
            @Param("weight") double weight,
            @Param("goal") String goal,
            @Param("nutritionistId") Long nutritionistId
    );

    // Retrieve a user by ID
    @Query("SELECT u FROM User u WHERE u.userId = :id")
    Optional<User> getUserById(@Param("id") Long id);

    // Delete a user by ID
    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.userId = :id")
    int deleteUserById(@Param("id") Long id);

    // Search users by name (case-insensitive search)
    @Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<User> searchUsersByName(@Param("name") String name);

    // Find users assigned to a specific nutritionist
    @Query("SELECT u FROM User u WHERE u.assignedByNutritionist.nutritionistId = :nutritionistId")
    List<User> findUsersAssignedByNutritionist(@Param("nutritionistId") Long nutritionistId);

    // Find a user by meal plan ID
    @Query("SELECT u FROM User u WHERE u.mealPlan.planId = :mealPlanId")
    Optional<User> findUserByMealPlanId(@Param("mealPlanId") Long mealPlanId);

    @Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<User> findByNameContainingIgnoreCase(String name);
}
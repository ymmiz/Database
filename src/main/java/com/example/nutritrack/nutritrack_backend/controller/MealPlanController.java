package com.example.nutritrack.nutritrack_backend.controller;

import com.example.nutritrack.nutritrack_backend.dto.MealPlan.MealPlanRequestDTO;
import com.example.nutritrack.nutritrack_backend.dto.MealPlan.MealPlanResponseDTO;
import com.example.nutritrack.nutritrack_backend.service.MealPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mealPlans")
@CrossOrigin("*")
public class MealPlanController {

    @Autowired
    private MealPlanService mealPlanService;

    // Create a meal plan
    @PostMapping("/create/{nutritionistId}")
    public ResponseEntity<MealPlanResponseDTO> createMealPlan(@PathVariable("nutritionistId") Long nutritionistId, @RequestBody MealPlanRequestDTO requestDTO) {
        MealPlanResponseDTO createdMealPlan = mealPlanService.createMealPlan(nutritionistId, requestDTO);
        return ResponseEntity.ok(createdMealPlan);
    }

    // Get meal plans assigned to a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MealPlanResponseDTO>> getMealPlansByUser(@PathVariable("userId") Long userId) {
       return ResponseEntity.ok(mealPlanService.getMealPlansByUser(userId));
    }

    // Get meal plans created by a nutritionist
    @GetMapping("/nutritionist/{nutritionistId}")
    public ResponseEntity<List<MealPlanResponseDTO>> getMealPlansByNutritionist(@PathVariable("nutritionistId") Long nutritionistId) {
        return ResponseEntity.ok(mealPlanService.getMealPlansByNutritionist(nutritionistId));
    }

    // Get a specific meal plan by ID
    @GetMapping("/{planId}")
    public ResponseEntity<MealPlanResponseDTO> getMealPlanById(@PathVariable("planId") Long planId) {
        return ResponseEntity.ok(mealPlanService.getMealPlanById(planId));
    }

    // Update a meal plan
    @PutMapping("/update/{planId}")
    public ResponseEntity<MealPlanResponseDTO> updateMealPlan(@PathVariable("planId") Long planId, @RequestBody MealPlanRequestDTO requestDTO) {
        return ResponseEntity.ok(mealPlanService.updateMealPlan(planId, requestDTO));
    }

    // Assign a meal plan to a user
    @PutMapping("/assign/{mealPlanId}/{userId}")
    public ResponseEntity<MealPlanResponseDTO> assignMealPlanToUser(@PathVariable("userId") Long mealPlanId, @PathVariable Long userId) {
        return ResponseEntity.ok(mealPlanService.assignMealPlanToUser(userId, mealPlanId));
    }

    // Delete a meal plan
    @DeleteMapping("/delete/{planId}")
    public ResponseEntity<String> deleteMealPlan(@PathVariable("planId") Long planId) {
        mealPlanService.deleteMealPlan(planId);
        return ResponseEntity.ok("Deleted");
    }
}

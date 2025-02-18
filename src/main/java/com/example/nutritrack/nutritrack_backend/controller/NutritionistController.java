package com.example.nutritrack.nutritrack_backend.controller;

import com.example.nutritrack.nutritrack_backend.dto.MealPlan.MealPlanResponseDTO;
import com.example.nutritrack.nutritrack_backend.dto.Nutritionist.NutritionistRequestDTO;
import com.example.nutritrack.nutritrack_backend.dto.Nutritionist.NutritionistResponseDTO;
import com.example.nutritrack.nutritrack_backend.dto.user.UserResponseDTO;
import com.example.nutritrack.nutritrack_backend.service.AdminService;
import com.example.nutritrack.nutritrack_backend.service.NutritionistService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nutritionists")
public class NutritionistController {

    private final NutritionistService nutritionistService;

    @Autowired  // Ensure this is present
    public NutritionistController(NutritionistService nutritionistService) {
        this.nutritionistService = nutritionistService;
    }

    @PostMapping
    public ResponseEntity<NutritionistResponseDTO> createNutritionist(@RequestBody NutritionistRequestDTO nutritionistRequestDTO) {
        NutritionistResponseDTO savedNutritionist = nutritionistService.createNutritionist(nutritionistRequestDTO);
        return new ResponseEntity<>(savedNutritionist,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NutritionistResponseDTO> getNutritionistById(@PathVariable("id") Long id) {
        NutritionistResponseDTO responseDTO = nutritionistService.getNutritionistById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<NutritionistResponseDTO>> getAllNutritionists() {
        List<NutritionistResponseDTO> responseDTOs = nutritionistService.getAllNutritionists();
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/{id}/meal-plans")
    public List<MealPlanResponseDTO> getMealPlansByNutritionist(@PathVariable("id") Long id) {
        return nutritionistService.getMealPlansByNutritionist(id);
    }

    @GetMapping("/{id}/assigned-users")
    public List<UserResponseDTO> getUsersAssignedByNutritionist(@PathVariable Long id) {
        return nutritionistService.getUsersAssignedByNutritionist(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NutritionistResponseDTO> updateNutritionist(@PathVariable("id") Long nutritionistId, @RequestBody NutritionistRequestDTO updatedNutritionist) {
        NutritionistResponseDTO responseDTO = nutritionistService.updateNutritionist(nutritionistId, updatedNutritionist);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNutritionist(@PathVariable("id") Long nutritionistId) {
        nutritionistService.deleteNutritionist(nutritionistId);
        return ResponseEntity.ok("User deleted successfully");
    }
}

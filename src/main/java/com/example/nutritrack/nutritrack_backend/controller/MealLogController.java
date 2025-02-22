package com.example.nutritrack.nutritrack_backend.controller;

import com.example.nutritrack.nutritrack_backend.dto.meallog.MealLogRequestDTO;
import com.example.nutritrack.nutritrack_backend.dto.meallog.MealLogResponseDTO;
import com.example.nutritrack.nutritrack_backend.model.MealLog;
import com.example.nutritrack.nutritrack_backend.model.constant.MealTypeStatus;
import com.example.nutritrack.nutritrack_backend.service.MealLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meallogs")
@CrossOrigin("*")
public class MealLogController {

    private final MealLogService mealLogService;

    public MealLogController(MealLogService mealLogService) {
        this.mealLogService = mealLogService;
    }

    @PostMapping
    public ResponseEntity<MealLogResponseDTO> createMealLog(@RequestBody MealLogRequestDTO requestDTO) {
        return ResponseEntity.ok(mealLogService.createMealLog(requestDTO));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MealLogResponseDTO>> getMealLogsByUser(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(mealLogService.getMealLogsByUser(userId));
    }

    @GetMapping
    public ResponseEntity<List<MealLogResponseDTO>> getAllMealLogs() {
        List<MealLogResponseDTO> mealLogs = mealLogService.getAllMealLogs();
        return ResponseEntity.ok(mealLogs);
    }
    @DeleteMapping("/{mealLogId}")
    public ResponseEntity<Void> deleteMealLog(@PathVariable("mealLogId") Long mealLogId) {
        mealLogService.deleteMealLog(mealLogId);
        return ResponseEntity.noContent().build();
    }
}

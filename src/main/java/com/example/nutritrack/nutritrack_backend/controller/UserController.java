package com.example.nutritrack.nutritrack_backend.controller;

import com.example.nutritrack.nutritrack_backend.dto.meallog.MealLogResponseDTO;
import com.example.nutritrack.nutritrack_backend.dto.user.UserRequestDTO;
import com.example.nutritrack.nutritrack_backend.dto.user.UserResponseDTO;
import com.example.nutritrack.nutritrack_backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO createdUser = userService.createUser(userRequestDTO);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable("id") Long userId) {
        UserResponseDTO user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable("id") Long userId, @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO updatedUser = userService.updateUser(userId, userRequestDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/by-mealplan/{mealPlanId}")
    public UserResponseDTO getUserByMealPlan(@PathVariable("mealPlanId") Long mealPlanId) {
        return userService.getUserByMealPlan(mealPlanId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserResponseDTO>> searchUsers(@RequestParam String name) {
        List<UserResponseDTO>  searchedUsers = userService.searchUsersByName(name);
        return ResponseEntity.ok(searchedUsers);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @PutMapping("/{userId}/assign-nutritionist/{nutritionistId}")
    public ResponseEntity<UserResponseDTO> assignUserToNutritionist(@PathVariable("userId") Long userId,@PathVariable("nutritionistId") Long nutritionistId) {

        UserResponseDTO updatedUser = userService.assignUserToNutritionist(userId, nutritionistId);
        return ResponseEntity.ok(updatedUser);
    }

    // Fetch meal logs by user ID
    @GetMapping("/{userId}/meal-logs")
    public List<MealLogResponseDTO> getMealLogsByUser(@PathVariable Long userId) {
        return userService.getMealLogsByUser(userId);
    }

    // Fetch all meal logs
    @GetMapping("/meal-logs")
    public List<MealLogResponseDTO> getAllMealLogs() {
        return userService.getAllMealLogs();
    }
}

package com.example.nutritrack.nutritrack_backend.service;

import com.example.nutritrack.nutritrack_backend.dto.user.UserRequestDTO;
import com.example.nutritrack.nutritrack_backend.dto.user.UserResponseDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    UserResponseDTO getUserById(Long id);
    UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);
    List<UserResponseDTO> getUsersByNutritionist(Long nutritionistId);
    UserResponseDTO getUserByMealPlan(Long mealPlanId);
    void deleteUser(Long id);
    List<UserResponseDTO> searchUsersByName(String name);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO assignUserToNutritionist(Long userId, Long nutritionistId);
}

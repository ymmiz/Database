package com.example.nutritrack.nutritrack_backend.service.impl;

import com.example.nutritrack.nutritrack_backend.dto.meallog.MealLogResponseDTO;
import com.example.nutritrack.nutritrack_backend.dto.user.UserRequestDTO;
import com.example.nutritrack.nutritrack_backend.dto.user.UserResponseDTO;
import com.example.nutritrack.nutritrack_backend.exception.ResourceNotFoundException;
import com.example.nutritrack.nutritrack_backend.mapper.MealLogMapper;
import com.example.nutritrack.nutritrack_backend.mapper.UserMapper;
import com.example.nutritrack.nutritrack_backend.model.MealLog;
import com.example.nutritrack.nutritrack_backend.model.Nutritionist;
import com.example.nutritrack.nutritrack_backend.model.User;
import com.example.nutritrack.nutritrack_backend.repository.MealLogRepository;
import com.example.nutritrack.nutritrack_backend.repository.NutritionistRepository;
import com.example.nutritrack.nutritrack_backend.repository.UserRepository;
import com.example.nutritrack.nutritrack_backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final MealLogRepository mealLogRepository;
    private final MealLogMapper mealLogMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final NutritionistRepository nutritionistRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, NutritionistRepository nutritionistRepository, MealLogRepository mealLogRepository, MealLogMapper mealLogMapper) {
        this.mealLogRepository = mealLogRepository;
        this.mealLogMapper = mealLogMapper;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.nutritionistRepository = nutritionistRepository;
    }

    @Override
    public List<MealLogResponseDTO> getMealLogsByUser(Long userId) {
        List<MealLog> mealLogs = mealLogRepository.findMealLogsByUserId(userId);
        return mealLogMapper.toResponseDtoList(mealLogs);
    }

    @Override
    public List<MealLogResponseDTO> getAllMealLogs() {
        List<MealLog> mealLogs = mealLogRepository.getAllMealLogs();
        return mealLogMapper.toResponseDtoList(mealLogs);
    }


    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        System.out.println("Received User Request: " + userRequestDTO);
        System.out.println("Received Password: " + userRequestDTO.getPassword());

        if (userRequestDTO.getPassword() == null || userRequestDTO.getPassword().isEmpty()) {
            throw new RuntimeException("Password cannot be null or empty");
        }
        User user = userMapper.toEntity(userRequestDTO);
        user.setPassword(userRequestDTO.getPassword());
        User savedUser = userRepository.save(user);
        return userMapper.toResponseDto(savedUser);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        return userMapper.toResponseDto(user);
    }

    @Override
    public UserResponseDTO updateUser(Long userId, UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setAge(userRequestDTO.getAge());
        user.setWeight(userRequestDTO.getWeight());
        user.setGoal(userRequestDTO.getGoal());
        User updatedUser = userRepository.save(user);
        return userMapper.toResponseDto(updatedUser);
    }

    @Override
    public UserResponseDTO getUserByMealPlan(Long mealPlanId) {
        User user = userRepository.findUserByMealPlanId(mealPlanId).orElseThrow(() -> new RuntimeException("No user assigned to this meal plan"));
        return userMapper.toResponseDto(user);
    }

    @Override
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with ID: " + userId);
        }
        userRepository.deleteById(userId);
    }

    @Override
    public List<UserResponseDTO> searchUsersByName(String name) {
        List<User> users = userRepository.findByNameContainingIgnoreCase(name);
        return userMapper.toDtoList(users);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        return userMapper.toDtoList(users);
    }

    @Override
    @Transactional
    public UserResponseDTO assignUserToNutritionist(Long userId, Long nutritionistId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Nutritionist nutritionist = nutritionistRepository.findById(nutritionistId)
                .orElseThrow(() -> new ResourceNotFoundException("Nutritionist not found"));

        user.setAssignedByNutritionist(nutritionist);
        User updatedUser = userRepository.save(user);

        return userMapper.toResponseDto(updatedUser);
    }
}

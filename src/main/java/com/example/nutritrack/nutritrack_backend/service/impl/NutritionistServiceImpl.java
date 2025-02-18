package com.example.nutritrack.nutritrack_backend.service.impl;

import com.example.nutritrack.nutritrack_backend.dto.MealPlan.MealPlanResponseDTO;
import com.example.nutritrack.nutritrack_backend.dto.Nutritionist.NutritionistRequestDTO;
import com.example.nutritrack.nutritrack_backend.dto.Nutritionist.NutritionistResponseDTO;
import com.example.nutritrack.nutritrack_backend.dto.user.UserResponseDTO;
import com.example.nutritrack.nutritrack_backend.mapper.MealPlanMapper;
import com.example.nutritrack.nutritrack_backend.mapper.NutritionistMapper;
import com.example.nutritrack.nutritrack_backend.mapper.UserMapper;
import com.example.nutritrack.nutritrack_backend.model.Nutritionist;
import com.example.nutritrack.nutritrack_backend.model.User;
import com.example.nutritrack.nutritrack_backend.repository.MealPlanRepository;
import com.example.nutritrack.nutritrack_backend.repository.NutritionistRepository;
import com.example.nutritrack.nutritrack_backend.service.NutritionistService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NutritionistServiceImpl implements NutritionistService {

    private final NutritionistRepository nutritionistRepository;
    private final NutritionistMapper nutritionistMapper;
    private final MealPlanMapper mealPlanMapper;
    private final MealPlanRepository mealPlanRepository;
    private final UserMapper userMapper;

    public NutritionistServiceImpl(NutritionistRepository nutritionistRepository, NutritionistMapper nutritionistMapper, MealPlanMapper mealPlanMapper, MealPlanRepository mealPlanRepository, UserMapper userMapper) {
        this.nutritionistRepository = nutritionistRepository;
        this.nutritionistMapper = nutritionistMapper;
        this.mealPlanMapper = mealPlanMapper;
        this.mealPlanRepository = mealPlanRepository;
        this.userMapper = userMapper;
    }

    @Override
    public NutritionistResponseDTO createNutritionist(NutritionistRequestDTO nutritionistRequestDTO) {
        Nutritionist nutritionist = nutritionistMapper.toEntity(nutritionistRequestDTO);
        Nutritionist savedNutritionist = nutritionistRepository.save(nutritionist);
        return nutritionistMapper.toDto(savedNutritionist);
    }

    @Override
    public NutritionistResponseDTO getNutritionistById(Long nutritionistId) {
        Nutritionist nutritionist = nutritionistRepository.findById(nutritionistId).orElseThrow(() -> new RuntimeException("Nutritionist not found"));
        return nutritionistMapper.toDto(nutritionist);
    }

    @Override
    public List<NutritionistResponseDTO> getAllNutritionists() {
        List<Nutritionist> nutritionists = nutritionistRepository.findAll();
        return nutritionistMapper.toDtoList(nutritionists);
    }

    @Override
    public List<MealPlanResponseDTO> getMealPlansByNutritionist(Long nutritionistId) {
        return mealPlanMapper.toResponseDTOList(mealPlanRepository.findByCreatedByNutritionistId(nutritionistId));
    }

    @Override
    public List<UserResponseDTO> getUsersAssignedByNutritionist(Long nutritionistId) {
        List<User> users = nutritionistRepository.findUsersAssignedByNutritionist(nutritionistId);
        return userMapper.toResponseDtoList(users);
    }

    @Override
    public NutritionistResponseDTO updateNutritionist(Long nutritionistId, NutritionistRequestDTO nutritionistRequestDTO) {
        Nutritionist nutritionist = nutritionistRepository.findById(nutritionistId).orElseThrow(() -> new RuntimeException("Nutritionist not found"));

        nutritionist.setName(nutritionistRequestDTO.getName());
        nutritionist.setEmail(nutritionistRequestDTO.getEmail());
        nutritionist.setPassword(nutritionistRequestDTO.getPassword());
        nutritionist.setSpecialization(nutritionistRequestDTO.getSpecialization());

        Nutritionist updatedNutritionist = nutritionistRepository.save(nutritionist);
        return nutritionistMapper.toDto(updatedNutritionist);
    }

    @Override
    public void deleteNutritionist(Long nutritionistId) {
        if (!nutritionistRepository.existsById(nutritionistId)) {
            throw new RuntimeException("Nutritionist not found");
        }
        nutritionistRepository.deleteById(nutritionistId);
    }
}

package com.example.nutritrack.nutritrack_backend.service.impl;

import com.example.nutritrack.nutritrack_backend.dto.MealPlan.MealPlanRequestDTO;
import com.example.nutritrack.nutritrack_backend.dto.MealPlan.MealPlanResponseDTO;
import com.example.nutritrack.nutritrack_backend.mapper.MealPlanMapper;
import com.example.nutritrack.nutritrack_backend.model.MealPlan;
import com.example.nutritrack.nutritrack_backend.model.Nutritionist;
import com.example.nutritrack.nutritrack_backend.model.User;
import com.example.nutritrack.nutritrack_backend.repository.MealPlanRepository;
import com.example.nutritrack.nutritrack_backend.repository.NutritionistRepository;
import com.example.nutritrack.nutritrack_backend.repository.UserRepository;
import com.example.nutritrack.nutritrack_backend.service.MealPlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealPlanserviceImpl implements MealPlanService {

    private final MealPlanRepository mealPlanRepository;
    private final MealPlanMapper mealPlanMapper;
    private final UserRepository userRepository;
    private final NutritionistRepository nutritionistRepository;

    public MealPlanserviceImpl(MealPlanRepository mealPlanRepository, MealPlanMapper mealPlanMapper, UserRepository userRepository, com.example.nutritrack.nutritrack_backend.repository.NutritionistRepository nutritionistRepository) {
        this.mealPlanRepository = mealPlanRepository;
        this.mealPlanMapper = mealPlanMapper;
        this.userRepository = userRepository;
        this.nutritionistRepository = nutritionistRepository;
    }
    @Override
    public MealPlanResponseDTO createMealPlan(Long nutritionistId, MealPlanRequestDTO requestDTO) {
        Nutritionist nutritionist = nutritionistRepository.findById(nutritionistId)
                .orElseThrow(() -> new RuntimeException("Nutritionist not found"));

        User user = userRepository.findById(requestDTO.getAssignedUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        MealPlan mealPlan = mealPlanMapper.toEntity(requestDTO);
        mealPlan.setCreatedBy(nutritionist);
        mealPlan.setAssignedUser(user);

        mealPlan = mealPlanRepository.save(mealPlan);
        return mealPlanMapper.toResponseDTO(mealPlan);
    }

    @Override
    public List<MealPlanResponseDTO> getMealPlansByUser(Long userId) {
        List<MealPlan> mealPlans = mealPlanRepository.findByAssignedUserId(userId);
        return mealPlanMapper.toResponseDTOList(mealPlans);
    }

    @Override
    public List<MealPlanResponseDTO> getMealPlansByNutritionist(Long nutritionistId) {
        List<MealPlan> mealPlans = mealPlanRepository.findByCreatedByNutritionistId(nutritionistId);
        return mealPlanMapper.toResponseDTOList(mealPlans);
    }

    @Override
    public MealPlanResponseDTO getMealPlanById(Long planId) {
        MealPlan mealPlan = mealPlanRepository.findByPlanId(planId);
        if (mealPlan == null) {
            throw new RuntimeException("Meal Plan not found");
        }
        return mealPlanMapper.toResponseDTO(mealPlan);
    }

    @Override
    public MealPlanResponseDTO updateMealPlan(Long planId, MealPlanRequestDTO requestDTO) {
        MealPlan mealPlan = mealPlanRepository.findByPlanId(planId);
        if (mealPlan == null) {
            throw new RuntimeException("Meal Plan not found");
        }

        mealPlanMapper.updateMealPlanFromDto(requestDTO, mealPlan);
        mealPlan = mealPlanRepository.save(mealPlan);

        return mealPlanMapper.toResponseDTO(mealPlan);
    }

    @Override
    public MealPlanResponseDTO assignMealPlanToUser(Long mealPlanId, Long userId) {
        MealPlan mealPlan = mealPlanRepository.findByPlanId(mealPlanId);
        if (mealPlan == null) {
            throw new RuntimeException("Meal Plan not found");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        mealPlan.setAssignedUser(user);
        mealPlanRepository.save(mealPlan);

        return mealPlanMapper.toResponseDTO(mealPlan);
    }

    @Override
    public void deleteMealPlan(Long planId) {
        if (!mealPlanRepository.existsById(planId)) {
            throw new RuntimeException("Meal Plan not found");
        }
        mealPlanRepository.deleteById(planId);
    }

}

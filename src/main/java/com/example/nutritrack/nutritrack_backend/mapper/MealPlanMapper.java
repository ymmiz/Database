package com.example.nutritrack.nutritrack_backend.mapper;

import com.example.nutritrack.nutritrack_backend.dto.MealPlan.MasterMealPlanDTO;
import com.example.nutritrack.nutritrack_backend.dto.MealPlan.MealPlanRequestDTO;
import com.example.nutritrack.nutritrack_backend.dto.MealPlan.MealPlanResponseDTO;
import com.example.nutritrack.nutritrack_backend.dto.Nutritionist.MasterNutritionistDTO;
import com.example.nutritrack.nutritrack_backend.model.MealPlan;
import com.example.nutritrack.nutritrack_backend.model.Nutritionist;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MealPlanMapper {

    MealPlanMapper INSTANCE = Mappers.getMapper(MealPlanMapper.class);

    @Mapping(target = "planId", ignore = true) // planId is auto-generated
    @Mapping(target = "createdDate", ignore = true) // Set automatically
    @Mapping(target = "createdBy", ignore = true) // Set in Service Layer
    @Mapping(target = "assignedUser", ignore = true) // Set in Service Layer
    MealPlan toEntity(MealPlanRequestDTO mealPlanRequestDTO);

    @Mapping(source = "createdBy", target = "createdBy")
    @Mapping(source = "assignedUser", target = "assignedUser")
    MasterMealPlanDTO toMasterDto(MealPlan mealPlan);

    @Mapping(source = "createdBy", target = "createdBy",qualifiedByName = "mapNutritionistToDTO")
    @Mapping(source = "assignedUser", target = "assignedUser")
    MealPlanResponseDTO toResponseDTO(MealPlan mealPlan);

    @Named("mapNutritionistToDTO")
    default MasterNutritionistDTO mapNutritionistToDTO(Nutritionist nutritionist) {
        if (nutritionist == null) {
            return null;
        }
        return new MasterNutritionistDTO(nutritionist.getNutritionistId(),nutritionist.getName(), nutritionist.getEmail(), nutritionist.getSpecialization());
    }

    void updateMealPlanFromDto(MealPlanRequestDTO requestDTO, @MappingTarget MealPlan mealPlan);

    List<MealPlanResponseDTO> toResponseDTOList(List<MealPlan> mealPlans);
}

package com.example.nutritrack.nutritrack_backend.mapper;

import com.example.nutritrack.nutritrack_backend.dto.Nutritionist.NutritionistRequestDTO;
import com.example.nutritrack.nutritrack_backend.dto.Nutritionist.NutritionistResponseDTO;
import com.example.nutritrack.nutritrack_backend.model.Nutritionist;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NutritionistMapper {

    NutritionistMapper INSTANCE = Mappers.getMapper(NutritionistMapper.class);

    Nutritionist toEntity(NutritionistRequestDTO dto);

    NutritionistResponseDTO toDto(Nutritionist nutritionist);

    List<NutritionistResponseDTO> toDtoList(List<Nutritionist> nutritionists);
}

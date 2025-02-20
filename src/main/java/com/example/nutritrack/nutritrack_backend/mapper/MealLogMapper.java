package com.example.nutritrack.nutritrack_backend.mapper;

import com.example.nutritrack.nutritrack_backend.dto.meallog.MealLogRequestDTO;
import com.example.nutritrack.nutritrack_backend.dto.meallog.MealLogResponseDTO;
import com.example.nutritrack.nutritrack_backend.model.MealLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MealLogMapper {

    MealLogMapper INSTANCE = Mappers.getMapper(MealLogMapper.class);

    @Mapping(target = "user.userId", source = "userId")
    MealLog toEntity(MealLogRequestDTO dto);

    @Mapping(target = "userName", source = "user.name")
    MealLogResponseDTO toResponseDTO(MealLog mealLog);

    List<MealLogResponseDTO> toResponseDtoList(List<MealLog> mealLogs);

}

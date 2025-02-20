package com.example.nutritrack.nutritrack_backend.mapper;

import com.example.nutritrack.nutritrack_backend.dto.Feedback.FeedbackRequestDTO;
import com.example.nutritrack.nutritrack_backend.dto.Feedback.FeedbackResponseDTO;
import com.example.nutritrack.nutritrack_backend.model.Feedback;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {

    FeedbackMapper INSTANCE = Mappers.getMapper(FeedbackMapper.class);

    // Convert Request DTO to Entity (Used for creating feedback)
    @Mapping(target = "feedbackId", ignore = true) // ID is auto-generated
    @Mapping(target = "date", ignore = true) // Date is handled by database default value
    Feedback toEntity(FeedbackRequestDTO requestDTO);

    // Convert Entity to Response DTO (Used for returning feedback)
    @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "nutritionist.nutritionistId", target = "nutritionistId")
    FeedbackResponseDTO toResponseDTO(Feedback feedback);

    // Convert List of Entities to List of Response DTOs
    List<FeedbackResponseDTO> toResponseDTOList(List<Feedback> feedbackList);
}

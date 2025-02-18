package com.example.nutritrack.nutritrack_backend.mapper;

import com.example.nutritrack.nutritrack_backend.dto.user.MasterUserDTO;
import com.example.nutritrack.nutritrack_backend.dto.user.UserRequestDTO;
import com.example.nutritrack.nutritrack_backend.dto.user.UserResponseDTO;
import com.example.nutritrack.nutritrack_backend.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {


    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // Mapping Entity to Response DTO (Hides Password)
    UserResponseDTO toResponseDto(User user);

    // Mapping Request DTO to Entity
    User toEntity(UserRequestDTO userRequestDTO);

    @Mapping(target = "totalMealLogs", source = "totalMealLogs")
    // Mapping Entity to Master DTO (For Internal Processing)
    MasterUserDTO toMasterDto(User user);

    // Mapping Master DTO to Entity
    User toEntity(MasterUserDTO masterUserDTO);

    List<UserResponseDTO> toDtoList(List<User> users);

    List<UserResponseDTO> toResponseDtoList(List<User> users);

}

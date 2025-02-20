package com.example.nutritrack.nutritrack_backend.service.impl;

import com.example.nutritrack.nutritrack_backend.dto.meallog.MealLogRequestDTO;
import com.example.nutritrack.nutritrack_backend.dto.meallog.MealLogResponseDTO;
import com.example.nutritrack.nutritrack_backend.exception.ResourceNotFoundException;
import com.example.nutritrack.nutritrack_backend.mapper.MealLogMapper;
import com.example.nutritrack.nutritrack_backend.model.MealLog;
import com.example.nutritrack.nutritrack_backend.model.MealPlan;
import com.example.nutritrack.nutritrack_backend.model.User;
import com.example.nutritrack.nutritrack_backend.model.constant.MealTypeStatus;
import com.example.nutritrack.nutritrack_backend.repository.MealLogRepository;
import com.example.nutritrack.nutritrack_backend.repository.UserRepository;
import com.example.nutritrack.nutritrack_backend.service.MealLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MealLogServiceImpl implements MealLogService {

    private final MealLogRepository mealLogRepository;
    private final MealLogMapper mealLogMapper;
    private final UserRepository userRepository;

    @Autowired
    public MealLogServiceImpl(MealLogRepository mealLogRepository, MealLogMapper mealLogMapper, UserRepository userRepository) {
        this.mealLogRepository = mealLogRepository;
        this.mealLogMapper = mealLogMapper;
        this.userRepository = userRepository;
    }

    @Override
    public MealLogResponseDTO createMealLog(MealLogRequestDTO requestDTO) {
        User user = userRepository.findById(requestDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        MealLog mealLog = mealLogMapper.toEntity(requestDTO);
        mealLog.setUser(user);
        MealLog savedMealLog = mealLogRepository.save(mealLog);

        return mealLogMapper.toResponseDTO(savedMealLog);
    }

    @Override
    public List<MealLogResponseDTO> getMealLogsByUser(Long userId) {
        List<MealLog> mealLogs = mealLogRepository.findByUserId(userId);
        return mealLogMapper.toResponseDtoList(mealLogs);
    }

    @Override
    public List<MealLogResponseDTO> getAllMealLogs() {
        List<MealLog> mealLogs = mealLogRepository.findAll();
        return mealLogMapper.toResponseDtoList(mealLogs);
    }

    @Override
    public void deleteMealLog(Long mealLogId) {
        if (!mealLogRepository.existsById(mealLogId)) {
            throw new ResourceNotFoundException("MealLog not found");
        }
        mealLogRepository.deleteById(mealLogId);
    }
}

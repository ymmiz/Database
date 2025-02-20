package com.example.nutritrack.nutritrack_backend.service.impl;

import com.example.nutritrack.nutritrack_backend.dto.Feedback.FeedbackRequestDTO;
import com.example.nutritrack.nutritrack_backend.dto.Feedback.FeedbackResponseDTO;
import com.example.nutritrack.nutritrack_backend.mapper.FeedbackMapper;
import com.example.nutritrack.nutritrack_backend.model.Feedback;
import com.example.nutritrack.nutritrack_backend.model.Nutritionist;
import com.example.nutritrack.nutritrack_backend.model.User;
import com.example.nutritrack.nutritrack_backend.repository.FeedbackRepository;
import com.example.nutritrack.nutritrack_backend.repository.NutritionistRepository;
import com.example.nutritrack.nutritrack_backend.repository.UserRepository;
import com.example.nutritrack.nutritrack_backend.service.FeedbackService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;
    private final NutritionistRepository nutritionistRepository;
    private final FeedbackMapper feedbackMapper;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, UserRepository userRepository, NutritionistRepository nutritionistRepository, FeedbackMapper feedbackMapper) {
        this.feedbackRepository = feedbackRepository;
        this.userRepository = userRepository;
        this.nutritionistRepository = nutritionistRepository;
        this.feedbackMapper = feedbackMapper;
    }


    @Override
    public FeedbackResponseDTO createFeedback(FeedbackRequestDTO feedbackRequestDTO) {
        User user = userRepository.findById(feedbackRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Nutritionist nutritionist = nutritionistRepository.findById(feedbackRequestDTO.getNutritionistId())
                .orElseThrow(() -> new RuntimeException("Nutritionist not found"));

        Feedback feedback = feedbackMapper.toEntity(feedbackRequestDTO);
        feedback.setUser(user);
        feedback.setNutritionist(nutritionist);

        // Save feedback
        feedback = feedbackRepository.save(feedback);
        return feedbackMapper.toResponseDTO(feedback);
    }

    @Override
    public List<FeedbackResponseDTO> getFeedbackByUser(Long userId) {
        List<Feedback> feedbackList = feedbackRepository.findByUserId(userId);
        return feedbackMapper.toResponseDTOList(feedbackList);
    }

    @Override
    public List<FeedbackResponseDTO> getFeedbackByNutritionist(Long nutritionistId) {
        List<Feedback> feedbackList = feedbackRepository.findByNutritionistId(nutritionistId);
        return feedbackMapper.toResponseDTOList(feedbackList);
    }

    @Override
    public List<FeedbackResponseDTO> getAllFeedbacks() {
        List<Feedback> feedbackList = feedbackRepository.getAllFeedbacks();
        return feedbackMapper.toResponseDTOList(feedbackList);
    }

    @Override
    public void deleteFeedback(Long feedbackId) {
        Optional<Feedback> feedback = feedbackRepository.findById(feedbackId);
        if (feedback.isEmpty()) {
            throw new EntityNotFoundException("Feedback not found");
        }
        feedbackRepository.deleteFeedbackById(feedbackId);
    }
}

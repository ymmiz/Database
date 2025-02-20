package com.example.nutritrack.nutritrack_backend.service;

import com.example.nutritrack.nutritrack_backend.dto.Feedback.FeedbackRequestDTO;
import com.example.nutritrack.nutritrack_backend.dto.Feedback.FeedbackResponseDTO;

import java.util.List;

public interface FeedbackService {
    FeedbackResponseDTO createFeedback(FeedbackRequestDTO feedbackRequestDTO);
    List<FeedbackResponseDTO> getFeedbackByUser(Long userId);
    List<FeedbackResponseDTO> getFeedbackByNutritionist(Long nutritionistId);
    List<FeedbackResponseDTO> getAllFeedbacks();
    void deleteFeedback(Long feedbackId);
}

package com.example.nutritrack.nutritrack_backend.controller;

import com.example.nutritrack.nutritrack_backend.dto.Feedback.FeedbackRequestDTO;
import com.example.nutritrack.nutritrack_backend.dto.Feedback.FeedbackResponseDTO;
import com.example.nutritrack.nutritrack_backend.service.FeedbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public ResponseEntity<FeedbackResponseDTO> createFeedback(@RequestBody FeedbackRequestDTO feedbackRequestDTO) {
        return ResponseEntity.ok(feedbackService.createFeedback(feedbackRequestDTO));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FeedbackResponseDTO>> getFeedbackByUser(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(feedbackService.getFeedbackByUser(userId));
    }

    @GetMapping("/nutritionist/{nutritionistId}")
    public ResponseEntity<List<FeedbackResponseDTO>> getFeedbackByNutritionist(@PathVariable("nutritionistId") Long nutritionistId) {
        return ResponseEntity.ok(feedbackService.getFeedbackByNutritionist(nutritionistId));
    }

    @GetMapping
    public ResponseEntity<List<FeedbackResponseDTO>> getAllFeedbacks() {
        return ResponseEntity.ok(feedbackService.getAllFeedbacks());
    }

    @DeleteMapping("/{feedbackId}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable("feedbackId") Long feedbackId) {
        feedbackService.deleteFeedback(feedbackId);
        return ResponseEntity.noContent().build();
    }
}

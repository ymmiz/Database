package com.example.nutritrack.nutritrack_backend.dto.Feedback;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackRequestDTO {
    private Long userId;
    private Long nutritionistId;
    private String feedbackText;
}

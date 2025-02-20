package com.example.nutritrack.nutritrack_backend.dto.Feedback;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackResponseDTO {

    private Long userId;
    private Long nutritionistId;
    private LocalDate date;
    private String feedbackText;
}

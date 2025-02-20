package com.example.nutritrack.nutritrack_backend.repository;

import com.example.nutritrack.nutritrack_backend.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    @Transactional
    @Query(value = "INSERT INTO feedback (user_id, nutritionist_id, date, feedback_text) " +
            "VALUES (:userId, :nutritionistId, CURRENT_DATE, :feedbackText) RETURNING feedback_id", nativeQuery = true)
    Long createFeedback(
            @Param("userId") Long userId,
            @Param("nutritionistId") Long nutritionistId,
            @Param("feedbackText") String feedbackText
    );

    // Retrieve feedback by ID
    @Query(value = "SELECT * FROM feedback WHERE feedback_id = :feedbackId", nativeQuery = true)
    Optional<Feedback> findFeedbackById(@Param("feedbackId") Long feedbackId);

    // Retrieve feedback by User ID
    @Query(value = "SELECT * FROM feedback WHERE user_id = :userId", nativeQuery = true)
    List<Feedback> findByUserId(@Param("userId") Long userId);

    // Retrieve feedback by Nutritionist ID
    @Query(value = "SELECT * FROM feedback WHERE nutritionist_id = :nutritionistId", nativeQuery = true)
    List<Feedback> findByNutritionistId(@Param("nutritionistId") Long nutritionistId);

    // Retrieve all feedbacks
    @Query(value = "SELECT * FROM feedback", nativeQuery = true)
    List<Feedback> getAllFeedbacks();

    // Update feedback text
    @Transactional
    @Modifying
    @Query(value = "UPDATE feedback SET feedback_text = :feedbackText WHERE feedback_id = :feedbackId", nativeQuery = true)
    int updateFeedbackText(@Param("feedbackId") Long feedbackId, @Param("feedbackText") String feedbackText);

    // Delete feedback by ID
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM feedback WHERE feedback_id = :feedbackId", nativeQuery = true)
    void deleteFeedbackById(@Param("feedbackId") Long feedbackId);
}

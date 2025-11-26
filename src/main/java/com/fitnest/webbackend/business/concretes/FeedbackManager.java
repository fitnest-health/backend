package com.fitnest.webbackend.business.concretes;

import com.fitnest.webbackend.business.abstracts.FeedbackService;
import com.fitnest.webbackend.exceptions.ConflictException;
import com.fitnest.webbackend.exceptions.ResourceNotFoundException;
import com.fitnest.webbackend.model.entities.Feedback;
import com.fitnest.webbackend.payload.dtos.FeedbackRequestDTO;
import com.fitnest.webbackend.payload.responses.APIResponse;
import com.fitnest.webbackend.payload.responses.FeedbackResponse;
import com.fitnest.webbackend.repositories.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackManager implements FeedbackService {
    private final FeedbackRepository feedbackRepository;

    @Override
    public APIResponse createFeedback(FeedbackRequestDTO requestDTO) {
        Feedback feedback = Feedback.builder()
                .fullName(requestDTO.getFullName())
                .email(requestDTO.getEmail())
                .type(requestDTO.getType())
                .message(requestDTO.getMessage())
                .read(false)
                .build();
        feedbackRepository.save(feedback);
        return new APIResponse("Feedback created successfully", true, "Feedback");
    }

    @Override
    public List<FeedbackResponse> getAllFeedbacks() {
        return feedbackRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::mapToResponse).toList();
    }

    @Override
    public FeedbackResponse getFeedbackById(Long id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback", "id", id));
        return mapToResponse(feedback);
    }

    private FeedbackResponse mapToResponse(Feedback feedback) {
        return FeedbackResponse.builder()
                .id(feedback.getId())
                .fullName(feedback.getFullName())
                .email(feedback.getEmail())
                .type(feedback.getType())
                .message(feedback.getMessage())
                .read(feedback.isRead())
                .createdAt(feedback.getCreatedAt())
                .build();
    }

    @Override
    @Transactional
    public APIResponse markAsRead(Long id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback", "id", id));

        if (feedback.isRead()) {
            throw new ConflictException("Feedback is already marked as read");
        }
        feedback.setRead(true);
        feedbackRepository.save(feedback);
        return new APIResponse("Feedback marked as read successfully", true, "Feedback");
    }

    @Override
    public APIResponse deleteFeedback(Long id) {
        Feedback feedback = feedbackRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Feedback", "id", id));
        feedbackRepository.delete(feedback);
        return new APIResponse("Feedback deleted successfully", true, "Feedback");
    }
}
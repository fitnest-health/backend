package com.fitnest.webbackend.business.abstracts;

import com.fitnest.webbackend.payload.dtos.FeedbackRequestDTO;
import com.fitnest.webbackend.payload.responses.APIResponse;
import com.fitnest.webbackend.payload.responses.FeedbackResponse;

import java.util.List;

public interface FeedbackService {
    APIResponse createFeedback(FeedbackRequestDTO requestDTO);

    List<FeedbackResponse> getAllFeedbacks();

    FeedbackResponse getFeedbackById(Long id);

    APIResponse markAsRead(Long id);

    APIResponse deleteFeedback(Long id);
}

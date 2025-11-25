package com.fitnest.webbackend.controller;

import com.fitnest.webbackend.business.abstracts.FeedbackService;
import com.fitnest.webbackend.payload.dtos.FeedbackRequestDTO;
import com.fitnest.webbackend.payload.responses.APIResponse;
import com.fitnest.webbackend.payload.responses.FeedbackResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<APIResponse> createFeedback(@Valid @RequestBody FeedbackRequestDTO requestDTO) {
        APIResponse response = feedbackService.createFeedback(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<FeedbackResponse>> getAllFeedbacks() {
        List<FeedbackResponse> allFeedbacks = feedbackService.getAllFeedbacks();
        return ResponseEntity.ok(allFeedbacks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackResponse> getFeedbackById(@PathVariable Long id) {
        FeedbackResponse feedback = feedbackService.getFeedbackById(id);
        return ResponseEntity.ok(feedback);
    }

    @PatchMapping("/{id}/read")
    public ResponseEntity<APIResponse> markAsRead(@PathVariable Long id) {
        APIResponse readResponse = feedbackService.markAsRead(id);
        return ResponseEntity.ok(readResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteFeedback(@PathVariable Long id) {
        APIResponse response = feedbackService.deleteFeedback(id);
        return ResponseEntity.ok(response);
    }
}

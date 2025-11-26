package com.fitnest.webbackend.payload.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fitnest.webbackend.model.enums.FeedbackType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FeedbackResponse {
    private Long id;
    private String fullName;
    private String email;
    private FeedbackType type;
    private String message;
    private boolean read;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
}

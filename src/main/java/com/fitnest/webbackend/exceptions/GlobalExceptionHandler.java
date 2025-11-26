//package com.fitnest.webbackend.exceptions;
//
//
//import com.fitnest.webbackend.payload.responses.ErrorResponse;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;
//
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    private ErrorResponse buildErrorResponse(String message, String errorCode,
//                                             HttpStatus status, HttpServletRequest request,
//                                             Map<String, String> validationErrors) {
//        return ErrorResponse.builder()
//                .message(message)
//                .errorCode(errorCode)
//                .path(request.getRequestURI())
//                .method(request.getMethod())
//                .timestamp(LocalDateTime.now())
//                .validationErrors(validationErrors)
//                .build();
//    }
//
//    @ExceptionHandler(AppException.class)
//    public ResponseEntity<ErrorResponse> handleAppException(AppException ex, HttpServletRequest request) {
//        HttpStatus status = switch (ex.getErrorCode()) {
//            case "ERR_RESOURCE_NOT_FOUND" -> HttpStatus.NOT_FOUND;
//            case "ERR_BAD_REQUEST" -> HttpStatus.BAD_REQUEST;
//            case "ERR_UNAUTHORIZED" -> HttpStatus.UNAUTHORIZED;
//            case "ERR_FORBIDDEN" -> HttpStatus.FORBIDDEN;
//            case "ERR_CONFLICT" -> HttpStatus.CONFLICT;
//            case "ERR_EXTERNAL_SERVICE" -> HttpStatus.SERVICE_UNAVAILABLE;
//            default -> HttpStatus.INTERNAL_SERVER_ERROR;
//        };
//        return new ResponseEntity<>(buildErrorResponse(ex.getMessage(), ex.getErrorCode(),
//                status, request, null), status);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex,
//                                                          HttpServletRequest request) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach(error -> {
//            String field = ((FieldError) error).getField();
//            errors.put(field, error.getDefaultMessage());
//        });
//
//        return new ResponseEntity<>(
//                buildErrorResponse("Validation failed", "ERR_VALIDATION",
//                        HttpStatus.BAD_REQUEST, request, errors),
//                HttpStatus.BAD_REQUEST
//        );
//    }
//
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity<ErrorResponse> handleConflict(DataIntegrityViolationException ex,
//                                                        HttpServletRequest request) {
//        return new ResponseEntity<>(
//                buildErrorResponse("Database constraint violated: " + ex.getMostSpecificCause().getMessage(),
//                        "ERR_DATABASE_CONFLICT",
//                        HttpStatus.CONFLICT, request, null),
//                HttpStatus.CONFLICT
//        );
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleOtherExceptions(Exception ex, HttpServletRequest request) {
//        return new ResponseEntity<>(
//                buildErrorResponse("Unexpected error: " + ex.getMessage(), "ERR_INTERNAL",
//                        HttpStatus.INTERNAL_SERVER_ERROR, request, null),
//                HttpStatus.INTERNAL_SERVER_ERROR
//        );
//    }
//}

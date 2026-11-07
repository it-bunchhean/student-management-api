package com.it_bunchhean.studentmanagement.exception;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.it_bunchhean.studentmanagement.payload.ErrorResponse;
import com.it_bunchhean.studentmanagement.payload.FieldErrorResponse;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException e,
            HttpServletRequest request
    ) {

        List<FieldErrorResponse> detail = e.getFieldErrors().stream()
            .map(fieldError -> new FieldErrorResponse(
                    fieldError.getField(),
                    fieldError.getDefaultMessage()
            ))
            .toList();

        log.warn("Validation failed: {}", detail.size());

        return ResponseEntity.badRequest()
                .body(ErrorResponse.of(
                            HttpStatus.BAD_REQUEST,
                            e.getMessage(),
                            request.getRequestURI()
                ));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(
            ResourceNotFoundException e,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = ErrorResponse.of(
                HttpStatus.NOT_FOUND,
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handelGeneric(
            Exception e,
            HttpServletRequest request
    ) {
        log.error("unexpected error", e);
        return ResponseEntity.internalServerError()
                .body(ErrorResponse.of(
                            HttpStatus.INTERNAL_SERVER_ERROR, 
                            e.getMessage(),
                            request.getRequestURI()
                ));
    }

    @ExceptionHandler(DucplicateResourceException.class)
    public ResponseEntity<Map<String, String>> handleDuplicate(DucplicateResourceException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", e.getMessage()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, String>> handleAuth(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Invalid email or password"));
    }

}

package com.it_bunchhean.studentmanagement.payload;


import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse (
        LocalDateTime timestamp,
        int status,
        String error, 
        String message, 
    String path
) {
    public static ErrorResponse of(
            HttpStatus status,
            String message,
            String path
    ) {
        return new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.name(),
                message,
                path
        );
    }
}


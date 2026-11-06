package com.it_bunchhean.studentmanagement.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class AppException extends RuntimeException {

    private final HttpStatus status;

    protected AppException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

}

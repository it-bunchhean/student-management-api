package com.taskflow.studentmanagement.exception;

import lombok.Getter;

@Getter
public class InvalidPasswordException extends RuntimeException {

    private final String reason;

    public InvalidPasswordException(String reason) {
        super(String.format("Invalid password: %s", reason));
        this.reason = reason;
    }

    public InvalidPasswordException(String message, String reason) {
        super(message);
        this.reason = reason;
    }

}

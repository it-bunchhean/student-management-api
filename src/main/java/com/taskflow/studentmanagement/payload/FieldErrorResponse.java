package com.taskflow.studentmanagement.payload;

public record FieldErrorResponse(
        String field,
        String reason
) {}

package com.it_bunchhean.studentmanagement.payload;

public record FieldErrorResponse(
        String field,
        String reason
) {}

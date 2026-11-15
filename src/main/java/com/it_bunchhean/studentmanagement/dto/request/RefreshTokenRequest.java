package com.it_bunchhean.studentmanagement.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequest(
        @NotBlank(message = "Token is required") String refreshToken
) { }

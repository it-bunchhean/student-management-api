package com.it_bunchhean.studentmanagement.dto.response;

import lombok.Builder;

@Builder
public record AuthResponse(
        String accessToken,
        String tokenType,
        long expiresIn
) {
    public AuthResponse(String accessToken, long expiresIn) {
        this(
                accessToken,
                "Bearer",
                expiresIn
        );
    }
}

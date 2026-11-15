package com.it_bunchhean.studentmanagement.dto.response;

import lombok.Builder;

@Builder
public record AuthResponse(
        String username,
        String accessToken,
        String refreshToken,
        String tokenType,
        long expiresIn
) {
    public AuthResponse(String username, String accessToken, String refreshToken, long expiresIn) {
        this(
                username,
                accessToken,
                refreshToken,
                "Bearer",
                expiresIn
        );
    }
}

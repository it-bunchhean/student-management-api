package com.it_bunchhean.studentmanagement.dto.response;

import lombok.Builder;

@Builder
public record LoginResponse(
        String token,
        long expiresIn
) {
    public String getToken() {
        return token;
    }

}

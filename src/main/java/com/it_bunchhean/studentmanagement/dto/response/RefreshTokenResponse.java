package com.it_bunchhean.studentmanagement.dto.response;

import lombok.Builder;

@Builder
public record RefreshTokenResponse(
        String token
) {}

package com.it_bunchhean.studentmanagement.dto.response;

import com.it_bunchhean.studentmanagement.entity.User;
import lombok.Builder;

import java.time.Instant;

@Builder
public record RefreshTokenResponse(
        User username,
        String token,
        Instant consumeAt,
        Instant revokedAt,
        Instant expiredAt
) {}

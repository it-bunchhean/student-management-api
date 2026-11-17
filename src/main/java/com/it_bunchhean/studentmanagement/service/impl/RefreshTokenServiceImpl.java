package com.it_bunchhean.studentmanagement.service.impl;

import com.it_bunchhean.studentmanagement.dto.response.RefreshTokenResponse;
import com.it_bunchhean.studentmanagement.entity.RefreshToken;
import com.it_bunchhean.studentmanagement.entity.User;
import com.it_bunchhean.studentmanagement.repository.RefreshTokenRepository;
import com.it_bunchhean.studentmanagement.repository.UserRepository;
import com.it_bunchhean.studentmanagement.security.jwt.JwtService;
import com.it_bunchhean.studentmanagement.security.user.CustomUserDetails;
import com.it_bunchhean.studentmanagement.service.RefreshTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.Optional;

@Service
@Slf4j
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Value(value = "${jwt.refresh-token.expiration}")
    private long expirationTime;

    public RefreshTokenServiceImpl(
            RefreshTokenRepository refreshTokenRepository,
            JwtService jwtService,
            UserRepository userRepository
    ) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    public RefreshToken create(User user) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setToken(generateSecureToken());
        refreshToken.setUser(user);
        refreshToken.setExpiryAt(
                Instant.now().plusMillis(expirationTime)
        );

        log.info("Refresh token created successfully");

        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> findByToken(String refresh) {
        return refreshTokenRepository.findByToken(refresh);
    }

    @Override
    public RefreshTokenResponse verify(String token) {

        RefreshToken refreshToken = this.findByToken(token)
                .orElseThrow(() -> new ResourceAccessException("Refresh token not found"));

        if (refreshToken.getExpiryAt().isBefore(Instant.now())) {
            refreshTokenRepository.delete(refreshToken);
            throw new BadCredentialsException("Refresh tokne expired");
        }

        User user = refreshToken.getUser();

        UserDetails userDetails = new CustomUserDetails(user);

        String accessToken = jwtService.generateToken(userDetails);

        return RefreshTokenResponse.builder()
                .token(accessToken)
                .build();
    }

    private String generateSecureToken() {
            byte[] randomBytes = new byte[64];

            new SecureRandom().nextBytes(randomBytes);

            return Base64.getUrlEncoder()
                    .withoutPadding()
                    .encodeToString(randomBytes);
    }
}
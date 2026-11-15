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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

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
    public RefreshToken create() {
        RefreshToken refresh= new RefreshToken();
        String randomRefreshToken = UUID.randomUUID().toString();
        refresh.setToken(randomRefreshToken);
        refresh.setExpiryAt(Instant.now().plusMillis(expirationTime));
        log.info("Created refresh token [{}]", refresh);
        return refresh;
    }

    @Override
    public Optional<RefreshToken> findByToken(String refresh) {
        return refreshTokenRepository.findByToken(refresh);
    }

    @Override
    public RefreshTokenResponse verify(String token) {

        if (!jwtService.isRefreshTokenValid(token)) {
            throw new BadCredentialsException("Invalid refresh token");
        }

        RefreshToken refreshToken = this.findByToken(token)
                .orElseThrow(() -> new ResourceAccessException("Refresh token not found"));


        if (refreshToken.getExpiryAt().isBefore(Instant.now())) {


            String username = jwtService.extractUsername(token);

            User user = userRepository.findByUsername(username)
                    .orElseThrow(() ->
                            new UsernameNotFoundException("User not found")
                    );

            UserDetails userDetails = new CustomUserDetails(user);

            String accessToken = jwtService.generateToken(userDetails);
            return RefreshTokenResponse
                    .builder()
                    .token(accessToken)
                    .build();
        }

        throw new IllegalArgumentException("Refresh token expired");
    }

}

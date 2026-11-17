package com.it_bunchhean.studentmanagement.service;

import com.it_bunchhean.studentmanagement.dto.response.RefreshTokenResponse;
import com.it_bunchhean.studentmanagement.entity.RefreshToken;
import com.it_bunchhean.studentmanagement.entity.User;

import java.util.Optional;

public interface RefreshTokenService {

    RefreshToken create(User user);

    Optional<RefreshToken> findByToken(String refresh);

    RefreshTokenResponse verify(String token);

}
package com.it_bunchhean.studentmanagement.security.auth;

import com.it_bunchhean.studentmanagement.constant.Role;
import com.it_bunchhean.studentmanagement.dto.request.LoginRequest;
import com.it_bunchhean.studentmanagement.dto.request.RegisterRequest;
import com.it_bunchhean.studentmanagement.dto.response.AuthResponse;
import com.it_bunchhean.studentmanagement.dto.response.UserResponse;
import com.it_bunchhean.studentmanagement.entity.User;
import com.it_bunchhean.studentmanagement.mapper.UserMapper;
import com.it_bunchhean.studentmanagement.repository.UserRepository;
import com.it_bunchhean.studentmanagement.security.jwt.JwtService;
import com.sun.jdi.request.DuplicateRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationService {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public AuthenticationService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            UserMapper userMapper,
            JwtService jwtService,
            UserDetailsService userDetailsService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Transactional
    public User signUp(RegisterRequest request) {

        String email = request.email();

        if (userRepository.existsByEmail(email)) {
            throw new DuplicateRequestException(("Email already registered"));
        }

        if (userRepository.existsByUsername(request.username())) {
            throw new DuplicateRequestException(("Username already registered"));
        }

        User user = userMapper.toEntity(request);

        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.USER);

        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateRequestException("Email or username already in use");
        }
    }

    public AuthResponse authenticate(LoginRequest request) {
        log.info("Login attempt for email: {}", request.email());

        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtService.generateToken(userDetails);

            log.info("User authenticated successfully: {}", request.email());

            return AuthResponse.builder()
                    .accessToken(token)
                    .tokenType("Bearer")
                    .expiresIn(jwtService.getExpirationTime())
                    .build();

        } catch (BadCredentialsException e) {
            log.warn("Invalid credentials for email: {}", request.email());
            throw e;
        } catch (AuthenticationException e) {
            log.error("Authentication failed for email: {}", request.email(), e);
            throw e;
        }
    }

}

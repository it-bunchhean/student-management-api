package com.it_bunchhean.studentmanagement.security.auth;

import com.it_bunchhean.studentmanagement.constant.Role;
import com.it_bunchhean.studentmanagement.dto.request.LoginRequest;
import com.it_bunchhean.studentmanagement.dto.request.RegisterRequest;
import com.it_bunchhean.studentmanagement.entity.User;
import com.it_bunchhean.studentmanagement.mapper.UserMapper;
import com.it_bunchhean.studentmanagement.repository.UserRepository;
import com.sun.jdi.request.DuplicateRequestException;
import org.hibernate.validator.internal.constraintvalidators.hv.NormalizedValidator;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public AuthenticationService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            NormalizedValidator normalizedValidator,
            UserMapper userMapper
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
    }

    @Transactional
    public User signUp(RegisterRequest request) {

        String email = normalize(request.email());

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

    @Transactional(readOnly = true)
    public User authenticate(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        normalize(request.email()),
                        request.password()
                )
        );

        // the principal is the user loaded by UserDetailsService, so not extra query
        return (User) authentication.getPrincipal();
    }

    private String normalize(String email) {
        return email.trim().toLowerCase();
    }
}

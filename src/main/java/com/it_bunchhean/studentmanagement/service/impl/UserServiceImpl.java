package com.it_bunchhean.studentmanagement.service.impl;

import com.it_bunchhean.studentmanagement.dto.request.UserRequest;
import com.it_bunchhean.studentmanagement.dto.response.UserResponse;
import com.it_bunchhean.studentmanagement.entity.User;
import com.it_bunchhean.studentmanagement.repository.UserRepository;
import com.it_bunchhean.studentmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public UserResponse register(UserRequest request) {
        return null;
    }

    @Override
    public UserResponse signIn(UserRequest request) {
        return null;
    }

    private UserResponse toResponse(User user) {
        if (user == null) return null;
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole().name())
                .build();
    }

}

package com.it_bunchhean.studentmanagement.mapper;

import com.it_bunchhean.studentmanagement.dto.request.RegisterRequest;
import com.it_bunchhean.studentmanagement.dto.response.UserResponse;
import com.it_bunchhean.studentmanagement.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public UserResponse toResponse(User user) {
        if (user == null) {
            return null;
        }

        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getFirstName(),
                user.getLastName(),
               user.getPhoneNumber()
        );

    }

    public List<UserResponse> toResponseList(List<User> users) {
        return users.stream()
                .map(this::toResponse)
                .toList();
    }

    public User toEntity(RegisterRequest request) {
        if (request == null) {
            return null;
        }

        return User.builder()
                .username(request.username().trim())
                .email(request.email().trim().toLowerCase())
                .build();
    }

    public void updateEntity(RegisterRequest request, User user) {
        if (request.username() != null) {
            user.setUsername(request.email().trim().toLowerCase());
        }

        if (request.email() != null) {
            user.setEmail(request.email().trim().toLowerCase());
        }
    }
}

package com.it_bunchhean.studentmanagement.mapper;

import com.it_bunchhean.studentmanagement.dto.request.RegisterRequest;
import com.it_bunchhean.studentmanagement.dto.request.UpdateUserRequest;
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
        if (users == null) {
            return List.of();
        }
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
        if (request == null || user == null) {
            return;
        }

        if (request.username() != null && !request.username().isBlank()) {
            user.setUsername(request.username().trim()); // Fixed: assigned username correctly
        }

        if (request.email() != null && !request.email().isBlank()) {
            user.setEmail(request.email().trim().toLowerCase());
        }
    }

    // Added method to handle profile updates using your UpdateUserRequest
    public void updateEntityFromRequest(UpdateUserRequest request, User user) {
        if (request == null || user == null) {
            return;
        }

        if (request.firstName() != null && !request.firstName().isBlank()) {
            user.setFirstName(request.firstName().trim());
        }

        if (request.lastName() != null && !request.lastName().isBlank()) {
            user.setLastName(request.lastName().trim());
        }

        if (request.phoneNumber() != null && !request.phoneNumber().isBlank()) {
            user.setPhoneNumber(request.phoneNumber().trim());
        }
    }

}

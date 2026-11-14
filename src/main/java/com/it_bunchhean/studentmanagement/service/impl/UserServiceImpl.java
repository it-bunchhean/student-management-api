package com.it_bunchhean.studentmanagement.service.impl;

import com.it_bunchhean.studentmanagement.constant.Role;
import com.it_bunchhean.studentmanagement.dto.request.ChangePasswordRequest;
import com.it_bunchhean.studentmanagement.dto.request.UpdateUserRequest;
import com.it_bunchhean.studentmanagement.dto.response.UserResponse;
import com.it_bunchhean.studentmanagement.entity.User;
import com.it_bunchhean.studentmanagement.exception.ResourceNotFoundException;
import com.it_bunchhean.studentmanagement.mapper.UserMapper;
import com.it_bunchhean.studentmanagement.repository.UserRepository;
import com.it_bunchhean.studentmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private PasswordEncoder passwordEncoder;
    private static final Set<String> SORTABLE = Set.of("id", "username", "email", "role");
    private static final int MAX_PAGE_SIZE = 100;

    @Override
    public UserResponse getUser(Long id) {
        System.out.print("Fetching user with ID: " + id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        return userMapper.toResponse(user);
    }

    @Override
    public Page<UserResponse> getAll(String keyword, Role role, Pageable pageable) {

        pageable.getSort().forEach(order -> {
            if (!SORTABLE.contains(order.getProperty())) {
                throw new IllegalArgumentException("Cannot sort by: " + order.getProperty());
            }
        });

        Pageable safe = PageRequest.of(
                pageable.getPageNumber(),
                Math.min(pageable.getPageSize(), MAX_PAGE_SIZE),
                pageable.getSort()
        );

        String normalized = (keyword == null || keyword.isBlank()) ? null : keyword.trim();
        return userRepository.search(normalized, role, pageable)
                .map(userMapper::toResponse);
    }

    @Override
    public UserResponse updateUser(Long userId, UpdateUserRequest request) {
        User user = findUserById(userId);

        userMapper.updateEntityFromRequest(request, user);

        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }

    @Override
    public void changePassword(Long userId, ChangePasswordRequest request) {
        User user = findUserById(userId);

        if (!passwordEncoder.matches(
                request.currentPassword(),
                user.getPassword()
        )) {
            throw new BadCredentialsException(
                    "Current password is incorrect"
            );
        }

        if (passwordEncoder.matches(
                request.newPassword(),
                user.getPassword()
        )) {
            throw new IllegalArgumentException(
                    "New password must be different from current password"
            );
        }

        user.setPassword(
                passwordEncoder.encode(request.newPassword())
        );

        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = findUserById(userId);
        userRepository.delete(user);
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with ID: " + userId
                ));
    }
}

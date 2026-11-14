package com.it_bunchhean.studentmanagement.service;


import com.it_bunchhean.studentmanagement.constant.Role;
import com.it_bunchhean.studentmanagement.dto.request.ChangePasswordRequest;
import com.it_bunchhean.studentmanagement.dto.request.UpdateUserRequest;
import com.it_bunchhean.studentmanagement.dto.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserResponse getUser(Long id);

    Page<UserResponse> getAll(String keyword, Role role, Pageable pageable);

    UserResponse updateUser(Long userId, UpdateUserRequest request);

    void changePassword(Long userId, ChangePasswordRequest request);

    void deleteUser(Long userId);
}

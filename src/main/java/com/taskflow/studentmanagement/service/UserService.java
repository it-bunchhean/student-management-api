package com.taskflow.studentmanagement.service;

import com.taskflow.studentmanagement.dto.request.UserRequest;
import com.taskflow.studentmanagement.dto.response.UserResponse;

public interface UserService {
    UserResponse register(UserRequest request);
    UserResponse signIn(UserRequest request);
}

package com.it_bunchhean.studentmanagement.service;

import com.it_bunchhean.studentmanagement.dto.request.UserRequest;
import com.it_bunchhean.studentmanagement.dto.response.UserResponse;

public interface UserService {
    UserResponse register(UserRequest request);
    UserResponse signIn(UserRequest request);
}

package com.it_bunchhean.studentmanagement.service;


import com.it_bunchhean.studentmanagement.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> findAllUsers();
}

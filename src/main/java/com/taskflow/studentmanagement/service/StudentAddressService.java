package com.taskflow.studentmanagement.service;

import com.taskflow.studentmanagement.dto.request.StudentAddressRequest;
import com.taskflow.studentmanagement.dto.response.StudentAddressResponse;

public interface StudentAddressService {

    StudentAddressResponse findByStudentId(Long studentId);
    StudentAddressResponse update(Long studentId, StudentAddressRequest request);
}
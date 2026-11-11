package com.it_bunchhean.studentmanagement.user;

import com.it_bunchhean.studentmanagement.dto.request.StudentAddressRequest;
import com.it_bunchhean.studentmanagement.dto.response.StudentAddressResponse;

public interface StudentAddressService {

    StudentAddressResponse findByStudentId(Long studentId);
    StudentAddressResponse update(Long studentId, StudentAddressRequest request);
}
package com.it_bunchhean.studentmanagement.user;

import com.it_bunchhean.studentmanagement.dto.request.DepartmentRequest;
import com.it_bunchhean.studentmanagement.dto.response.DepartmentResponse;

public interface DepartmentService 
        extends BaseService<DepartmentRequest, DepartmentResponse, Integer> {

        DepartmentResponse findByName(String name);
        
        DepartmentResponse update(Integer id, DepartmentRequest request);
} 

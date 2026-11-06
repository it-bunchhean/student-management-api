package com.taskflow.studentmanagement.service;

import com.taskflow.studentmanagement.dto.request.DepartmentRequest;
import com.taskflow.studentmanagement.dto.response.DepartmentResponse;

public interface DepartmentService 
        extends BaseService<DepartmentRequest, DepartmentResponse, Integer> {

        DepartmentResponse findByName(String name);
        
        DepartmentResponse update(Integer id, DepartmentRequest request);
} 

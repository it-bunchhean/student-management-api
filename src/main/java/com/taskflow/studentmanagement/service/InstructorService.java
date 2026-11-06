package com.taskflow.studentmanagement.service;

import com.taskflow.studentmanagement.dto.request.InstructorRequest;
import com.taskflow.studentmanagement.dto.response.InstructorResponse;

public interface InstructorService extends BaseService<InstructorRequest, InstructorResponse, Integer> {

    InstructorResponse update(Integer id, InstructorRequest request);
}

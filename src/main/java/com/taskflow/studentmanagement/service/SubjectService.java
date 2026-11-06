package com.taskflow.studentmanagement.service;

import com.taskflow.studentmanagement.dto.request.SubjectRequest;
import com.taskflow.studentmanagement.dto.response.SubjectResponse;

public interface SubjectService {

   SubjectResponse create(SubjectRequest request);

   SubjectResponse getById(Long id);
}

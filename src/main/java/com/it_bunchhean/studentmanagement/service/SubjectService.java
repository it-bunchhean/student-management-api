package com.it_bunchhean.studentmanagement.service;

import com.it_bunchhean.studentmanagement.dto.request.SubjectRequest;
import com.it_bunchhean.studentmanagement.dto.response.SubjectResponse;

public interface SubjectService {

   SubjectResponse create(SubjectRequest request);

   SubjectResponse getById(Long id);
}

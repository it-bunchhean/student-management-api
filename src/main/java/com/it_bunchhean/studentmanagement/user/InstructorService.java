package com.it_bunchhean.studentmanagement.user;

import com.it_bunchhean.studentmanagement.dto.request.InstructorRequest;
import com.it_bunchhean.studentmanagement.dto.response.InstructorResponse;

public interface InstructorService extends BaseService<InstructorRequest, InstructorResponse, Integer> {

    InstructorResponse update(Integer id, InstructorRequest request);
}

package com.it_bunchhean.studentmanagement.service;

import com.it_bunchhean.studentmanagement.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.it_bunchhean.studentmanagement.dto.request.CreateStudentRequest;
import com.it_bunchhean.studentmanagement.dto.request.UpdateStudentRequest;
import com.it_bunchhean.studentmanagement.dto.response.StudentResponse;

import java.util.List;

public interface StudentService {

    StudentResponse create(CreateStudentRequest request);

    StudentResponse getById(Long id);

    Page<StudentResponse> getStudentWithFilter(String status, Pageable pageable);

    StudentResponse update(Long id, UpdateStudentRequest request);

    void delete(Long id);

    List<Student> getStudentSortByKhName();

    List<Student> getStudentSortByEnName();

}

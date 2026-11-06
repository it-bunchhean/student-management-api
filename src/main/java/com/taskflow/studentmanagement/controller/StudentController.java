package com.taskflow.studentmanagement.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.taskflow.studentmanagement.dto.request.CreateStudentRequest;
import com.taskflow.studentmanagement.dto.request.UpdateStudentRequest;
import com.taskflow.studentmanagement.dto.response.StudentResponse;
import com.taskflow.studentmanagement.service.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentResponse> create(@Valid @RequestBody CreateStudentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> update(@PathVariable Long id,
                                                  @RequestBody UpdateStudentRequest request
    ) {
        return ResponseEntity.ok(studentService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<StudentResponse>> getStudents(
            @RequestParam(required = false) String status,
            @ParameterObject Pageable pageable
    ) {
       pageable = validationPageable(pageable);
       return ResponseEntity.ok(studentService.getStudentWithFilter(status, pageable));
    }

    private Pageable validationPageable(Pageable pageable) {
        int maxSize = 100;
        if (pageable.getPageSize() > maxSize) {
            pageable = PageRequest.of(
                    pageable.getPageNumber(),
                    maxSize,
                    pageable.getSort()
            );
        }
        return pageable;
    }

}

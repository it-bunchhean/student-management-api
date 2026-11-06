package com.taskflow.studentmanagement.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taskflow.studentmanagement.exception.ResourceNotFoundException;
import com.taskflow.studentmanagement.dto.request.DepartmentRequest;
import com.taskflow.studentmanagement.dto.response.DepartmentResponse;
import com.taskflow.studentmanagement.service.DepartmentService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentResponse> create(@Valid @RequestBody DepartmentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.save(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getByName(@PathVariable Integer id) {
        return ResponseEntity.ok(departmentService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id )));
    }

    @GetMapping
    public ResponseEntity<Page<DepartmentResponse>> getAll(Pageable pageable) {
        return ResponseEntity.ok(departmentService.findAll(pageable));
    }

    @GetMapping(params = "name")
    public ResponseEntity<DepartmentResponse> getByName(@RequestParam @NotBlank(message = "Name parameter connot be blank") String name) {
        return ResponseEntity.ok(departmentService.findByName(name));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        departmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

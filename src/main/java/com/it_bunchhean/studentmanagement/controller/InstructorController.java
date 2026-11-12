package com.it_bunchhean.studentmanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it_bunchhean.studentmanagement.dto.request.InstructorRequest;
import com.it_bunchhean.studentmanagement.dto.response.InstructorResponse;
import com.it_bunchhean.studentmanagement.exception.ResourceNotFoundException;
import com.it_bunchhean.studentmanagement.service.InstructorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/instructors")
@RequiredArgsConstructor
public class InstructorController {
    
    private final InstructorService instructorService;

    @PostMapping
    public ResponseEntity<InstructorResponse> create(@Valid @RequestBody InstructorRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(instructorService.save(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstructorResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(instructorService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor or Prof. not found"))
        );
    }
    
}


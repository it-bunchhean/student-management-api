package com.taskflow.studentmanagement.service.impl;

import org.springframework.stereotype.Service;

import com.taskflow.studentmanagement.entity.Instructor;
import com.taskflow.studentmanagement.exception.ResourceNotFoundException;
import com.taskflow.studentmanagement.dto.request.InstructorRequest;
import com.taskflow.studentmanagement.dto.response.InstructorResponse;
import com.taskflow.studentmanagement.mapper.InstructorMapper;
import com.taskflow.studentmanagement.repository.InstructorRepository;
import com.taskflow.studentmanagement.service.InstructorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InstructorServiceImpl extends BaseServiceImpl<Instructor, InstructorRequest, InstructorResponse, Integer,
        InstructorRepository, InstructorMapper> implements InstructorService {

    public InstructorServiceImpl(InstructorRepository repository, InstructorMapper mapper) {
       super(repository, mapper);
    }

    @Override
    public InstructorResponse update(Integer id, InstructorRequest request) {

        log.info("Updating student with id: {}", id);

        Instructor instructor = repository.findById(id)
                .orElseThrow(() -> {
                    log.info("Instructor not found with id: {}", id);
                    return new ResourceNotFoundException("Instructor not found");
                });

        mapper.partialUpdate(request, instructor);

        Instructor updated = repository.save(instructor);

        log.info("Instructor updated successfully with id: {}", id);

        return mapper.toResponse(updated);
    }
}

package com.it_bunchhean.studentmanagement.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.it_bunchhean.studentmanagement.dto.request.CreateStudentRequest;
import com.it_bunchhean.studentmanagement.dto.request.UpdateStudentRequest;
import com.it_bunchhean.studentmanagement.dto.response.StudentResponse;
import com.it_bunchhean.studentmanagement.entity.Student;
import com.it_bunchhean.studentmanagement.exception.ResourceNotFoundException;
import com.it_bunchhean.studentmanagement.mapper.StudentMapper;
import com.it_bunchhean.studentmanagement.repository.StudentRepository;
import com.it_bunchhean.studentmanagement.service.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentResponse create(CreateStudentRequest request) {

        log.info("Creating student with code: {}", request.getCode());

        Student student = studentMapper.toEntity(request);

        if (student.getAddress() != null) {
            student.getAddress().setStudent(student);
            log.info("Student address mapped successfully");
        }

        Student savedStudent = studentRepository.save(student);

        log.info("Student created successfully with id: {}", savedStudent.getId());

        return studentMapper.toResponse(savedStudent);
    }

    @Override
    public StudentResponse getById(Long id) {

        log.info("Fetching student by id: {}", id);

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Student not found with id: {}", id);
                    return new ResourceNotFoundException("Student not found");
                });

        log.info("Student fetched successfully with id: {}", id);

        return studentMapper.toResponse(student);
    }

    @Override
    public Page<StudentResponse> getStudentWithFilter(String status, Pageable pageable) {
        Specification<Student> specification = ((root, query, criteriaBuilder) ->
                status != null ? criteriaBuilder.equal(root.get("status"), status) : null
        );
        return studentRepository.findAll( specification, pageable)
                .map(studentMapper::toResponse);
    }

    @Override
    public StudentResponse update(Long id, UpdateStudentRequest request) {

        log.info("Updating student with id: {}", id);

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.info("Student not found with id: {}", id);
                    return new ResourceNotFoundException("Student not found");
                });

        studentMapper.updateFromRequest(request, student);

        Student updatedStudent = studentRepository.save(student);

        log.info("Student updated successfully with id: {}", id);

        return studentMapper.toResponse(updatedStudent);
    }

    @Override
    public void delete(Long id) {

        log.info("Deleting student with id: {}", id);

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Student not found with id: {}", id);
                    return new ResourceNotFoundException("Student not found");
                });

        student.setDeleted(true);

        studentRepository.save(student);

        log.info("Student deleted successfully with id: {}", id);
    }

    @Override
    public List<Student> getStudentSortByKhName() {
        return studentRepository.findAll(Sort.by(
                Sort.Order.asc("khLastName"),
                Sort.Order.asc("khFirstName")
        ));
    }

    @Override
    public List<Student> getStudentSortByEnName() {
        return studentRepository.findAll(Sort.by(
               Sort.Order.asc("enLastName"),
                Sort.Order.asc("enFirstName")
        ));
    }

}

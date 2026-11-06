package com.taskflow.studentmanagement.service.impl;

import org.springframework.stereotype.Service;

import com.taskflow.studentmanagement.dto.request.DepartmentRequest;
import com.taskflow.studentmanagement.dto.response.DepartmentResponse;
import com.taskflow.studentmanagement.entity.Department;
import com.taskflow.studentmanagement.exception.ResourceNotFoundException;
import com.taskflow.studentmanagement.mapper.DepartmentMapper;
import com.taskflow.studentmanagement.repository.DepartmentRepository;
import com.taskflow.studentmanagement.service.DepartmentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DepartmentServiceImpl 
        extends BaseServiceImpl<
        Department,
        DepartmentRequest,
        DepartmentResponse,
        Integer,
        DepartmentRepository,
        DepartmentMapper>
    implements DepartmentService {

   public DepartmentServiceImpl(DepartmentRepository repository, DepartmentMapper mapper) {
       super(repository, mapper);
   }

   @Override
   public DepartmentResponse findByName(String name) {
       return repository.findByNameIgnoreCase(name)
           .map(mapper::toResponse)
           .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
   }

   @Override
   public DepartmentResponse update(Integer id, DepartmentRequest request) {
       log.info("Updatiing department with id: {}", id);
       Department department = repository.findById(id)
           .orElseThrow(() -> {
               log.info("Department not found with id: {}", id);
               return new ResourceNotFoundException("Department not found");
           });
       mapper.partialUpdate(request, department);
       Department updateDepartment = repository.save(department);
       log.info("Department updated successfullyy with id: {}", id);
       return mapper.toResponse(updateDepartment);
   }

}

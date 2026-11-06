package com.it_bunchhean.studentmanagement.service.impl;

import org.springframework.stereotype.Service;

import com.it_bunchhean.studentmanagement.dto.request.DepartmentRequest;
import com.it_bunchhean.studentmanagement.dto.response.DepartmentResponse;
import com.it_bunchhean.studentmanagement.entity.Department;
import com.it_bunchhean.studentmanagement.exception.ResourceNotFoundException;
import com.it_bunchhean.studentmanagement.mapper.DepartmentMapper;
import com.it_bunchhean.studentmanagement.repository.DepartmentRepository;
import com.it_bunchhean.studentmanagement.service.DepartmentService;

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

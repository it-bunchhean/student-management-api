package com.taskflow.studentmanagement.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.taskflow.studentmanagement.entity.Department;

@Repository
public interface DepartmentRepository extends BaseRepository<Department, Integer>{
    Optional<Department> findByNameIgnoreCase(String name);
    List<Department> findAllByActiveTrue();
}

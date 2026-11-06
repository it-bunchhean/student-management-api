package com.it_bunchhean.studentmanagement.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.it_bunchhean.studentmanagement.entity.Department;

@Repository
public interface DepartmentRepository extends BaseRepository<Department, Integer>{
    Optional<Department> findByNameIgnoreCase(String name);
    List<Department> findAllByActiveTrue();
}

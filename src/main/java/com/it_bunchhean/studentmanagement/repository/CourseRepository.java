package com.it_bunchhean.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it_bunchhean.studentmanagement.entity.Course;

public interface CourseRepository  extends JpaRepository<Course, Long>{
      
} 
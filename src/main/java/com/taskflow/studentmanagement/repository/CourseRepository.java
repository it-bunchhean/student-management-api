package com.taskflow.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskflow.studentmanagement.entity.Course;

public interface CourseRepository  extends JpaRepository<Course, Long>{
      
} 
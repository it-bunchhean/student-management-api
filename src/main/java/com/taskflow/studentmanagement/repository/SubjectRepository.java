package com.taskflow.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskflow.studentmanagement.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}

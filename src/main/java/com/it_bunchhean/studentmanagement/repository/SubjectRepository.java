package com.it_bunchhean.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it_bunchhean.studentmanagement.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}

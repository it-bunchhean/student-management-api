package com.it_bunchhean.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it_bunchhean.studentmanagement.entity.StudentAddress;

public interface StudentAddressRepository extends JpaRepository<StudentAddress, Long> {
}

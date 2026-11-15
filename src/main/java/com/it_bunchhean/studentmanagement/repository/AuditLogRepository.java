package com.it_bunchhean.studentmanagement.repository;

import com.it_bunchhean.studentmanagement.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
   List<AuditLog> findAll();
}

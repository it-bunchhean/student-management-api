package com.it_bunchhean.studentmanagement.service.impl;

import com.it_bunchhean.studentmanagement.dto.response.AuditLogResponse;
import com.it_bunchhean.studentmanagement.mapper.AuditLogMapper;
import com.it_bunchhean.studentmanagement.repository.AuditLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditLogServiceImpl {

    private final AuditLogRepository auditLogRepository;
    private final AuditLogMapper auditLogMapper;

    public AuditLogServiceImpl(
            AuditLogRepository auditLogRepository,
            AuditLogMapper auditLogMapper
    ) {
        this.auditLogRepository = auditLogRepository;
        this.auditLogMapper = auditLogMapper;
    }

    public List<AuditLogResponse> getAllLogs() {
        return auditLogRepository.findAll()
                .stream()
                .map(auditLogMapper::auditLogResponse)
                .toList();
    }
}

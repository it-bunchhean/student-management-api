package com.it_bunchhean.studentmanagement.controller;

import com.it_bunchhean.studentmanagement.dto.response.AuditLogResponse;
import com.it_bunchhean.studentmanagement.service.impl.AuditLogServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/audit")
public class AuditLogController {

    private final AuditLogServiceImpl auditLogService;

    public AuditLogController(AuditLogServiceImpl auditLogService) {
        this.auditLogService = auditLogService;
    }

    @GetMapping
    public ResponseEntity<List<AuditLogResponse>> getAll() {
        return ResponseEntity.ok(auditLogService.getAllLogs());
    }
}

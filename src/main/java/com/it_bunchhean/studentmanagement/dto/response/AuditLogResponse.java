package com.it_bunchhean.studentmanagement.dto.response;

import java.time.LocalDateTime;
import java.util.Map;

public record AuditLogResponse(
        Long id,
        String action,
        LocalDateTime changeAt,
        String recordId,
        String tableName,
        Map<String, Object> oldData,
        Map<String, Object> newData
) {}

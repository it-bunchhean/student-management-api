package com.it_bunchhean.studentmanagement.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.it_bunchhean.studentmanagement.dto.response.AuditLogResponse;
import com.it_bunchhean.studentmanagement.entity.AuditLog;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AuditLogMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public AuditLogResponse auditLogResponse(AuditLog auditLog) {
        if (auditLog == null) {
            return null;
        }

        return new AuditLogResponse(
                auditLog.getId(),
                auditLog.getAction(),
                auditLog.getChangeAt(),
                auditLog.getTableName(),
                auditLog.getRecordId() != null ? auditLog.getRecordId().toString() : null,
                jsonToMap(auditLog.getOldData()),
                jsonToMap(auditLog.getNewData())
        );
    }

    private Map<String, Object> jsonToMap(String json) {
        try {

            if (json == null) {
                return null;
            }

            return objectMapper.readValue(json, Map.class);

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON", e);
        }
    }

}

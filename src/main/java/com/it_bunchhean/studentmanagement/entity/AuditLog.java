package com.it_bunchhean.studentmanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_audit_log")
@Getter
@Setter
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "audit_id", updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String tableName;

    @Column(name = "record_id")
    private Long recordId;

    private String action;

    @Column(name = "new_data", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private String newData;

    @Column(name = "old_data", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private String oldData;

    @Column(name = "change_at")
    private LocalDateTime changeAt;
}

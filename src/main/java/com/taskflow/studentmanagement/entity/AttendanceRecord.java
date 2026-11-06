package com.taskflow.studentmanagement.entity;

import java.time.LocalDateTime;

public class AttendanceRecord {

    private Long id;
    private Student studentId;
    private Course courseId;
    private LocalDateTime date;
    private String status;
    private String note;
    private LocalDateTime timestamp;
}

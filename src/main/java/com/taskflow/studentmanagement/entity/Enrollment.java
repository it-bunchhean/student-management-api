package com.taskflow.studentmanagement.entity;

import java.time.LocalDateTime;

import com.taskflow.studentmanagement.constant.EnrollmentStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Enrollment {

    private Long id;

    private Student studentId;

    private Course courseId;

    private LocalDateTime enrollmentDate;

    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

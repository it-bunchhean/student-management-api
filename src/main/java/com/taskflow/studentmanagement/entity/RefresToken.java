package com.taskflow.studentmanagement.entity;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefresToken {
    
    private Long id;

    private String token;

    private LocalDateTime expiryAt;
}

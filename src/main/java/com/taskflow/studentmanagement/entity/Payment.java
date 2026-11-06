package com.taskflow.studentmanagement.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {
    
    private Long id;
    //private Student studentId;
    private BigDecimal amount;
    private String currency;
    private String type;
    private String status;
    private LocalDateTime dueDate;
    private LocalDateTime paidDate;
    private String semester;
    private String academicYear;
    private String invoiceNumber;
    private String description;
    private String paymentMethod;
    private LocalDateTime createdAt;

}

package com.taskflow.studentmanagement.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Grade {
   
    private Long id;
    //private Student studentId;
    //private Course courseId;
    //private Instructor teacherId;
    private String semester;
    private String academicYear;
    private BigDecimal midtermScore; 
    private BigDecimal finalScore;
    private BigDecimal assignmentScore;
    private BigDecimal totalScore;
    private String letterGrade;
    private BigDecimal gpaPoints;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

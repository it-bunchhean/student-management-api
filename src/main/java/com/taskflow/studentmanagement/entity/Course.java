package com.taskflow.studentmanagement.entity;

import java.time.LocalDateTime;

import com.taskflow.studentmanagement.constant.SemesterStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "tbl_courses")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;

    @Column(name = "course_name", nullable = false , length = 50)
    private String name;

    private String description;

    private String schedule;

    private String room;

    @Enumerated(EnumType.STRING)
    private SemesterStatus semester;

    private String academicYear;

    private int creditHouse;

    private int maxCapacity;

    private LocalDateTime startAt;

    private LocalDateTime endAt;
    
}

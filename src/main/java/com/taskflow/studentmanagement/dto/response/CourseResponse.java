package com.taskflow.studentmanagement.dto.response;

import com.taskflow.studentmanagement.constant.SemesterStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponse {

    private Long id;

    private String name;

    private String description;

    private String schedule;

    private String room;

    private SemesterStatus semester;

    private String academicYear;

    private int creditHouse;

    private int maxCapacity;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

}

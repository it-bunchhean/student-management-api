package com.taskflow.studentmanagement.dto.request;

import java.time.LocalDateTime;
import com.taskflow.studentmanagement.constant.SemesterStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateCourseRequest {

     String name;

     String description;

     String schedule;

     String room;

     SemesterStatus semester;

     String academicYear;

     int creditHouse;

     int maxCapacity;

     LocalDateTime startAt;

     LocalDateTime endAt;
    
     
}

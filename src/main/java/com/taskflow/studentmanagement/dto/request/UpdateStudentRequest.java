package com.taskflow.studentmanagement.dto.request;

import com.taskflow.studentmanagement.entity.StudentAddress;

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
public class UpdateStudentRequest {

    Long id;

    String code;

    String khFirstName;

    String khLastName;

    String enFirstName;

    String enLastName;

    String dob;

    String email;

    String phone;

    String gender;

    StudentAddress address;
}

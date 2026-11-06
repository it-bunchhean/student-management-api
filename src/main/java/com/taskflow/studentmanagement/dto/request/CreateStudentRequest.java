package com.taskflow.studentmanagement.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateStudentRequest {

    String code;

    String khFirstName;

    String khLastName;

    String enFirstName;

    String enLastName;

    String dob;

    String email;

    String phone;

    String gender;

    StudentAddressRequest address;

}

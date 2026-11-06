package com.taskflow.studentmanagement.dto.request;

import com.taskflow.studentmanagement.dto.BaseRequestDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InstructorRequest extends BaseRequestDTO {

    @NotBlank(message = "Instructor Khmer name is required")
    private String khName;

    @NotBlank(message = "Instructor English name is required")
    private String enName;

    private String specialization;

    private String email;

    private String phone;

    private String gender;

    private String specilization;

}

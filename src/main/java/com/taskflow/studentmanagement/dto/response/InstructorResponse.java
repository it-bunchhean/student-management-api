package com.taskflow.studentmanagement.dto.response;

import com.taskflow.studentmanagement.dto.BaseResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorResponse extends BaseResponseDTO {

    private String code;
    private String khName;
    private String enName;
    private String specialization;
    private String email;
    private String phone;
    private String gender;
    private String specilization;

}

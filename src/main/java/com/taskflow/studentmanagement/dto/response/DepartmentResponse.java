package com.taskflow.studentmanagement.dto.response;

import com.taskflow.studentmanagement.dto.BaseResponseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentResponse extends BaseResponseDTO {

    private String name;
    private String code;

}

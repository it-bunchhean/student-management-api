package com.it_bunchhean.studentmanagement.dto.request;

import com.it_bunchhean.studentmanagement.dto.BaseRequestDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
public class DepartmentRequest extends BaseRequestDTO {

    @NotBlank(message = "Department name is required")
    private String name;

    @NotBlank(message = "Department name is required")
    private String code;

}

package com.taskflow.studentmanagement.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubjectRequest {

    @NotBlank(message = "Subject name is required")
    String name;

    @NotBlank(message = "Subject code is required")
    String code;

    String description;

}

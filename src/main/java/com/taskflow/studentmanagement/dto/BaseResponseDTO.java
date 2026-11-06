package com.taskflow.studentmanagement.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseResponseDTO {

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

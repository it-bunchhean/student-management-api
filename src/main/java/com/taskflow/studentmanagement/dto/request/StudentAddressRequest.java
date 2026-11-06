package com.taskflow.studentmanagement.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentAddressRequest {

    String street;

    @NotBlank(message = "City is required")
    String city;

    String district;

    @NotBlank(message = "Country is required")
    String country;
}

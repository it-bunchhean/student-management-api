package com.it_bunchhean.studentmanagement.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChangePasswordRequest(
        @NotBlank(message = "Currect password is required")
        String currentPassword,

        @NotBlank(message = "Currect password is required")
        @Size(
                min = 8,
                max = 30,
                message = "Password must be between 8 and 72 characters"
        )
        String newPassword
) {}

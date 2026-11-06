package com.taskflow.studentmanagement.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

      @NotBlank(message = "Username is required")
      private String username; 

      @Email
      private String email;

      @NotBlank(message = "Password is required")
      private String password;

      @NotBlank(message = "Role is required")
      private String role;
}

package com.it_bunchhean.studentmanagement.dto.response;

import com.it_bunchhean.studentmanagement.constant.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private Role role;
}

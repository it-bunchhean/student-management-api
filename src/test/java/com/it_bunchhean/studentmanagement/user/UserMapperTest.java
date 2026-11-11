package com.it_bunchhean.studentmanagement.user;

import com.it_bunchhean.studentmanagement.constant.Role;
import com.it_bunchhean.studentmanagement.dto.request.RegisterRequest;
import com.it_bunchhean.studentmanagement.dto.response.UserResponse;
import com.it_bunchhean.studentmanagement.entity.User;
import com.it_bunchhean.studentmanagement.mapper.UserMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserMapperTest {

    private final UserMapper userMapper = new UserMapper();

    @Test
    void toResponse_mapsAllFields_andHidesPassword() {
        User user = User.builder()
                .id(1L)
                .username("chheandev")
                .email("chhean@gmail.com")
                .password("hashed")
                .role(Role.ADMIN)
                .build();

        UserResponse response = userMapper.toResponse(user);
        assertThat(response).isEqualTo(new UserResponse(1L, "chheandev", "chhean@gmail.com", Role.ADMIN));
    }

    @Test
    void toEntity_trimsAndLowercaseEmail_withoutPasswordOrRole() {
        User user = userMapper.toEntity(new RegisterRequest("chheandev", "chhean@gmail.com", "chhean168"));

        assertThat(user.getUsername()).isEqualTo("chheandev");
        assertThat(user.getEmail()).isEqualTo("chhean@gmail.com");
        assertThat(user.getPassword()).isNull();
        assertThat(user.getRole()).isNull();
    }
}

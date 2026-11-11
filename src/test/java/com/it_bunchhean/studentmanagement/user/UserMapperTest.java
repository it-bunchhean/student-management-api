package com.it_bunchhean.studentmanagement.user;

import com.it_bunchhean.studentmanagement.constant.Role;
import com.it_bunchhean.studentmanagement.dto.request.RegisterRequest;
import com.it_bunchhean.studentmanagement.dto.response.UserResponse;
import com.it_bunchhean.studentmanagement.entity.User;
import com.it_bunchhean.studentmanagement.mapper.UserMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static com.it_bunchhean.studentmanagement.data.TestData.*;

public class UserMapperTest {

    private final UserMapper userMapper = new UserMapper();

    @Test
    void toResponse_mapsAllFields_andHidesPassword() {
        User user = User.builder()
                .id(1L)
                .username(USERNAME)
                .email(EMAIL)
                .password(PASSWORD)
                .role(Role.USER)
                .build();

        UserResponse response = userMapper.toResponse(user);

        assertThat(response.getUsername()).isEqualTo(USERNAME);
        assertThat(response.getEmail()).isEqualTo(EMAIL);
    }

    @Test
    void toEntity_trimsAndLowercaseEmail_withoutPasswordOrRole() {
        User user = userMapper.toEntity(new RegisterRequest(USERNAME, EMAIL, PASSWORD));

        assertThat(user.getUsername()).isEqualTo(USERNAME);
        assertThat(user.getEmail()).isEqualTo(EMAIL);
        assertThat(user.getPassword()).isNull();
        assertThat(user.getRole()).isNull();
    }
}

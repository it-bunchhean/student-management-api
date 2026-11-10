package com.it_bunchhean.studentmanagement.service;

import com.it_bunchhean.studentmanagement.constant.Role;
import com.it_bunchhean.studentmanagement.dto.response.UserResponse;
import com.it_bunchhean.studentmanagement.entity.User;
import com.it_bunchhean.studentmanagement.mapper.UserMapper;
import com.it_bunchhean.studentmanagement.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Spy
    UserMapper userMapper = new UserMapper();

    @InjectMocks
    UserService userService;

    @Test
    void getAll_returnMappedPage() {
        User user = User.builder()
                .id(1L)
                .username("chhean")
                .email("chhean@gmail.com")
                .role(Role.USER)
                .build();

        Pageable pageable = PageRequest.of(0, 10);

        when(userRepository.search(null, null, pageable))
                .thenReturn(new PageImpl<>(List.of(user), pageable, 1));

        Page<UserResponse> result = userService.getAll(null, null, pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals("chhean", result.getContent().get(0).getUsername());
    }

}

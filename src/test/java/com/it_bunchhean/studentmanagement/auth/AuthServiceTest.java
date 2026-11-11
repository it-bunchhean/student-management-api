package com.it_bunchhean.studentmanagement.auth;

import com.it_bunchhean.studentmanagement.constant.Role;
import com.it_bunchhean.studentmanagement.dto.request.LoginRequest;
import com.it_bunchhean.studentmanagement.dto.request.RegisterRequest;
import com.it_bunchhean.studentmanagement.dto.response.AuthResponse;
import com.it_bunchhean.studentmanagement.entity.User;
import com.it_bunchhean.studentmanagement.mapper.UserMapper;
import com.it_bunchhean.studentmanagement.repository.UserRepository;
import com.it_bunchhean.studentmanagement.security.auth.AuthenticationService;
import com.it_bunchhean.studentmanagement.security.jwt.JwtService;
import com.it_bunchhean.studentmanagement.security.user.CustomUserDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    JwtService jwtService;

    @Mock
    UserMapper userMapper;

    @InjectMocks
    AuthenticationService authenticationService;

    @Test
    void register_success_encodePasswordAndAssignsUserRole() {
        RegisterRequest request = new RegisterRequest("chheandev", "chhean@gmail.com", "chhean168");

        when(userRepository.existsByEmail("chhean@gmail.com")).thenReturn(false);
        when(userRepository.existsByUsername("chhean")).thenReturn(false);
        when(passwordEncoder.encode("chhean168")).thenReturn("hashed");
        when(userRepository.save(any(User.class))).thenAnswer(inv -> {
            User user = inv.getArgument(0);
            user.setId(1L);
            return user;
        });

        User response = authenticationService.signUp(request);

        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getEmail()).isEqualTo("chhean@gmail.com");
        assertThat(response.getRole()).isEqualTo(Role.USER);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());
        assertThat(captor.getValue().getPassword()).isEqualTo("hashed");
    }

    @Test
    void register_duplicateEmail_throwsAndDoesNotSave() {
        RegisterRequest request = new RegisterRequest("chheandev", "chhean@gmail.com", "chhean168");

        when(userRepository.existsByEmail("chhean@gmail.com")).thenReturn(true);

        assertThatThrownBy(() -> authenticationService.signUp(request))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Email already registered");

        verify(userRepository, never()).save(any());
    }

    @Test
    void login_success_returnsToken() {
        User user = User.builder()
                .id(1L)
                .username("chheandev")
                .email("chhean@gamil.com")
                .password("hashed")
                .role(Role.USER)
                .build();

        CustomUserDetails principal = new CustomUserDetails(user);
        Authentication auth = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());

        when(authenticationManager.authenticate(any())).thenReturn(auth);
        when(jwtService.generateToken(principal)).thenReturn("jwt-token");
        when(jwtService.getExpirationTime()).thenReturn(null);

        AuthResponse response = authenticationService.authenticate(new LoginRequest("chhean@gmail.com", "chhean168"));

        assertThat(response.accessToken()).isEqualTo("jwt-token");
        assertThat(response.tokenType()).isEqualTo("Bearer");
        assertThat(response.expiresIn()).isEqualTo("1800");
    }

    @Test
    void login_badCredentials_propagatesException() {
        when(authenticationManager.authenticate(any()))
                .thenThrow(new BadCredentialsException("bad Ass"));

        assertThatThrownBy(() -> authenticationService.authenticate(new LoginRequest("chhean@gmail.com", "chhean168")))
                .isInstanceOf(BadCredentialsException.class);
    }


}

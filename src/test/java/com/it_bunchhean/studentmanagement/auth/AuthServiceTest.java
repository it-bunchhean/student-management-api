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
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.it_bunchhean.studentmanagement.data.TestData.*;
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

    @Spy
    UserMapper userMapper = new UserMapper();

    @InjectMocks
    AuthenticationService authenticationService;

    @Test
    void register_success_encodePasswordAndAssignsUserRole() {
        RegisterRequest request = new RegisterRequest(USERNAME, EMAIL, PASSWORD);

        when(userRepository.existsByEmail(EMAIL)).thenReturn(false);
        when(userRepository.existsByUsername(USERNAME)).thenReturn(false);
        when(passwordEncoder.encode(PASSWORD)).thenReturn("hashed");
        when(userRepository.save(any(User.class))).thenAnswer(inv -> {
            User user = inv.getArgument(0);
            user.setId(1L);
            return user;
        });

        User response = authenticationService.signUp(request);

        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getEmail()).isEqualTo(EMAIL);
        assertThat(response.getRole()).isEqualTo(Role.USER);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());
        assertThat(captor.getValue().getPassword()).isEqualTo("hashed");
    }

    @Test
    void register_duplicateEmail_throwsAndDoesNotSave() {
        RegisterRequest request = new RegisterRequest(USERNAME, EMAIL, PASSWORD);

        when(userRepository.existsByEmail(EMAIL)).thenReturn(true);

        assertThatThrownBy(() -> authenticationService.signUp(request))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Email already registered");

        verify(userRepository, never()).save(any());
    }

    @Test
    void login_success_returnsToken() {
        User user = new User(1L, USERNAME, EMAIL, PASSWORD, Role.USER, FIRSTNAME, LASTNAME, PHONENUMBER);

        CustomUserDetails principal = new CustomUserDetails(user);
        Authentication auth = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());

        when(authenticationManager.authenticate(any())).thenReturn(auth);
        when(jwtService.generateToken(principal)).thenReturn("jwt-token");
        when(jwtService.getExpirationTime()).thenReturn(1800L);

        AuthResponse response = authenticationService.authenticate(new LoginRequest("chhean@gmail.com", "chhean168"));

        assertThat(response.accessToken()).isEqualTo("jwt-token");
        assertThat(response.tokenType()).isEqualTo("Bearer");
        assertThat(response.expiresIn()).isEqualTo(1800L);
    }

    @Test
    void login_badCredentials_propagatesException() {
        when(authenticationManager.authenticate(any()))
                .thenThrow(new BadCredentialsException("bad"));

        assertThatThrownBy(() -> authenticationService.authenticate(
                new LoginRequest("%s".formatted(EMAIL), "%s".formatted(PASSWORD))))
                .isInstanceOf(BadCredentialsException.class);
    }


}

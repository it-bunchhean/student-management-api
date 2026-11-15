package com.it_bunchhean.studentmanagement.security.auth;

import com.it_bunchhean.studentmanagement.dto.request.RefreshTokenRequest;
import com.it_bunchhean.studentmanagement.dto.response.RefreshTokenResponse;
import com.it_bunchhean.studentmanagement.entity.RefreshToken;
import com.it_bunchhean.studentmanagement.service.RefreshTokenService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.it_bunchhean.studentmanagement.dto.request.LoginRequest;
import com.it_bunchhean.studentmanagement.dto.request.RegisterRequest;
import com.it_bunchhean.studentmanagement.dto.response.AuthResponse;
import com.it_bunchhean.studentmanagement.dto.response.UserResponse;
import com.it_bunchhean.studentmanagement.entity.User;
import com.it_bunchhean.studentmanagement.mapper.UserMapper;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

   private final AuthenticationService authenticationService;
   private final UserMapper userMapper;
   private final RefreshTokenService refreshTokenService;

   public AuthenticationController(
           AuthenticationService authenticationService,
           UserMapper userMapper,
           RefreshTokenService refreshTokenService
   ) {
       this.authenticationService = authenticationService;
       this.userMapper = userMapper;
       this.refreshTokenService = refreshTokenService;
   }

   @PostMapping("/signup")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request) {
       User registeredUser = authenticationService.signUp(request);
       return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toResponse(registeredUser));
   }

   @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody LoginRequest request) {
       return ResponseEntity.ok(authenticationService.authenticate(request));
   }

   @Operation(summary = "Retrieve new access token")
   @GetMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refreshToken(HttpServletRequest request) {
       String token = null;
       for (Cookie cookie : request.getCookies()) {
           if (cookie.getName().equals("token")) {
               token = cookie.getValue();
               break;
           }
       }

       RefreshTokenResponse response = refreshTokenService.verify(token);

       return ResponseEntity.ok().body(response);
   }
}

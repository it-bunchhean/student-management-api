package com.it_bunchhean.studentmanagement.security.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

   public AuthenticationController(
           AuthenticationService authenticationService,
           UserMapper userMapper
   ) {
       this.authenticationService = authenticationService;
       this.userMapper = userMapper;
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

}

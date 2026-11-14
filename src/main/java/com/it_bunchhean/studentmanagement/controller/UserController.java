package com.it_bunchhean.studentmanagement.controller;

import com.it_bunchhean.studentmanagement.constant.Role;
import com.it_bunchhean.studentmanagement.dto.request.ChangePasswordRequest;
import com.it_bunchhean.studentmanagement.dto.request.UpdateUserRequest;
import com.it_bunchhean.studentmanagement.dto.response.UserResponse;
import com.it_bunchhean.studentmanagement.repository.UserRepository;
import com.it_bunchhean.studentmanagement.security.user.CustomUserDetails;
import com.it_bunchhean.studentmanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserResponse> me(@AuthenticationPrincipal CustomUserDetails currentUser) {
        return ResponseEntity.ok(userService.getUser(currentUser.getId()));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public PagedModel<UserResponse> getAllUsers(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Role role,
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return new PagedModel<>(userService.getAll(keyword, role, pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PutMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserResponse> updateMe(
            @AuthenticationPrincipal CustomUserDetails currentUser,
            @Valid @RequestBody UpdateUserRequest request
    ) {
        return ResponseEntity.ok(
                userService.updateUser(currentUser.getId(),  request)
        );
    }

    @PatchMapping("/me/password")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> changePassword(
            @AuthenticationPrincipal CustomUserDetails currentUser,
            @Valid @RequestBody ChangePasswordRequest request
    ) {
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> deleteMe(
            @AuthenticationPrincipal CustomUserDetails currentUser
    ) {
        userService.deleteUser(currentUser.getId());

        return ResponseEntity.noContent().build();
    }
}

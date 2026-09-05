package com.example.todo;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDTO>> registerUser(
            @Valid @RequestBody UserRequestDTO request) {

        UserDTO savedUser = service.registerUser(request);

        ApiResponse<UserDTO> response =
                new ApiResponse<>(
                        true,
                        "User registered successfully",
                        savedUser
                );

        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> loginUser(
            @Valid @RequestBody LoginRequestDTO request) {

        LoginResponseDTO loginResponse =
                service.loginUser(request);

        ApiResponse<LoginResponseDTO> response =
                new ApiResponse<>(
                        true,
                        "Login successful",
                        loginResponse
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<CurrentUserDTO>> getCurrentUser() {

        CurrentUserDTO currentUser =
                service.getCurrentUser();

        ApiResponse<CurrentUserDTO> response =
                new ApiResponse<>(
                        true,
                        "Current user retrieved successfully",
                        currentUser
                );

        return ResponseEntity.ok(response);
    }
}
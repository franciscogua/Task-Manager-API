package com.franciscogua.proyectos.taskmanager.controller;

import com.franciscogua.proyectos.taskmanager.dto.LoginRequestDTO;
import com.franciscogua.proyectos.taskmanager.dto.LoginResponseDTO;
import com.franciscogua.proyectos.taskmanager.dto.UserCreateDTO;
import com.franciscogua.proyectos.taskmanager.dto.UserResponseDTO;
import com.franciscogua.proyectos.taskmanager.security.JwtUtil;
import com.franciscogua.proyectos.taskmanager.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Francisco
 */
@Tag(name = "Authentication", description = "Endpoints for user registration and login")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Operation(summary = "Registers a new user in the system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "User registered successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data (e.g., malformed email, short password)"),
        @ApiResponse(responseCode = "409", description = "The email or display name already exists")
    })
    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        UserResponseDTO createdUser = userService.createUser(userCreateDTO);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @Operation(summary = "Authenticates a user and returns a JWT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Authentication successful, JWT in the response"),
        @ApiResponse(responseCode = "401", description = "Invalid credentials (Unauthorized)")
    })
    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new LoginResponseDTO(jwt));
    }
}

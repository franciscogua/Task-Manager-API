package com.franciscogua.proyectos.taskmanager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Francisco
 */
@Getter
@Setter
public class LoginRequestDTO {

    @Schema(description = "User's registered email address.", example = "john.smith@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(description = "User's password.", example = "password123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}

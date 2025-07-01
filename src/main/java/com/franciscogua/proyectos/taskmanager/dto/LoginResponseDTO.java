package com.franciscogua.proyectos.taskmanager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author Francisco
 */
@Getter
@AllArgsConstructor
public class LoginResponseDTO {

    @Schema(description = "The JWT token for authenticating subsequent requests.")
    private String token;
}

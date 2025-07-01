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
public class UserResponseDTO {

    @Schema(description = "The user's unique identifier.", example = "101")
    private Long id;

    @Schema(description = "The user's public display name.", example = "johnsmith")
    private String displayName;

    @Schema(description = "The user's registered email address.", example = "john.smith@example.com")
    private String email;
}

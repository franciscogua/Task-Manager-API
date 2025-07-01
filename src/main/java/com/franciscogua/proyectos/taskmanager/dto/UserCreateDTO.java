package com.franciscogua.proyectos.taskmanager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Francisco
 */
@Getter
@Setter
public class UserCreateDTO {

    @Schema(description = "Unique display name for the user.", example = "johnsmith", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String displayName;

    @Schema(description = "Unique display name for the user.", example = "johnsmith", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @Schema(description = "User's unique email address. Will be used for login.", example = "john.smith@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
}

package com.franciscogua.proyectos.taskmanager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class ProjectCreateDTO {

    @Schema(description = "Name of the project.", example = "My First Web App", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Project name cannot be blank")
    @Size(min = 3, max = 100, message = "Project name must be between 3 and 100 characters")
    private String name;

    @Schema(description = "A brief description of the project.", example = "This is the backend API for my portfolio project.")
    @Size(max = 255, message = "Description can be up to 255 characters")
    private String description;
}

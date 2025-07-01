package com.franciscogua.proyectos.taskmanager.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Francisco
 */
@Getter
@Setter
public class ProjectResponseDTO {

    @Schema(description = "The project's unique identifier.", example = "1")
    private Long id;

    @Schema(description = "Name of the project.", example = "My First Web App")
    private String name;

    @Schema(description = "A brief description of the project.", example = "This is the backend API...")
    private String description;

    @Schema(description = "ID of the user who owns the project.", example = "101")
    private Long userId;
    
    @Schema(description = "Set of tasks associated with this project.")
    private Set<TaskResponseDTO> tasks;
}

package com.franciscogua.proyectos.taskmanager.dto;

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

    @NotBlank(message = "Project name cannot be blank")
    @Size(min = 3, max = 100, message = "Project name must be between 3 and 100 characters")
    private String name;

    @Size(max = 255, message = "Description can be up to 255 characters")
    private String description;
}

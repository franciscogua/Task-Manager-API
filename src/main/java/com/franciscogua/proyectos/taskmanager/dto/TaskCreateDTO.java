package com.franciscogua.proyectos.taskmanager.dto;

import com.franciscogua.proyectos.taskmanager.entity.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Francisco
 */
@Getter
@Setter
public class TaskCreateDTO {

    @NotBlank(message = "Task title cannot be blank")
    @Size(min = 3, max = 200, message = "Title must be between 3 and 200 characters")
    private String title;

    private Status status;

    @NotNull(message = "Project ID cannot be null")
    private Long projectId;

    private LocalDate dueDate;
}

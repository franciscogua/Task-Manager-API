package com.franciscogua.proyectos.taskmanager.dto;

import com.franciscogua.proyectos.taskmanager.entity.Status;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "Title or name of the task.", example = "Implement login endpoint", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Task title cannot be blank")
    @Size(min = 3, max = 200, message = "Title must be between 3 and 200 characters")
    private String title;

    @Schema(description = "Current status of the task.", example = "IN_PROGRESS", defaultValue = "PENDING")
    private Status status;

    @Schema(description = "ID of the project this task belongs to.", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Project ID cannot be null")
    private Long projectId;

    @Schema(description = "The due date for the task.", example = "2025-12-31")
    private LocalDate dueDate;
}

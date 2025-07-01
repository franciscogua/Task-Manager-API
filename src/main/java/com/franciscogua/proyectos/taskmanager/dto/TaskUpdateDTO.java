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
public class TaskUpdateDTO {

    @Schema(description = "Title or name of the task.", example = "Refactor login endpoint", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Task title cannot be blank")
    @Size(min = 3, max = 200)
    private String title;

    @Schema(description = "Current status of the task.", example = "DONE", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Status cannot be null")
    private Status status;
    
    @Schema(description = "The due date for the task.", example = "2025-12-30")
    private LocalDate dueDate;
}

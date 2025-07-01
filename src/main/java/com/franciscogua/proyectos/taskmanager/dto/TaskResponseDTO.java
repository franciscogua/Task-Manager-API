package com.franciscogua.proyectos.taskmanager.dto;

import com.franciscogua.proyectos.taskmanager.entity.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Francisco
 */
@Getter
@Setter
public class TaskResponseDTO {

    @Schema(description = "The unique identifier of the task.", example = "51")
    private Long id;

    @Schema(description = "The title of the task.", example = "Deploy application to production")
    private String title;

    @Schema(description = "The current status of the task.", example = "DONE")
    private Status status;

    @Schema(description = "The due date for the task.", example = "2025-08-01")
    private LocalDate dueDate;

    @Schema(description = "The ID of the project this task belongs to.", example = "10")
    private Long projectId;
}

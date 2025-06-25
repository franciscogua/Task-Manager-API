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
public class TaskUpdateDTO {

    @NotBlank(message = "Task title cannot be blank")
    @Size(min = 3, max = 200)
    private String title;

    @NotNull(message = "Status cannot be null")
    private Status status;

    private LocalDate dueDate;
}

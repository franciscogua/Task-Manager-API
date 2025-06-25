package com.franciscogua.proyectos.taskmanager.dto;

import com.franciscogua.proyectos.taskmanager.entity.Status;
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

    private Long id;
    private String title;
    private Status status;
    private LocalDate dueDate;
    private Long projectId;
}

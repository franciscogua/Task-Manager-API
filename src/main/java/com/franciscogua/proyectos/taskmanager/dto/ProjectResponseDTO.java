package com.franciscogua.proyectos.taskmanager.dto;

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
    private Long id;
    private String name;
    private String description;
    private Long userId;
    private Set<TaskResponseDTO> tasks;
}

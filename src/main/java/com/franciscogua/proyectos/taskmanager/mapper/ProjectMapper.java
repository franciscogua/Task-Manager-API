package com.franciscogua.proyectos.taskmanager.mapper;

import com.franciscogua.proyectos.taskmanager.dto.ProjectResponseDTO;
import com.franciscogua.proyectos.taskmanager.entity.Project;
import java.util.stream.Collectors;

/**
 *
 * @author Francisco
 */
public class ProjectMapper {

    public static ProjectResponseDTO toProjectResponseDTO(Project project) {
        if (project == null) {
            return null;
        }

        ProjectResponseDTO dto = new ProjectResponseDTO();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        dto.setUserId(project.getUser().getId());

        if (project.getTasks() != null) {
            dto.setTasks(project.getTasks().stream()
                    .map(TaskMapper::toTaskResponseDTO)
                    .collect(Collectors.toSet()));
        }
        
        return dto;
    }
}

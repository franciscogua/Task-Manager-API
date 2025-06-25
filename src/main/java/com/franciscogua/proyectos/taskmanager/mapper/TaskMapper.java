package com.franciscogua.proyectos.taskmanager.mapper;

import com.franciscogua.proyectos.taskmanager.dto.TaskResponseDTO;
import com.franciscogua.proyectos.taskmanager.entity.Task;

/**
 *
 * @author Francisco
 */
public class TaskMapper {

    public static TaskResponseDTO toTaskResponseDTO(Task task) {
        if (task == null) {
            return null;
        }
        
        TaskResponseDTO dto = new TaskResponseDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setStatus(task.getStatus());
        dto.setDueDate(task.getDueDate());
        dto.setProjectId(task.getProject().getId());

        return dto;
    }
}

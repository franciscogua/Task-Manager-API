package com.franciscogua.proyectos.taskmanager.service;

import com.franciscogua.proyectos.taskmanager.dto.TaskCreateDTO;
import com.franciscogua.proyectos.taskmanager.dto.TaskResponseDTO;
import com.franciscogua.proyectos.taskmanager.dto.TaskUpdateDTO;
import com.franciscogua.proyectos.taskmanager.entity.Project;
import com.franciscogua.proyectos.taskmanager.entity.Task;
import com.franciscogua.proyectos.taskmanager.exception.ResourceNotFoundException;
import com.franciscogua.proyectos.taskmanager.mapper.TaskMapper;
import com.franciscogua.proyectos.taskmanager.repository.ProjectRepository;
import com.franciscogua.proyectos.taskmanager.repository.TaskRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Francisco
 */
@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Transactional
    public TaskResponseDTO createTask(TaskCreateDTO taskDTO, Long userId) {
        Project project = projectRepository.findById(taskDTO.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + taskDTO.getProjectId()));

        if (!project.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("You cannot create a task in a project that does not belong to you.");
        }

        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setStatus(taskDTO.getStatus());
        task.setDueDate(taskDTO.getDueDate());
        task.setProject(project);

        Task savedTask = taskRepository.save(task);
        return TaskMapper.toTaskResponseDTO(savedTask);
    }

    @Transactional(readOnly = true)
    public List<TaskResponseDTO> getTasksByProjectId(Long projectId, Long userId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));

        if (!project.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("You do not have permission to view tasks for this project.");
        }

        return taskRepository.findByProjectId(projectId).stream()
                .map(TaskMapper::toTaskResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public TaskResponseDTO updateTask(Long taskId, TaskUpdateDTO taskDetails, Long userId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));

        if (!task.getProject().getUser().getId().equals(userId)) {
            throw new AccessDeniedException("You do not have permission to update this task.");
        }

        task.setTitle(taskDetails.getTitle());
        task.setStatus(taskDetails.getStatus());
        task.setDueDate(taskDetails.getDueDate());

        Task updatedTask = taskRepository.save(task);
        return TaskMapper.toTaskResponseDTO(updatedTask);
    }

    @Transactional
    public void deleteTask(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + taskId));

        if (!task.getProject().getUser().getId().equals(userId)) {
            throw new AccessDeniedException("You do not have permission to delete this task.");
        }

        taskRepository.delete(task);
    }
}

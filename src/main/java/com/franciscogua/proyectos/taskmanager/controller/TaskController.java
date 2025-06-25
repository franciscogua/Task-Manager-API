package com.franciscogua.proyectos.taskmanager.controller;

import com.franciscogua.proyectos.taskmanager.dto.TaskCreateDTO;
import com.franciscogua.proyectos.taskmanager.dto.TaskResponseDTO;
import com.franciscogua.proyectos.taskmanager.dto.TaskUpdateDTO;
import com.franciscogua.proyectos.taskmanager.entity.User;
import com.franciscogua.proyectos.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Francisco
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(
            @Valid @RequestBody TaskCreateDTO taskCreateDTO,
            @AuthenticationPrincipal User userDetails) {

        Long userId = userDetails.getId();

        TaskResponseDTO createdTask = taskService.createTask(taskCreateDTO, userId);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<TaskResponseDTO>> getTasksByProject(
            @PathVariable Long projectId,
            @AuthenticationPrincipal User userDetails) {

        Long userId = userDetails.getId();

        List<TaskResponseDTO> tasks = taskService.getTasksByProjectId(projectId, userId);
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponseDTO> updateTask(
            @PathVariable Long taskId,
            @Valid @RequestBody TaskUpdateDTO taskUpdateDTO,
            @AuthenticationPrincipal User userDetails) {

        Long userId = userDetails.getId();

        TaskResponseDTO updatedTask = taskService.updateTask(taskId, taskUpdateDTO, userId);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable Long taskId,
            @AuthenticationPrincipal User userDetails) {

        Long userId = userDetails.getId();

        taskService.deleteTask(taskId, userId);
        return ResponseEntity.noContent().build();
    }
}

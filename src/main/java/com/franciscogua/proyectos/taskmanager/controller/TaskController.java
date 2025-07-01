package com.franciscogua.proyectos.taskmanager.controller;

import com.franciscogua.proyectos.taskmanager.dto.TaskCreateDTO;
import com.franciscogua.proyectos.taskmanager.dto.TaskResponseDTO;
import com.franciscogua.proyectos.taskmanager.dto.TaskUpdateDTO;
import com.franciscogua.proyectos.taskmanager.entity.User;
import com.franciscogua.proyectos.taskmanager.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Tasks", description = "Endpoints for managing tasks within projects")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Operation(summary = "Create a new task within a specific project")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Task created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token is missing or invalid"),
        @ApiResponse(responseCode = "403", description = "Forbidden - User does not have permission to add a task to this project"),
        @ApiResponse(responseCode = "404", description = "Project not found")
    })
    @PostMapping(produces = "application/json")
    public ResponseEntity<TaskResponseDTO> createTask(
            @Valid @RequestBody TaskCreateDTO taskCreateDTO,
            @AuthenticationPrincipal User userDetails) {

        Long userId = userDetails.getId();

        TaskResponseDTO createdTask = taskService.createTask(taskCreateDTO, userId);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all tasks for a specific project")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list of tasks"),
        @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token is missing or invalid"),
        @ApiResponse(responseCode = "403", description = "Forbidden - User does not have permission to view this project's tasks"),
        @ApiResponse(responseCode = "404", description = "Project not found")
    })
    @GetMapping(value = "/project/{projectId}", produces = "application/json")
    public ResponseEntity<List<TaskResponseDTO>> getTasksByProject(
            @PathVariable Long projectId,
            @AuthenticationPrincipal User userDetails) {

        Long userId = userDetails.getId();

        List<TaskResponseDTO> tasks = taskService.getTasksByProjectId(projectId, userId);
        return ResponseEntity.ok(tasks);
    }

    @Operation(summary = "Update an existing task by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Task updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token is missing or invalid"),
        @ApiResponse(responseCode = "403", description = "Forbidden - User does not have permission to update this task"),
        @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @PutMapping(value = "/{taskId}", produces = "application/json")
    public ResponseEntity<TaskResponseDTO> updateTask(
            @PathVariable Long taskId,
            @Valid @RequestBody TaskUpdateDTO taskUpdateDTO,
            @AuthenticationPrincipal User userDetails) {

        Long userId = userDetails.getId();

        TaskResponseDTO updatedTask = taskService.updateTask(taskId, taskUpdateDTO, userId);
        return ResponseEntity.ok(updatedTask);
    }

    @Operation(summary = "Delete a task by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Task deleted successfully"),
        @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token is missing or invalid"),
        @ApiResponse(responseCode = "403", description = "Forbidden - User does not have permission to delete this task"),
        @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable Long taskId,
            @AuthenticationPrincipal User userDetails) {

        Long userId = userDetails.getId();

        taskService.deleteTask(taskId, userId);
        return ResponseEntity.noContent().build();
    }
}

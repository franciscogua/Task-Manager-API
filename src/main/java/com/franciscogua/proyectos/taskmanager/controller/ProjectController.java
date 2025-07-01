package com.franciscogua.proyectos.taskmanager.controller;

import com.franciscogua.proyectos.taskmanager.dto.ProjectCreateDTO;
import com.franciscogua.proyectos.taskmanager.dto.ProjectResponseDTO;
import com.franciscogua.proyectos.taskmanager.dto.ProjectUpdateDTO;
import com.franciscogua.proyectos.taskmanager.entity.User;
import com.franciscogua.proyectos.taskmanager.service.ProjectService;
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
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Francisco
 */
@Tag(name = "Projects", description = "Endpoints for managing user projects")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Operation(summary = "Create a new project for the authenticated user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Project created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token is missing or invalid")
    })
    @PostMapping(produces = "application/json")
    public ResponseEntity<ProjectResponseDTO> createProject(
            @Valid @RequestBody ProjectCreateDTO projectCreateDTO,
            @AuthenticationPrincipal User userDetails) {

        Long userId = userDetails.getId();

        ProjectResponseDTO createdProject = projectService.createProject(projectCreateDTO, userId);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all projects for the authenticated user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list of projects"),
        @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token is missing or invalid")
    })
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ProjectResponseDTO>> getUserProjects(
            @AuthenticationPrincipal User userDetails) {

        Long userId = userDetails.getId();

        List<ProjectResponseDTO> projects = projectService.getAllProjectsByUserId(userId);
        return ResponseEntity.ok(projects);
    }

    @Operation(summary = "Update an existing project by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Project updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token is missing or invalid"),
        @ApiResponse(responseCode = "403", description = "Forbidden - User does not have permission to update this project"),
        @ApiResponse(responseCode = "404", description = "Project not found")
    })
    @PutMapping(value = "/{projectId}", produces = "application/json")
    public ResponseEntity<ProjectResponseDTO> updateProject(
            @PathVariable Long projectId,
            @Valid @RequestBody ProjectUpdateDTO projectUpdateDTO,
            @AuthenticationPrincipal User userDetails) {

        Long userId = userDetails.getId();

        ProjectResponseDTO updatedProject = projectService.updateProject(projectId, projectUpdateDTO, userId);
        return ResponseEntity.ok(updatedProject);
    }

    @Operation(summary = "Delete a project by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Project deleted successfully"),
        @ApiResponse(responseCode = "401", description = "Unauthorized - JWT token is missing or invalid"),
        @ApiResponse(responseCode = "403", description = "Forbidden - User does not have permission to delete this project"),
        @ApiResponse(responseCode = "404", description = "Project not found")
    })
    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(
            @PathVariable Long projectId,
            @AuthenticationPrincipal User userDetails) {

        Long userId = userDetails.getId();

        projectService.deleteProject(projectId, userId);
        return ResponseEntity.noContent().build();
    }
}

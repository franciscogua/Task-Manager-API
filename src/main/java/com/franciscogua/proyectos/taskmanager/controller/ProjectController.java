package com.franciscogua.proyectos.taskmanager.controller;

import com.franciscogua.proyectos.taskmanager.dto.ProjectCreateDTO;
import com.franciscogua.proyectos.taskmanager.dto.ProjectResponseDTO;
import com.franciscogua.proyectos.taskmanager.dto.ProjectUpdateDTO;
import com.franciscogua.proyectos.taskmanager.entity.User;
import com.franciscogua.proyectos.taskmanager.service.ProjectService;
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
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> createProject(
            @Valid @RequestBody ProjectCreateDTO projectCreateDTO,
            @AuthenticationPrincipal User userDetails) {

        Long userId = userDetails.getId();

        ProjectResponseDTO createdProject = projectService.createProject(projectCreateDTO, userId);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> getUserProjects(
            @AuthenticationPrincipal User userDetails) {

        Long userId = userDetails.getId();

        List<ProjectResponseDTO> projects = projectService.getAllProjectsByUserId(userId);
        return ResponseEntity.ok(projects);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectResponseDTO> updateProject(
            @PathVariable Long projectId,
            @Valid @RequestBody ProjectUpdateDTO projectUpdateDTO,
            @AuthenticationPrincipal User userDetails) {

        Long userId = userDetails.getId();

        ProjectResponseDTO updatedProject = projectService.updateProject(projectId, projectUpdateDTO, userId);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(
            @PathVariable Long projectId,
            @AuthenticationPrincipal User userDetails) {
        
        Long userId = userDetails.getId();
        
        projectService.deleteProject(projectId, userId);
        return ResponseEntity.noContent().build();
    }
}

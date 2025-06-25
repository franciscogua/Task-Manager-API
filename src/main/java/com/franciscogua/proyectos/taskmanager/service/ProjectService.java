package com.franciscogua.proyectos.taskmanager.service;

import com.franciscogua.proyectos.taskmanager.dto.ProjectCreateDTO;
import com.franciscogua.proyectos.taskmanager.dto.ProjectResponseDTO;
import com.franciscogua.proyectos.taskmanager.dto.ProjectUpdateDTO;
import com.franciscogua.proyectos.taskmanager.entity.Project;
import com.franciscogua.proyectos.taskmanager.entity.User;
import com.franciscogua.proyectos.taskmanager.exception.ResourceNotFoundException;
import com.franciscogua.proyectos.taskmanager.mapper.ProjectMapper;
import com.franciscogua.proyectos.taskmanager.repository.ProjectRepository;
import com.franciscogua.proyectos.taskmanager.repository.UserRepository;
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
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ProjectResponseDTO createProject(ProjectCreateDTO projectDTO, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot create project for a non-existent user with id: " + userId));

        Project project = new Project();
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setUser(user);

        Project savedProject = projectRepository.save(project);

        return ProjectMapper.toProjectResponseDTO(savedProject);
    }

    @Transactional(readOnly = true)
    public List<ProjectResponseDTO> getAllProjectsByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }

        return projectRepository.findByUserId(userId).stream()
                .map(ProjectMapper::toProjectResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProjectResponseDTO updateProject(Long projectId, ProjectUpdateDTO projectDetails, Long userId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));

        if (!project.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("You do not have permission to update this project.");
        }

        project.setName(projectDetails.getName());
        project.setDescription(projectDetails.getDescription());

        Project updatedProject = projectRepository.save(project);

        return ProjectMapper.toProjectResponseDTO(updatedProject);
    }

    @Transactional
    public void deleteProject(Long projectId, Long userId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));

        if (!project.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("You do not have permission to delete this project.");
        }

        projectRepository.delete(project);
    }
}

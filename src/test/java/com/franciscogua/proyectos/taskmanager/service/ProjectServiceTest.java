package com.franciscogua.proyectos.taskmanager.service;

import com.franciscogua.proyectos.taskmanager.dto.ProjectCreateDTO;
import com.franciscogua.proyectos.taskmanager.dto.ProjectResponseDTO;
import com.franciscogua.proyectos.taskmanager.entity.Project;
import com.franciscogua.proyectos.taskmanager.entity.User;
import com.franciscogua.proyectos.taskmanager.exception.ResourceNotFoundException;
import com.franciscogua.proyectos.taskmanager.repository.ProjectRepository;
import com.franciscogua.proyectos.taskmanager.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

/**
 *
 * @author Francisco
 */
@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ProjectService projectService;

    private User user;
    private Project project;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setDisplayName("testuser");
        user.setEmail("test@example.com");

        project = new Project();
        project.setId(10L);
        project.setName("Test Project");
        project.setUser(user);
    }

    @Test
    void whenCreateProject_withValidUser_thenSavesAndReturnsProject() {
        ProjectCreateDTO createDTO = new ProjectCreateDTO();
        createDTO.setName("New Test Project");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        when(projectRepository.save(any(Project.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ProjectResponseDTO response = projectService.createProject(createDTO, 1L);

        assertNotNull(response);
        assertEquals("New Test Project", response.getName());
        assertEquals(1L, response.getUserId());

        verify(userRepository).findById(1L);
        verify(projectRepository).save(any(Project.class));
    }

    @Test
    void whenCreateProject_withInvalidUser_thenThrowsResourceNotFoundException() {
        ProjectCreateDTO createDTO = new ProjectCreateDTO();
        createDTO.setName("Project for non-existent user");

        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            projectService.createProject(createDTO, 99L);
        });
    }
}

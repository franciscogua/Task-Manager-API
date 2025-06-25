package com.franciscogua.proyectos.taskmanager.repository;

import com.franciscogua.proyectos.taskmanager.entity.Project;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Francisco
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    public List<Project> findByUserId(Long userId);
}

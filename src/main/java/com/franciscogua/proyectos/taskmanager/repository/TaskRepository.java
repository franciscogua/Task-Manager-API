package com.franciscogua.proyectos.taskmanager.repository;

import com.franciscogua.proyectos.taskmanager.entity.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Francisco
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    public List<Task> findByProjectId(Long projectId);
}

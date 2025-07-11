package com.franciscogua.proyectos.taskmanager.repository;

import com.franciscogua.proyectos.taskmanager.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Francisco
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByDisplayName(String username);

    Optional<User> findByEmail(String email);
}

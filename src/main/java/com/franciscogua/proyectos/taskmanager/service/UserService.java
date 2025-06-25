package com.franciscogua.proyectos.taskmanager.service;

import com.franciscogua.proyectos.taskmanager.dto.UserCreateDTO;
import com.franciscogua.proyectos.taskmanager.dto.UserResponseDTO;
import com.franciscogua.proyectos.taskmanager.entity.User;
import com.franciscogua.proyectos.taskmanager.exception.ResourceNotFoundException;
import com.franciscogua.proyectos.taskmanager.exception.UserAlreadyExistsException;
import com.franciscogua.proyectos.taskmanager.mapper.UserMapper;
import com.franciscogua.proyectos.taskmanager.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Francisco
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponseDTO createUser(UserCreateDTO userCreateDTO) {
        userRepository.findByUsername(userCreateDTO.getUsername())
                .ifPresent(u -> {
                    throw new UserAlreadyExistsException("Username " + userCreateDTO.getUsername() + " is already taken.");
                });
        userRepository.findByEmail(userCreateDTO.getEmail())
                .ifPresent(u -> {
                    throw new UserAlreadyExistsException("Email " + userCreateDTO.getEmail() + " is already registered.");
                });

        User user = new User();
        user.setUsername(userCreateDTO.getUsername());
        user.setEmail(userCreateDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));

        User savedUser = userRepository.save(user);

        return UserMapper.toUserResponseDTO(savedUser);
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return UserMapper.toUserResponseDTO(user);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toUserResponseDTO)
                .collect(Collectors.toList());
    }

    public void deleteUser(Long id) {
        if(!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}

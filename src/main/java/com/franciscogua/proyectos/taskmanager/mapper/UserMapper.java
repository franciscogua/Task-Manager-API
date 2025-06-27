package com.franciscogua.proyectos.taskmanager.mapper;

import com.franciscogua.proyectos.taskmanager.dto.UserResponseDTO;
import com.franciscogua.proyectos.taskmanager.entity.User;

/**
 *
 * @author Francisco
 */
public class UserMapper {

    public static UserResponseDTO toUserResponseDTO(User user) {
        if (user == null) {
            return null;
        }

        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setDisplayName(user.getDisplayName());
        dto.setEmail(user.getEmail());
        return dto;
    }
}

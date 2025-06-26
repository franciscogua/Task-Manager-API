package com.franciscogua.proyectos.taskmanager.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Francisco
 */
@Getter
@Setter
public class UserResponseDTO {

    private Long id;
    private String displayName;
    private String email;
}

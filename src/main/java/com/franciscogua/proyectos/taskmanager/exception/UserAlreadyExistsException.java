package com.franciscogua.proyectos.taskmanager.exception;

/**
 *
 * @author Francisco
 */
public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}

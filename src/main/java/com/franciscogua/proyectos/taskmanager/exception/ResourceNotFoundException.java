package com.franciscogua.proyectos.taskmanager.exception;

/**
 *
 * @author Francisco
 */
public class ResourceNotFoundException extends RuntimeException{
    
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

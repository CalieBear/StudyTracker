package com.example.studytracker.exception;

/**
 * Exception thrown when a requested resource cannot be found.
 *
 * @author Calista LaPorte
 */
public class NotFoundException extends RuntimeException {

    /**
     * Creates a new not-found exception with a message.
     *
     * @param message error detail message
     */
    public NotFoundException(String message) {
        super(message);
    }
}

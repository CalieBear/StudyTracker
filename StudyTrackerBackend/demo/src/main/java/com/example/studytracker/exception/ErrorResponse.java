package com.example.studytracker.exception;

import java.time.LocalDateTime;

/**
 * Standardized error response payload for REST API failures.
 *
 * @author Calista LaPorte
 */
public class ErrorResponse {

    private int status;
    private String error;
    private String message;
    private LocalDateTime timestamp;
    private String path;

    /**
     * Default constructor.
     */
    public ErrorResponse() {}

    /**
     * Creates an error response object.
     *
     * @param status HTTP status code
     * @param error short error label
     * @param message detailed error message
     * @param timestamp error timestamp
     * @param path request path where the error occurred
     */
    public ErrorResponse(int status, String error, String message, LocalDateTime timestamp, String path) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = timestamp;
        this.path = path;
    }

    // GETTERS
    public int getStatus() {return status;}
    public String getError() {return error;}
    public String getMessage() {return message;}
    public LocalDateTime getTimestamp() {return timestamp;}
    public String getPath() {return path;}

    // SETTERS
    public void setStatus(int status) {this.status = status;}
    public void setError(String error) {this.error = error;}
    public void setMessage(String message) {this.message = message;}
    public void setTimestamp(LocalDateTime timestamp) {this.timestamp = timestamp;}
    public void setPath(String path) {this.path = path;}
}

package com.example.studytracker.dto;

import com.example.studytracker.model.Status;

import jakarta.validation.constraints.Size;

/**
 * Request payload for updating a task.
 *
 * <p>All fields are optional and can be provided partially.
 *
 * @author Calista LaPorte
 */
public class UpdateTaskRequest {

    @Size(max = 255)
    private String subject;

    @Size(max = 2000)
    private String description;

    @Size(max = 255)
    private String name;

    private Status status;

    /**
     * Default constructor.
     */
    public UpdateTaskRequest() {}

    /**
     * Creates a request for updating a task.
     *
     * @param subject the updated task subject
     * @param description the updated task details
     * @param name the updated task name
     * @param status the updated task status
     */
    public UpdateTaskRequest(String subject, String description, String name, Status status) {
        this.subject = subject;
        this.description = description;
        this.name = name;
        this.status = status;
    }

    // GETTERS
    public String getSubject() {return subject;}
    public String getName() {return name;}
    public String getDescription() {return description;}
    public Status getStatus() {return status;}

    // SETTERS
    public void setSubject(String subject) {this.subject = subject;}
    public void setName(String name) {this.name = name;}
    public void setDescription(String description) {this.description = description;}
    public void setStatus(Status status) {this.status = status;}

}

package com.example.studytracker.dto;

import com.example.studytracker.model.Status;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Request payload for creating a task.
 *
 * <p>Includes the task name and optional detail fields.
 *
 * @author Calista LaPorte
 */
public class CreateTaskRequest {

    @Size(max = 255)
    private String subject;

    @Size(max = 2000)
    private String description;

    @NotBlank(message = "Task must have a name")
    @Size(max = 255)
    private String name;

    @Enumerated(EnumType.STRING)
    private Status status;

    /**
     * Default constructor.
     */
    public CreateTaskRequest() {}

    /**
     * Creates a request for a new task.
     *
     * @param subject the task subject
     * @param description additional task details
     * @param name the task name
     * @param status the current task status
     */
    public CreateTaskRequest(String subject, String description, String name, Status status) {
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



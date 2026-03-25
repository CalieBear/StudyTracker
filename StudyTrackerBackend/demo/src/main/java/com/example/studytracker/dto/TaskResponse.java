package com.example.studytracker.dto;

import com.example.studytracker.model.Status;

/**
 * Response payload representing a task.
 *
 * @author Calista LaPorte
 */
public class TaskResponse {

    private Integer id;
    private String subject;
    private String description;
    private String name;
    private Status status;

    /**
     * Default constructor.
     */
    public TaskResponse() {}

    /**
     * Creates a task response object.
     *
     * @param id the task identifier
     * @param subject the task subject
     * @param description additional task details
     * @param name the task name
     * @param status the current task status
     */
    public TaskResponse(Integer id, String subject, String description, String name, Status status) {
        this.id = id;
        this.subject = subject;
        this.description = description;
        this.name = name;
        this.status = status;
    }

    // GETTERS
    public Integer getId() {return id;}
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

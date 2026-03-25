package com.example.studytracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

/**
 * Represents a task associated with a user.
 *
 * <p>This entity stores task details including subject, name, description,
 * and progress status.
 *
 * @author Calista LaPorte
 */
@Entity
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String subject;
    private String description;
    private String name;
    
    @Enumerated(EnumType.STRING)
    private Status status;

    // CONSTRUCTORS
    /**
     * Default constructor required by JPA.
     */
    public Task() {}

    /**
     * Creates a task with user, identifier, and subject.
     *
     * @param user the user who owns the task
     * @param id the task identifier
     * @param subject the task subject
     */
    public Task(User user, Integer id, String subject) {
        this.user = user;
        this.id = id;
        this.subject = subject;
    }

    /**
     * Creates a task with user, identifier, subject, and status.
     *
     * @param user the user who owns the task
     * @param id the task identifier
     * @param subject the task subject
     * @param status the current task status
     */
    public Task(User user, Integer id, String subject, Status status) {
        this.user = user;
        this.id = id;
        this.subject = subject;
        this.status = status;
    }

    /**
     * Creates a task with full details, including an explicit identifier.
     *
     * @param user the user who owns the task
     * @param id the task identifier
     * @param subject the task subject
     * @param name the task title
     * @param description additional details about the task
     * @param status the current task status
     */
    public Task(User user, Integer id, String subject, String name, String description, Status status) {
        this.user = user;
        this.id = id;
        this.subject = subject;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    /**
     * Creates a task with full details, without providing an identifier.
     *
     * @param user the user who owns the task
     * @param subject the task subject
     * @param name the task title
     * @param description additional details about the task
     * @param status the current task status
     */
    public Task(User user, String subject, String name, String description, Status status) {
        this.user = user;
        this.subject = subject;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    // GETTERS
    public Integer getId() {return id;}
    public String getSubject() {return subject;}
    public String getName() {return name;}
    public String getDescription() {return description;}
    public Status getStatus() {return status;}
    public User getUser() {return user;}

    // SETTERS
    public void setId(Integer id) {this.id = id;}
    public void setSubject(String subject) {this.subject = subject;}
    public void setName(String name) {this.name = name;}
    public void setDescription(String description) {this.description = description;}
    public void setStatus(Status status) {this.status = status;}
    public void setUser(User user) {this.user = user;}
}

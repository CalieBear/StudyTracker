package com.example.studytracker.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Represents a study session associated with a user.
 *
 * <p>Required fields: {@code id}, {@code user}, and {@code subject}.
 * Optional fields: {@code description}, {@code startTime}, and {@code endTime}.
 *
 * <p>This entity captures study activity and can represent both in-progress
 * sessions (no end time yet) and fully completed sessions.
 *
 * @author Calista LaPorte
 */
@Entity
public class StudySession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String subject;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    // CONSTRUCTORS
    /**
     * Default constructor required by JPA.
     */
    public StudySession() {}

    /**
     * Creates a study session with a subject and start time.
     *
     * @param subject the subject being studied
     * @param startTime when the session begins
     */
    public StudySession(String subject, LocalDateTime startTime) {
        this.subject = subject;
        this.startTime = startTime;
    }

    /**
     * Creates a study session with a subject, start time, and end time.
     *
     * @param subject the subject being studied
     * @param startTime when the session begins
     * @param endTime when the session ends
     */
    public StudySession(String subject, LocalDateTime startTime, LocalDateTime endTime) {
        this.subject = subject;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Creates a study session with a subject, description, start time, and end time.
     *
     * @param subject the subject being studied
     * @param description additional details about the session
     * @param startTime when the session begins
     * @param endTime when the session ends
     */
    public StudySession(String subject, String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.subject = subject;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }

    // GETTERS
    public Integer getId() {return id;}
    public String getSubject() {return subject;}
    public String getDescription() {return description;}
    public LocalDateTime getStartTime() {return startTime;}
    public LocalDateTime getEndTime() {return endTime;}
    public User getUser() {return user;}

    // SETTERS
    public void setId(Integer id) {this.id = id;}
    public void setSubject(String subject) {this.subject = subject;}
    public void setDescription(String description) {this.description = description;}
    public void setStartTime(LocalDateTime startTime) {this.startTime = startTime;}
    public void setEndTime(LocalDateTime endTime) {this.endTime = endTime;}
    public void setUser(User user) {this.user = user;}
}

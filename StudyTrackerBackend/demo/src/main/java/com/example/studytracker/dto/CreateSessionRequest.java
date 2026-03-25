package com.example.studytracker.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Request payload for creating a study session.
 *
 * <p>Includes optional session details and a required start time.
 *
 * @author Calista LaPorte
 */
public class CreateSessionRequest {

    @Size(max = 255)
    private String subject;

    @Size(max = 2000)
    private String description;

    @NotNull(message = "Start time must not be blank")
    private LocalDateTime startTime;

    // End time, when provided, must be after start time.
    private LocalDateTime endTime;

    /**
     * Default constructor.
     */
    public CreateSessionRequest() {}

    /**
     * Creates a request for a new study session.
     *
     * @param subject the session subject
     * @param description additional session details
     * @param startTime when the session begins
     * @param endTime when the session ends
     */
    public CreateSessionRequest(String subject, String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.subject = subject;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }

    // GETTERS
    public String getSubject() {return subject;}
    public String getDescription() {return description;}
    public LocalDateTime getStartTime() {return startTime;}
    public LocalDateTime getEndTime() {return endTime;}

    // SETTERS
    public void setSubject(String subject) {this.subject = subject;}
    public void setDescription(String description) {this.description = description;}
    public void setStartTime(LocalDateTime startTime) {this.startTime = startTime;}
    public void setEndTime(LocalDateTime endTime) {this.endTime = endTime;}


    @AssertTrue(message = "Endtime must be after Start Time")
    public boolean timeValidation() {
        if (endTime != null) {
            return endTime.isAfter(startTime);
        }
        return true;
    }
}


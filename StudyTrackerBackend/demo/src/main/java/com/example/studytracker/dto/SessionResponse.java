package com.example.studytracker.dto;

import java.time.LocalDateTime;

/**
 * Response payload representing a study session.
 *
 * @author Calista LaPorte
 */
public class SessionResponse {

    private String subject;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Default constructor.
     */
    public SessionResponse() {}

    /**
     * Creates a session response object.
     *
     * @param subject the session subject
     * @param description additional session details
     * @param startTime when the session started
     * @param endTime when the session ended
     */
    public SessionResponse(String subject, String description, LocalDateTime startTime, LocalDateTime endTime) {
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
    
}

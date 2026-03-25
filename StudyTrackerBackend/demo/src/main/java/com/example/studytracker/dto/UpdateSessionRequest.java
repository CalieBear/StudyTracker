package com.example.studytracker.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Size;

/**
 * Request payload for updating a study session.
 *
 * <p>All fields are optional and can be provided partially.
 *
 * @author Calista LaPorte
 */
public class UpdateSessionRequest {

    @Size(max = 255)
    private String subject;

    @Size(max = 2000)
    private String description;

    // Used with endTime for chronological validation.
    private LocalDateTime startTime;

    // Must be after startTime when both values are provided.
    private LocalDateTime endTime;

    /**
     * Default constructor.
     */
    public UpdateSessionRequest() {}

    /**
     * Creates a request for updating a study session.
     *
     * @param subject the updated session subject
     * @param description the updated session details
     * @param startTime the updated start time
     * @param endTime the updated end time
     */
    public UpdateSessionRequest(String subject, String description, LocalDateTime startTime, LocalDateTime endTime) {
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
        if (endTime != null && startTime != null) {
            return endTime.isAfter(startTime);
        }
        return true;
    }
}

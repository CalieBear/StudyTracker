package com.example.demo.dto;

import java.time.LocalDateTime;

public class UpdateSessionRequest {
    private String subject;
    private String description;
    private LocalDateTime startTime; 
    private LocalDateTime endTime; 

    public UpdateSessionRequest(){}
    public UpdateSessionRequest(String subject, String description, LocalDateTime startTime, LocalDateTime endTime){
        this.subject=subject;
        this.startTime=startTime;
        this.endTime=endTime;
        this.description=description;
    }

    public String getSubject(){return subject;}
    public String getDescription(){return description;}
    public LocalDateTime getStartTime(){return startTime;}
    public LocalDateTime getEndTime(){return endTime;}

    public void setSubject(String subject){this.subject=subject;}
    public void setDescription(String description){this.description=description;}
    public void setStartTime(LocalDateTime startTime){this.startTime=startTime;}
    public void setEndTime(LocalDateTime endTime){this.endTime=endTime;}
}

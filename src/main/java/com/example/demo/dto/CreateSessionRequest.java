package com.example.demo.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
public class CreateSessionRequest {
    private String subject;
    private String description;
    @NotNull(message = "Start time must not be blank")
    private LocalDateTime startTime; 
    private LocalDateTime endTime; 

    public CreateSessionRequest(){}
    public CreateSessionRequest(String subject, String description, LocalDateTime startTime, LocalDateTime endTime){
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


    //TEMP
    private Integer userId;
    public void setUserId(Integer userId){this.userId=userId;}
    public Integer getUserId(){return userId;}
}


package com.example.demo.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
public class CreateSessionRequest {
    @Size(max = 255)
    private String subject;
    @Size(max=2000)
    private String description;
    @NotNull(message = "Start time must not be blank")
    private LocalDateTime startTime; 
    //MAKE SURE END TIME IS AFTER START TIME
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


    @AssertTrue(message = "Endtime must be after Start Time")
    public boolean timeValidation(){
        if(endTime!=null){
            return endTime.isAfter(startTime);
        }
        return true;
    }
}


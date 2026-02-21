package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class StudySession {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    private String subject;
    private String description;
    private LocalDateTime startTime; 
    private LocalDateTime endTime; 

    public StudySession(){}
    public StudySession(String subject, LocalDateTime startTime){
        this.subject=subject;
        this.startTime=startTime;
    }
    public StudySession(String subject, LocalDateTime startTime, LocalDateTime endTime){
        this.subject=subject;
        this.startTime=startTime;
        this.endTime=endTime;
    }
    public StudySession(String subject, String description, LocalDateTime startTime, LocalDateTime endTime){
        this.subject=subject;
        this.startTime=startTime;
        this.endTime=endTime;
        this.description=description;
    }

    //gettors
    public Integer getId(){return id;}
    public String getSubject(){return subject;}
    public String getDescription(){return description;}
    public LocalDateTime getStartTime(){return startTime;}
    public LocalDateTime getEndTime(){return endTime;}
    public User getUser(){return user;}

    //setters
    public void setSubject(String subject){this.subject=subject;}
    public void setDescription(String description){this.description=description;}
    public void setStartTime(LocalDateTime startTime){this.startTime=startTime;}
    public void setEndTime(LocalDateTime endTime){this.endTime=endTime;}
    public void setUser(User user){this.user= user;}
}

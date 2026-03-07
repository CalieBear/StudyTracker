package com.example.demo.dto;
import com.example.demo.model.Status;

import jakarta.validation.constraints.Size;

public class UpdateTaskRequest {
    @Size(max=255)
    private String subject;
    @Size(max=2000)
    private String description;
    @Size(max=255)
    private String name;
    private Status status;

    public UpdateTaskRequest(){}
    public UpdateTaskRequest(String subject, String description, String name, Status status){
        this.subject=subject;
        this.description=description;
        this.name=name;
        this.status=status;
    }
    
    //GETTERS
    public String getSubject(){return subject;}
    public String getName(){return name;}
    public String getDescription(){return description;}
    public Status getStatus(){return status;}
    
    //SETTERS
    public void setSubject(String subject){this.subject=subject;}
    public void setName(String name){this.name=name;}
    public void setDescription(String description){this.description= description;}
    public void setStatus(Status status){this.status= status;}

}

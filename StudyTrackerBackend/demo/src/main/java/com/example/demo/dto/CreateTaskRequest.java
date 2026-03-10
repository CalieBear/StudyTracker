package com.example.demo.dto;
import com.example.demo.model.Status;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateTaskRequest {
    @Size(max = 255)
    private String subject;
    @Size(max = 2000)
    private String description;
    @NotBlank(message = "Task must have a name")
    @Size(max = 255)
    private String name;
    @Enumerated(EnumType.STRING)
    private Status status;

    //
    public CreateTaskRequest(){}
    public CreateTaskRequest(String subject, String description, String name, Status status){
        this.subject=subject;
        this.description=description;
        this.name = name;
        this.status = status;
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
    public void setStatus(Status status){this.status=status;}
}



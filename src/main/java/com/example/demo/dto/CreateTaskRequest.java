package com.example.demo.dto;
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

    //
    public CreateTaskRequest(){}
    public CreateTaskRequest(String subject, String description, String name){
        this.subject=subject;
        this.description=description;
        this.name = name;
    }
    //GETTERS
    public String getSubject(){return subject;}
    public String getName(){return name;}
    public String getDescription(){return description;}
    
    //SETTERS
    public void setSubject(String subject){this.subject=subject;}
    public void setName(String name){this.name=name;}
    public void setDescription(String description){this.description= description;}



    //TEMP
    private Integer userId;
    public void setUserId(Integer userId){this.userId=userId;}
    public Integer getUserId(){return userId;}
}



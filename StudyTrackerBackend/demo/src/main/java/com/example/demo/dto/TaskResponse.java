package com.example.demo.dto;
import com.example.demo.model.Status;
public class TaskResponse {
    
    private Integer id;
    private String subject;
    private String description;
    private String name;
    private Status status;

    public TaskResponse(){}
    public TaskResponse(Integer id,String subject, String description, String name, Status status){
        this.id=id;
        this.subject=subject;
        this.description=description;
        this.name=name;
        this.status=status;
    }
    
    //GETTERS
    public Integer getId(){return id;}
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

package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    private String subject;
    private String description;
    private String name;
    // private String status;
    
    @Enumerated(EnumType.STRING)
    private Status status;

    public Task(){}
    public Task(User user,Integer id, String subject){
        this.user = user;
        this.id = id;
        this.subject = subject;
    }
    public Task(User user, Integer id, String subject, Status status){
        this.user = user;
        this.id = id;
        this.subject = subject;
        this.status=status;
    }
    public Task(User user, Integer id, String subject, String name, String description, Status status){
        this.user = user;
        this.id = id;
        this.subject = subject;
        this.name=name;
        this.description=description;
        this.status=status;
    }
    public Task(User user, String subject, String name, String description, Status status){
        this.user = user;
        this.subject = subject;
        this.name=name;
        this.description=description;
        this.status=status;
    }

    //Getters
    public Integer getId(){return id;}
    public String getSubject(){return subject;}
    public String getName(){return name;}
    public String getDescription(){return description;}
    public Status getStatus(){return status;}
    public User getUser(){return user;}

    //Setters
    public void setId(int id){this.id=id;}
    public void setSubject(String subject){this.subject=subject;}
    public void setName(String name){this.name=name;}
    public void setDescription(String description){this.description= description;}
    public void setStatus(Status status){this.status= status;}
    public void setUser(User user){this.user= user;}
}

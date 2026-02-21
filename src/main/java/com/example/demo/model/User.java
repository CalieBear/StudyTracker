package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String username;
    private String password;
    private String email;

    public User(){}
    public User(String username, String password, String email){
        this.username = username;
        this.password=password;
        this.email=email;
    }

    //gettors
    public String getUsername(){return username;}
    public String getEmail(){return email;}
    public Integer getId(){return id;}

    //TEMPORARY?
    public Boolean verifyPassword(String password){
        return this.password.equals(password);
    }
    public Boolean hasPassword(){return password!=null;}
    
    //Settors
    public void setUsername(String username){this.username=username;}
    public void setEmail(String email){this.email=email;}
    public void setPassword(String password){this.password=password;}
}

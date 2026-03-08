package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class UpdateUserRequest {
    //VALUES ARE OPTIONAL
   @Size(max = 255)
    private String username;
    @Size(min = 8, max = 50)
    private String password;
    @Email
    @Size(max = 255)
    private String email;

    public UpdateUserRequest(){}
    public UpdateUserRequest(String username, String password, String email){
        this.username = username;
        this.password=password;
        this.email=email;
    }

    //gettors
    public String getUsername(){return username;}
    public String getEmail(){return email;}
    public String getPassword(){return password;}
    //Settors
    public void setUsername(String username){this.username=username;}
    public void setEmail(String email){this.email=email;}
    public void setPassword(String password){this.password=password;}

}

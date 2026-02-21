package com.example.demo.dto;
import jakarta.validation.constraints.NotBlank;

public class CreateUserRequest {
    //NEEDS ALL THREE VALUES
    @NotBlank(message = "User must have a username")
    private String username;
    @NotBlank(message = "User must have a password")
    private String password;
    private String email;

    public CreateUserRequest(){}
    public CreateUserRequest(String username, String password, String email){
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

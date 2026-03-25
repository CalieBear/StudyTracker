package com.example.studytracker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Request payload for creating a user account.
 *
 * @author Calista LaPorte
 */
public class CreateUserRequest {

    @NotBlank(message = "User must have a username")
    @Size(max = 255)
    private String username;

    @NotBlank(message = "User must have a password")
    @Size(min = 8, max = 50)
    // Password confirmation can be handled at the API or UI layer.
    private String password;

    @Email
    @Size(max = 255)
    private String email;

    /**
     * Default constructor.
     */
    public CreateUserRequest() {}

    /**
     * Creates a request for a new user.
     *
     * @param username the user's username
     * @param password the user's password
     * @param email the user's email address
     */
    public CreateUserRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // GETTERS
    public String getUsername() {return username;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}

    // SETTERS
    public void setUsername(String username) {this.username = username;}
    public void setEmail(String email) {this.email = email;}
    public void setPassword(String password) {this.password = password;}

}

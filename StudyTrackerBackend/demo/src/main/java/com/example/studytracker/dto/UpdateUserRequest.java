package com.example.studytracker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

/**
 * Request payload for updating a user.
 *
 * <p>All fields are optional and can be provided partially.
 *
 * @author Calista LaPorte
 */
public class UpdateUserRequest {

    @Size(max = 255)
    private String username;

    @Size(min = 8, max = 50)
    private String password;

    @Email
    @Size(max = 255)
    private String email;

    /**
     * Default constructor.
     */
    public UpdateUserRequest() {}

    /**
     * Creates a request for updating user fields.
     *
     * @param username the updated username
     * @param password the updated password
     * @param email the updated email address
     */
    public UpdateUserRequest(String username, String password, String email) {
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

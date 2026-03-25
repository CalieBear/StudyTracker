package com.example.studytracker.dto;

/**
 * Response payload representing user profile data.
 *
 * @author Calista LaPorte
 */
public class UserResponse {

    private String username;
    private String email;

    /**
     * Default constructor.
     */
    public UserResponse() {}

    /**
     * Creates a user response object.
     *
     * @param username the user's username
     * @param email the user's email address
     */
    public UserResponse(String username, String email) {
        this.username = username;
        this.email = email;
    }

    // GETTERS
    public String getUsername() {return username;}
    public String getEmail() {return email;}

    // SETTERS
    public void setUsername(String username) {this.username = username;}
    public void setEmail(String email) {this.email = email;}
}

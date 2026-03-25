package com.example.studytracker.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

/**
 * Represents an application user used for authentication and authorization.
 *
 * <p>This entity is persisted in the {@code app_user} table and implements
 * {@link UserDetails} for Spring Security integration.
 *
 * @author Calista LaPorte
 */
@Entity
@Table(name = "app_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String username;
    private String password;
    private String email;

    // CONSTRUCTORS
    /**
     * Default constructor required by JPA.
     */
    public User() {}

    /**
     * Creates a user with basic credential and contact fields.
     *
     * @param username the login username
     * @param password the user's password value
     * @param email the user's email address
     */
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // GETTERS
    @Override
    public String getUsername() {return username;}
    public String getEmail() {return email;}
    public Integer getId() {return id;}

    @Override
    public String getPassword() {return password;}

    // SETTERS
    public void setUsername(String username) {this.username = username;}
    public void setEmail(String email) {this.email = email;}
    public void setPassword(String password) {this.password = password;}
    public void setId(Integer id) {this.id = id;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    // Account status methods currently return defaults for all users.
    @Override
    public boolean isAccountNonExpired() {return true;}

    @Override
    public boolean isAccountNonLocked() {return true;}

    @Override
    public boolean isCredentialsNonExpired() {return true;}

    @Override
    public boolean isEnabled() {return true;}
}

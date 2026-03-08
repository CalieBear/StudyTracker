package com.example.demo.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "app_user")
public class User implements UserDetails {

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


    //Settors
    public void setUsername(String username){this.username=username;}
    public void setEmail(String email){this.email=email;}
    public void setPassword(String password){this.password=password;}

    //NEW
    public String getPassword(){return password;}
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of();
    }

    //TEMP NOT FULLY IMPLEMENTED
    @Override
    public boolean isAccountNonExpired(){return true;}
    @Override
    public boolean isAccountNonLocked(){return true;}
    @Override
    public boolean isCredentialsNonExpired(){return true;}
    @Override
    public boolean isEnabled(){return true;}
}

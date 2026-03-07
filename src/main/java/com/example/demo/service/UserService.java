package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UserResponse;
import com.example.demo.exception.NotFoundException;
import com.example.demo.dto.CreateUserRequest;
import com.example.demo.dto.UpdateUserRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){this.userRepository=userRepository;}

    public UserResponse createUser(CreateUserRequest create){
        User user = new User(create.getUsername(),create.getPassword(),create.getEmail());
        userRepository.save(user);
        return userToResponse(user);
    }
    
    public UserResponse getUserById(Integer id){
        User user = userRepository.findById(id)
            .orElseThrow(()-> new NotFoundException("User not found with id "+ id));
        return userToResponse(user);
    }

    public void deleteById(Integer id){
        userRepository.deleteById(id);
    }
    
    public UserResponse updateUser(Integer id, UpdateUserRequest update){
        User user = userRepository.findById(id)
            .orElseThrow(()-> new NotFoundException("User not found with id "+id));
        if(update.getUsername()!=null){
            user.setUsername(update.getUsername());
        }if(update.getEmail()!=null){
            user.setEmail(update.getEmail());
        }   //PASSWORD VERIFICATION BEFORE CHANGING IT
        userRepository.save(user);
        return userToResponse(user);
    }
    
    //TEMPORARY
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
 
    public UserResponse userToResponse(User user){
        return new UserResponse(user.getUsername(),user.getEmail());
    }
}

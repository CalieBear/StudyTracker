package com.example.demo.controller;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.UserService;
import com.example.demo.dto.CreateUserRequest;
import com.example.demo.dto.UpdateUserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.model.User;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping //posting -> pushing something to the database
    public UserResponse createUser(@RequestBody CreateUserRequest create){
        return userService.createUser(create);
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Integer id){ //should be get user, and get the id from authenticated user
        return userService.getUserById(id);
    }

    @PatchMapping("/{id}")
    public UserResponse updateUser(@RequestBody UpdateUserRequest update, @PathVariable Integer id){
        return userService.updateUser(id, update);
    }
    @DeleteMapping("/{id}") 
    public void deleteUser(@PathVariable Integer id){
        userService.deleteById(id);
    }

    //TEMPORARY
    @GetMapping //getting -> getting something from the database
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

}

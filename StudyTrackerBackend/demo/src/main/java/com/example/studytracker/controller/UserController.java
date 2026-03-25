package com.example.studytracker.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studytracker.dto.CreateUserRequest;
import com.example.studytracker.dto.UpdateUserRequest;
import com.example.studytracker.dto.UserResponse;
import com.example.studytracker.model.User;
import com.example.studytracker.service.UserService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import jakarta.validation.Valid;

/**
 * REST controller for user account operations.
 *
 * <p>Provides endpoints for creating, retrieving, updating, and deleting users,
 * as well as retrieving the currently authenticated user profile.
 *
 * @author Calista LaPorte
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /**
     * Creates a new user controller.
     *
     * @param userService service used for user business operations
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Creates a new user.
     *
     * @param create validated user creation payload
     * @return created user response
     */
    @PostMapping
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest create) {
        return userService.createUser(create);
    }

    /**
     * Retrieves a user by identifier.
     *
     * @param id user identifier
     * @return user response
     */
    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    /**
     * Updates user fields for the specified identifier.
     *
     * @param id user identifier
     * @param update validated partial update payload
     * @return updated user response
     */
    @PatchMapping("/{id}")
    public UserResponse updateUser(@PathVariable Integer id, @Valid @RequestBody UpdateUserRequest update) {
        return userService.updateUser(id, update);
    }

    /**
     * Deletes a user by identifier.
     *
     * @param id user identifier
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteById(id);
    }

    /**
     * Retrieves the currently authenticated user profile.
     *
    * @param currentUser authenticated user
     * @return current user response
     */
    @GetMapping("/me")
    public UserResponse getCurrentUser(@AuthenticationPrincipal User currentUser) {
        return userService.userToResponse(currentUser);
    }
}

package com.example.studytracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.studytracker.dto.CreateUserRequest;
import com.example.studytracker.dto.UpdateUserRequest;
import com.example.studytracker.dto.UserResponse;
import com.example.studytracker.exception.NotFoundException;
import com.example.studytracker.model.User;
import com.example.studytracker.repository.UserRepository;

/**
 * Service layer for user-related business operations.
 *
 * @author Calista LaPorte
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Creates a new user.
     *
     * @param create validated user creation payload
     * @return created user response
     */
    public UserResponse createUser(CreateUserRequest create) {
        User user = new User(create.getUsername(), passwordEncoder.encode(create.getPassword()), create.getEmail());
        userRepository.save(user);
        return userToResponse(user);
    }

    /**
     * Retrieves a user by identifier.
     *
     * @param id user identifier
     * @return user response
     */
    public UserResponse getUserById(Integer id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("User not found with id " + id));
        return userToResponse(user);
    }

    /**
     * Deletes a user by identifier.
     *
     * @param id user identifier
     */
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    /**
     * Updates mutable user fields.
     *
     * @param id user identifier
     * @param update validated user update payload
     * @return updated user response
     */
    public UserResponse updateUser(Integer id, UpdateUserRequest update) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("User not found with id " + id));
        if (update.getUsername() != null) {
            user.setUsername(update.getUsername());
        }
        if (update.getEmail() != null) {
            user.setEmail(update.getEmail());
        }
        userRepository.save(user);
        return userToResponse(user);
    }

    /**
     * Retrieves all users.
     *
     * @return all user entities
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Maps a user entity to a response DTO.
     *
     * @param user user entity
     * @return mapped response
     */
    public UserResponse userToResponse(User user) {
        return new UserResponse(user.getUsername(), user.getEmail());
    }
}

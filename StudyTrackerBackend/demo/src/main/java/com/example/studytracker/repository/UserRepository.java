package com.example.studytracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studytracker.model.User;

/**
 * Repository for user persistence operations.
 *
 * @author Calista LaPorte
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Finds a user by username.
     *
     * @param username username to search for
     * @return optional user match
     */
    Optional<User> findByUsername(String username);
}

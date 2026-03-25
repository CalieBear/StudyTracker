package com.example.studytracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.studytracker.model.Status;
import com.example.studytracker.model.Task;
import com.example.studytracker.model.User;

/**
 * Repository for task persistence operations.
 *
 * @author Calista LaPorte
 */
public interface TaskRepository extends JpaRepository<Task, Integer> {

    /**
     * Finds a task by identifier and owner.
     *
     * @param id task identifier
     * @param user owning user
     * @return optional task match
     */
    Optional<Task> findByIdAndUser(Integer id, User user);

    /**
     * Retrieves tasks for a user with optional subject and status filters.
     *
     * @param user owning user
     * @param subject optional subject filter
     * @param status optional status filter
     * @return matching tasks
     */
    @Query("""
            SELECT t from Task t
            WHERE t.user = :user 
            AND (:status IS NULL OR t.status = :status)
            AND (:subject IS NULL OR t.subject = :subject)
            """)
    List<Task> getTasks(User user, String subject, Status status);

    /**
     * Deletes all tasks owned by a user.
     *
     * @param user owning user
     */
    void deleteAllByUser(User user);
}

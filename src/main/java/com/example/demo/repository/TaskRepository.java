package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Task;
import com.example.demo.model.User;
import com.example.demo.model.Status;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query("""
            SELECT t from Task t
            WHERE t.user.id =:userId 
            AND (:status IS NULL OR t.status = :status)
            AND (:subject IS NULL OR t.subject = :subject)
            """)
            List<Task> getTasks(Integer userId, String subject,Status status);

    void deleteAllByUser(User user);
}

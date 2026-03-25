package com.example.studytracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studytracker.dto.CreateTaskRequest;
import com.example.studytracker.dto.TaskResponse;
import com.example.studytracker.dto.UpdateTaskRequest;
import com.example.studytracker.exception.NotFoundException;
import com.example.studytracker.model.Status;
import com.example.studytracker.model.Task;
import com.example.studytracker.model.User;
import com.example.studytracker.repository.TaskRepository;

import jakarta.transaction.Transactional;

/**
 * Service layer for task-related business operations.
 *
 * @author Calista LaPorte
 */
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Creates a task for the authenticated user.
     *
     * @param create validated task creation payload
     * @param currentUser authenticated user
     * @return created task response
     */
    public TaskResponse createTask(CreateTaskRequest create, User currentUser) {
        Task task = new Task(currentUser, create.getSubject(), create.getName(), create.getDescription(), create.getStatus());
        task = taskRepository.save(task);
        return taskToResponse(task);
    }

    /**
     * Retrieves tasks for the authenticated user with optional filters.
     *
     * @param subject optional subject filter
     * @param status optional status filter
     * @param currentUser authenticated user
     * @return matching task responses
     */
    public List<TaskResponse> getTasks(String subject, Status status, User currentUser) {
        List<Task> tasks = taskRepository.getTasks(currentUser, subject, status);
        return tasks.stream().map(this::taskToResponse).toList();
    }

    /**
     * Retrieves a task by identifier for the authenticated user.
     *
     * @param id task identifier
     * @param currentUser authenticated user
     * @return task response
     */
    public TaskResponse getTaskById(Integer id, User currentUser) {
        Task task = taskRepository.findByIdAndUser(id, currentUser)
            .orElseThrow(() -> new NotFoundException("Task not found with id " + id));
        return taskToResponse(task);
    }

    /**
     * Deletes a task by identifier for the authenticated user.
     *
     * @param id task identifier
     * @param currentUser authenticated user
     */
    public void deleteById(Integer id, User currentUser) {
        Task task = taskRepository.findByIdAndUser(id, currentUser)
            .orElseThrow(() -> new NotFoundException("Task not found with id " + id));
        taskRepository.delete(task);
    }

    /**
     * Deletes all tasks for the authenticated user.
     *
     * @param currentUser authenticated user
     */
    @Transactional
    public void deleteAllByUser(User currentUser) {
        taskRepository.deleteAllByUser(currentUser);
    }

    /**
     * Updates a task for the authenticated user.
     *
     * @param id task identifier
     * @param update validated task update payload
     * @param currentUser authenticated user
     * @return updated task response
     */
    public TaskResponse updateTask(Integer id, UpdateTaskRequest update, User currentUser) {
        Task task = taskRepository.findByIdAndUser(id, currentUser)
            .orElseThrow(() -> new NotFoundException("Task not found with id " + id));
        if (update.getName() != null) {
            task.setName(update.getName());
        }
        if (update.getDescription() != null) {
            task.setDescription(update.getDescription());
        }
        if (update.getSubject() != null) {
            task.setSubject(update.getSubject());
        }
        if (update.getStatus() != null) {
            task.setStatus(update.getStatus());
        }
        taskRepository.save(task);
        return taskToResponse(task);
    }

    /**
     * Maps a task entity to a response DTO.
     *
     * @param task task entity
     * @return mapped response
     */
    private TaskResponse taskToResponse(Task task) {
        TaskResponse response = new TaskResponse(task.getId(), task.getSubject(), task.getDescription(), task.getName(), task.getStatus());
        return response;
    }
}

package com.example.studytracker.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.studytracker.dto.CreateTaskRequest;
import com.example.studytracker.dto.TaskResponse;
import com.example.studytracker.dto.UpdateTaskRequest;
import com.example.studytracker.model.Status;
import com.example.studytracker.model.User;
import com.example.studytracker.service.TaskService;

import jakarta.validation.Valid;

/**
 * REST controller for task operations scoped to the authenticated user.
 *
 * @author Calista LaPorte
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    /**
     * Creates a new task controller.
     *
     * @param taskService service used for task business operations
     */
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Creates a task for the authenticated user.
     *
     * @param task validated task creation payload
    * @param currentUser authenticated user
     * @return created task response
     */
    @PostMapping
    public TaskResponse createTask(@Valid @RequestBody CreateTaskRequest task, @AuthenticationPrincipal User currentUser) {
        return taskService.createTask(task, currentUser);
    }

    /**
     * Retrieves tasks for the authenticated user with optional filters.
     *
     * @param subject optional subject filter
     * @param status optional status filter
    * @param currentUser authenticated user
     * @return matching task responses
     */
    @GetMapping
    public List<TaskResponse> getTasks(
        @RequestParam(required = false) String subject,
        @RequestParam(required = false) Status status,
        @AuthenticationPrincipal User currentUser
    ) {
        return taskService.getTasks(subject, status, currentUser);
    }

    /**
     * Retrieves a task by identifier for the authenticated user.
     *
     * @param id task identifier
    * @param currentUser authenticated user
     * @return task response
     */
    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable Integer id, @AuthenticationPrincipal User currentUser) {
        return taskService.getTaskById(id, currentUser);
    }

    /**
     * Deletes a task by identifier for the authenticated user.
     *
     * @param id task identifier
    * @param currentUser authenticated user
     */
    @DeleteMapping("/{id}")
    public void deleteTaskById(@PathVariable Integer id, @AuthenticationPrincipal User currentUser) {
        taskService.deleteById(id, currentUser);
    }

    /**
     * Deletes all tasks for the authenticated user.
     *
    * @param currentUser authenticated user
     */
    @DeleteMapping
    public void deleteAllTasksForUser(@AuthenticationPrincipal User currentUser) {
        taskService.deleteAllByUser(currentUser);
    }

    /**
     * Updates a task for the authenticated user.
     *
     * @param id task identifier
     * @param update validated partial task update payload
    * @param currentUser authenticated user
     * @return updated task response
     */
    @PatchMapping("/{id}")
    public TaskResponse updateTask(
        @PathVariable Integer id,
        @Valid @RequestBody UpdateTaskRequest update,
        @AuthenticationPrincipal User currentUser
    ) {
        return taskService.updateTask(id, update, currentUser);
    }
}
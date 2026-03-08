package com.example.demo.controller;
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

import com.example.demo.service.TaskService;

import jakarta.validation.Valid;

import com.example.demo.dto.CreateTaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.dto.UpdateTaskRequest;
import com.example.demo.model.User;
import com.example.demo.model.Status;
@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService=taskService;
    }

    //CREATE
    @PostMapping
    public TaskResponse createTask(@Valid  @RequestBody CreateTaskRequest task, @AuthenticationPrincipal User currentUser){//curenty user
        return taskService.createTask(task,currentUser);
    }

    //GETTERS
    @GetMapping
    public List<TaskResponse> getTasks( 
        @RequestParam(required = false)String subject,
        @RequestParam(required = false)Status status,
        @AuthenticationPrincipal User currentUser
    ){
        return taskService.getTasks(subject,status,currentUser);
    }
    
    @GetMapping("/{id}") 
    public TaskResponse getTaskById(@PathVariable Integer id,@AuthenticationPrincipal User currentUser){
        return taskService.getTaskById(id,currentUser);
    }

    //DELETE
    @DeleteMapping("/{id}") 
    public void deleteTaskById(@PathVariable Integer id,@AuthenticationPrincipal User currentUser){
        taskService.deleteById(id,currentUser);
    }

    @DeleteMapping
    public void deleteAllTasksForUser(@AuthenticationPrincipal User currentUser){
        taskService.deleteAllByUser(currentUser);
    }

    //UPDATE
    @PatchMapping("/{id}")
    public TaskResponse updateTask(
        @PathVariable Integer id,
        @Valid @RequestBody UpdateTaskRequest update,
        @AuthenticationPrincipal User currentUser
    ){
        return taskService.updateTask(id, update,currentUser);
    }
}
package com.example.demo.controller;
import java.util.List;

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
    public TaskResponse createTask(@RequestBody CreateTaskRequest task, User user){//curenty user
        return taskService.createTask(task,user);
    }

    //GETTERS
    @GetMapping
    public List<TaskResponse> getTasks( 
        @RequestParam(required = false)String subject,
        @RequestParam(required = false)Status status
    ){
        return taskService.getTasks(subject,status);
    }
    
    @GetMapping("/{id}") 
    public TaskResponse getTaskById(@PathVariable Integer id){
        return taskService.getTaskById(id);
    }

    //DELETE
    @DeleteMapping("/{id}") 
    public void deleteTaskById(@PathVariable Integer id){
        taskService.deleteById(id);
    }

    @DeleteMapping
    public void deleteAllTasksForUser(){
        taskService.deleteAllByUser(1);
    }

    //UPDATE
    @PatchMapping("/{id}")
    public TaskResponse updateTask(
        @PathVariable Integer id,
        @RequestBody UpdateTaskRequest update
    ){
        return taskService.updateTask(id, update);
    }
}
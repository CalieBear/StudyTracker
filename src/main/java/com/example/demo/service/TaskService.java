package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CreateTaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.dto.UpdateTaskRequest;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Status;
import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

//holds the logic, verifing if input or requests are valid
@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    
    @Autowired 
    public TaskService(TaskRepository taskRepository, UserRepository userRepository){
        this.taskRepository=taskRepository;
        this.userRepository=userRepository;
    }

    //CREATE
    public TaskResponse createTask(CreateTaskRequest create, User currentUser){  //TEMP GETS USERID FROM DTO
        User user = userRepository.findById(create.getUserId())
            .orElseThrow(()-> new NotFoundException("User not found with id "+create.getUserId()));
        Task task = new Task(user, create.getSubject(),create.getName(),create.getDescription(),Status.NOT_STARTED);//current user not made yet
        task = taskRepository.save(task);
        return taskToResponse(task);
    }

    //GET TASKS
    public List<TaskResponse> getTasks(String subject, Status status){
        List<Task> tasks=taskRepository.getTasks(1, subject, status); //REPO DEALS WITH NULL
        return tasks.stream().map(this::taskToResponse).toList();
    }
    public TaskResponse getTaskById(Integer id){
        Task task = taskRepository.findById(id)
            .orElseThrow(()-> new NotFoundException("Task not found with id "+ id));
        return taskToResponse(task);
    }
    
    //DELETE TASKS 
    public void deleteById(Integer id){ 
        Task task = taskRepository.findById(id)
            .orElseThrow(()-> new NotFoundException("Task not found with id "+ id));
        //USER AUTHENTICATION HERE
        taskRepository.delete(task);
    }
    @Transactional
    public void deleteAllByUser(Integer userId){
        User user = userRepository.findById(userId)
            .orElseThrow(()-> new NotFoundException("User not found with id" + userId));
        taskRepository.deleteAllByUser(user);
    }

    public TaskResponse updateTask(Integer id, UpdateTaskRequest update){
        //find verify userId param is the same as task's userId
        Task task = taskRepository.findById(id)
            .orElseThrow(()-> new NotFoundException("Task not found with id "+ id));
        if(update.getName()!=null){
            task.setName(update.getName());
        }if(update.getDescription()!=null){
            task.setDescription(update.getDescription());
        }if(update.getSubject()!=null){
            task.setSubject(update.getSubject());
        }if(update.getStatus()!=null){//switch to enum
            task.setStatus(update.getStatus());
        }
        taskRepository.save(task);
        return taskToResponse(task);
    }

    private TaskResponse taskToResponse(Task task){
        TaskResponse response = new TaskResponse(task.getId(),task.getSubject(),task.getDescription(),task.getName(),task.getStatus());
        return response;
    }
}

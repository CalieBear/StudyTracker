package com.example.demo.controller;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.CreateSessionRequest;
import com.example.demo.dto.SessionResponse;
import com.example.demo.dto.UpdateSessionRequest;
import com.example.demo.service.StudySessionService;

@RestController
@RequestMapping("/session")
public class SessionController{
    private final StudySessionService sessionService;

    public SessionController(StudySessionService sessionService){
        this.sessionService=sessionService;
    }

    @PostMapping
    public SessionResponse createSession(@RequestBody CreateSessionRequest create){
        return sessionService.createSession(create);
    }

    @GetMapping("/{id}")
    public SessionResponse getSessionById(@PathVariable Integer id){
        return sessionService.getSessionById(id);
    }

    //GET SESSIONS
    @GetMapping
    public List<SessionResponse> getSessions(
        @RequestParam(required = false)String subject
    ){
        return sessionService.getSessions(1, subject);//PLACEHOLDER
    }

    @PatchMapping("/{id}")
    public SessionResponse updateSession(@RequestBody UpdateSessionRequest update,@PathVariable Integer id){
        return sessionService.updateSession(id,update);
    }

    @DeleteMapping("/{id}") 
    public void deleteSession(@PathVariable Integer id){
        sessionService.deleteById(id);
    }
    @DeleteMapping() 
    public void deleteByUser(){
        sessionService.deleteAllUserSessions(1);//PLACEHOLDER !!!
    }

    
}
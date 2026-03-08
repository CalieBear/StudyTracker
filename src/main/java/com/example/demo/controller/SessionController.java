package com.example.demo.controller;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.CreateSessionRequest;
import com.example.demo.dto.SessionResponse;
import com.example.demo.dto.UpdateSessionRequest;
import com.example.demo.model.User;
import com.example.demo.service.StudySessionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/session")
public class SessionController{
    private final StudySessionService sessionService;

    public SessionController(StudySessionService sessionService){
        this.sessionService=sessionService;
    }

    @PostMapping
    public SessionResponse createSession(@Valid @RequestBody CreateSessionRequest create,@AuthenticationPrincipal User currentUser){
        return sessionService.createSession(create,currentUser);
    }

    @GetMapping("/{id}")
    public SessionResponse getSessionById(@PathVariable Integer id, @AuthenticationPrincipal User currentUser){
        return sessionService.getSessionById(id,currentUser);
    }

    //GET SESSIONS
    @GetMapping
    public List<SessionResponse> getSessions(
        @RequestParam(required = false)String subject,
        @AuthenticationPrincipal User currentUser
    ){
        return sessionService.getSessions(subject,currentUser);
    }

    @PatchMapping("/{id}")
    public SessionResponse updateSession(@Valid @RequestBody UpdateSessionRequest update,@PathVariable Integer id,@AuthenticationPrincipal User currentUser){
        return sessionService.updateSession(id,update,currentUser);
    }

    @DeleteMapping("/{id}") 
    public void deleteSession(@PathVariable Integer id,@AuthenticationPrincipal User currentUser){
        sessionService.deleteById(id,currentUser);
    }
    @DeleteMapping() 
    public void deleteByUser(@AuthenticationPrincipal User currentUser){
        sessionService.deleteAllUserSessions(currentUser);//PLACEHOLDER !!!
    }

    
}
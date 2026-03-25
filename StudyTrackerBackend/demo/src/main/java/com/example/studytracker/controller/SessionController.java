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

import com.example.studytracker.dto.CreateSessionRequest;
import com.example.studytracker.dto.SessionResponse;
import com.example.studytracker.dto.UpdateSessionRequest;
import com.example.studytracker.model.User;
import com.example.studytracker.service.StudySessionService;

import jakarta.validation.Valid;

/**
 * REST controller for study-session operations scoped to the authenticated user.
 *
 * @author Calista LaPorte
 */
@RestController
@RequestMapping("/session")
public class SessionController {

    private final StudySessionService sessionService;

    /**
     * Creates a new session controller.
     *
     * @param sessionService service used for study-session business operations
     */
    public SessionController(StudySessionService sessionService) {
        this.sessionService = sessionService;
    }

    /**
     * Creates a study session for the authenticated user.
     *
     * @param create validated session creation payload
    * @param currentUser authenticated user
     * @return created session response
     */
    @PostMapping
    public SessionResponse createSession(@Valid @RequestBody CreateSessionRequest create, @AuthenticationPrincipal User currentUser) {
        return sessionService.createSession(create, currentUser);
    }

    /**
     * Retrieves a study session by identifier.
     *
     * @param id session identifier
    * @param currentUser authenticated user
     * @return session response
     */
    @GetMapping("/{id}")
    public SessionResponse getSessionById(@PathVariable Integer id, @AuthenticationPrincipal User currentUser) {
        return sessionService.getSessionById(id, currentUser);
    }

    /**
     * Retrieves sessions for the authenticated user with optional subject filter.
     *
     * @param subject optional subject filter
    * @param currentUser authenticated user
     * @return matching session responses
     */
    @GetMapping
    public List<SessionResponse> getSessions(
        @RequestParam(required = false) String subject,
        @AuthenticationPrincipal User currentUser
    ) {
        return sessionService.getSessions(subject, currentUser);
    }

    /**
     * Updates a study session for the authenticated user.
     *
     * @param id session identifier
     * @param update validated partial session update payload
    * @param currentUser authenticated user
     * @return updated session response
     */
    @PatchMapping("/{id}")
    public SessionResponse updateSession(@PathVariable Integer id, @Valid @RequestBody UpdateSessionRequest update, @AuthenticationPrincipal User currentUser) {
        return sessionService.updateSession(id, update, currentUser);
    }

    /**
     * Deletes a study session by identifier for the authenticated user.
     *
     * @param id session identifier
    * @param currentUser authenticated user
     */
    @DeleteMapping("/{id}")
    public void deleteSession(@PathVariable Integer id, @AuthenticationPrincipal User currentUser) {
        sessionService.deleteById(id, currentUser);
    }

    /**
     * Deletes all study sessions for the authenticated user.
     *
    * @param currentUser authenticated user
     */
    @DeleteMapping
    public void deleteByUser(@AuthenticationPrincipal User currentUser) {
        sessionService.deleteAllUserSessions(currentUser);
    }
}
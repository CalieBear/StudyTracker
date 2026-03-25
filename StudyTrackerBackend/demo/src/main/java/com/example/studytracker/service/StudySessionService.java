package com.example.studytracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studytracker.dto.CreateSessionRequest;
import com.example.studytracker.dto.SessionResponse;
import com.example.studytracker.dto.UpdateSessionRequest;
import com.example.studytracker.exception.NotFoundException;
import com.example.studytracker.model.StudySession;
import com.example.studytracker.model.User;
import com.example.studytracker.repository.StudySessionRepository;

import jakarta.transaction.Transactional;

/**
 * Service layer for study-session business operations.
 *
 * @author Calista LaPorte
 */
@Service
public class StudySessionService {

    private final StudySessionRepository studySessionRepository;

    @Autowired
    public StudySessionService(StudySessionRepository studySessionRepository) {
        this.studySessionRepository = studySessionRepository;
    }

    /**
     * Creates a study session for the authenticated user.
     *
     * @param create validated session creation payload
     * @param currentUser authenticated user
     * @return created session response
     */
    public SessionResponse createSession(CreateSessionRequest create, User currentUser) {
        StudySession session = new StudySession(create.getSubject(), create.getDescription(), create.getStartTime(), create.getEndTime());
        session.setUser(currentUser);
        validateSession(session);
        studySessionRepository.save(session);
        return sessionToResponse(session);
    }

    /**
     * Retrieves study sessions for the authenticated user with optional subject filter.
     *
     * @param subject optional subject filter
     * @param currentUser authenticated user
     * @return matching session responses
     */
    public List<SessionResponse> getSessions(String subject, User currentUser) {
        List<StudySession> sessions = studySessionRepository.getSessions(currentUser, subject);
        return sessions.stream().map(this::sessionToResponse).toList();
    }

    /**
     * Retrieves a study session by identifier for the authenticated user.
     *
     * @param id session identifier
     * @param currenUser authenticated user
     * @return session response
     */
    public SessionResponse getSessionById(Integer id, User currenUser) {
        StudySession session = studySessionRepository.findByIdAndUser(id, currenUser)
            .orElseThrow(() -> new NotFoundException("Session not found with id " + id));
        return sessionToResponse(session);
    }

    /**
     * Deletes a study session by identifier for the authenticated user.
     *
     * @param id session identifier
     * @param currentUser authenticated user
     */
    public void deleteById(Integer id, User currentUser) {
        StudySession session = studySessionRepository.findByIdAndUser(id, currentUser)
            .orElseThrow(() -> new NotFoundException("Session not found with id " + id));
        studySessionRepository.delete(session);
    }

    /**
     * Deletes all study sessions for the authenticated user.
     *
     * @param currentUser authenticated user
     */
    @Transactional
    public void deleteAllUserSessions(User currentUser) {
        studySessionRepository.deleteAllByUser(currentUser);
    }

    /**
     * Updates a study session for the authenticated user.
     *
     * @param id session identifier
     * @param update validated session update payload
     * @param currentUser authenticated user
     * @return updated session response
     */
    public SessionResponse updateSession(Integer id, UpdateSessionRequest update, User currentUser) {
        StudySession session = studySessionRepository.findByIdAndUser(id, currentUser)
            .orElseThrow(() -> new NotFoundException("Study Session not found with id " + id));
        if (update.getSubject() != null) {
            session.setSubject(update.getSubject());
        }
        if (update.getDescription() != null) {
            session.setDescription(update.getDescription());
        }
        if (update.getStartTime() != null) {
            session.setStartTime(update.getStartTime());
        }
        if (update.getEndTime() != null) {
            session.setEndTime(update.getEndTime());
        }
        studySessionRepository.save(session);
        return sessionToResponse(session);
    }

    /**
     * Validates session constraints before persistence.
     *
     * @param session session entity to validate
     */
    private void validateSession(StudySession session) {
        if (session.getId() != null) {
            throw new IllegalArgumentException("Session cant already have an id");
        }
        if (session.getEndTime() == null) {
            studySessionRepository.existsActiveSession(session.getUser());
        } else {
            studySessionRepository.existsOverlappingSession(session.getUser(), session.getStartTime(), session.getEndTime());
        }
    }

    /**
     * Maps a session entity to a response DTO.
     *
     * @param session session entity
     * @return mapped response
     */
    private SessionResponse sessionToResponse(StudySession session) {
        return new SessionResponse(session.getSubject(), session.getDescription(), session.getStartTime(), session.getEndTime());
    }
}

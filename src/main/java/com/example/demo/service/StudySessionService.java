package com.example.demo.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.StudySessionRepository;
import com.example.demo.model.User;
import com.example.demo.dto.CreateSessionRequest;
import com.example.demo.dto.SessionResponse;
import com.example.demo.dto.UpdateSessionRequest;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.StudySession;

import jakarta.transaction.Transactional;
@Service
public class StudySessionService {
    
    private final StudySessionRepository studySessionRepository;

    @Autowired
    public StudySessionService(StudySessionRepository studySessionRepository){
        this.studySessionRepository=studySessionRepository;
    }

    //CREATE //NEED TO IMPLEMENT USERID
    public SessionResponse createSession(CreateSessionRequest create,User currentUser){
        StudySession session = new StudySession(create.getSubject(),create.getDescription(),create.getStartTime(),create.getEndTime());
        session.setUser(currentUser);
        validateSession(session);
        studySessionRepository.save(session);
        return sessionToResponse(session);
    }

    //GETTERS
    public List<SessionResponse> getSessions(String subject,User currentUser){
        List<StudySession> sessions= studySessionRepository.getSessions(currentUser, subject);
        return sessions.stream().map(this::sessionToResponse).toList();
    }

    public SessionResponse getSessionById(Integer id, User currenUser){
        StudySession session= studySessionRepository.findByIdAndUser(id,currenUser)
         .orElseThrow(()-> new NotFoundException("Session not found with id "+ id));
        return sessionToResponse(session);
    }
    
    //DELETE
    public void deleteById(Integer id, User currentUser){
        StudySession session = studySessionRepository.findByIdAndUser(id,currentUser)
            .orElseThrow(()-> new NotFoundException("Session not found with id "+ id));
        studySessionRepository.delete(session);
    }
    
    @Transactional
    public void deleteAllUserSessions(User currentUser){
        studySessionRepository.deleteAllByUser(currentUser);
    }

    //UPDATE
    public SessionResponse updateSession(Integer id,UpdateSessionRequest update, User currentUser){
        StudySession session = studySessionRepository.findByIdAndUser(id,currentUser)
            .orElseThrow(()-> new NotFoundException("Study Session not found with id "+ id));
        if(update.getSubject()!=null){
            session.setSubject(update.getSubject());
        }if(update.getDescription()!=null){
            session.setDescription(update.getDescription());
        }if(update.getStartTime()!=null){
            session.setStartTime(update.getStartTime());
        }if(update.getEndTime()!=null){
            session.setEndTime(update.getEndTime());
        }
        //VALIDATE TIME
        studySessionRepository.save(session);
        return sessionToResponse(session);
    }

    //helper methods
    private void validateSession(StudySession session){
        if(session.getId()!=null){
            throw new IllegalArgumentException("Session cant already have an id");
        }
        if(session.getEndTime()==null){
            studySessionRepository.existsActiveSession(session.getUser());
        }else{
            studySessionRepository.existsOverlappingSession(session.getUser(),session.getStartTime(),session.getEndTime());
        }
    }

    private SessionResponse sessionToResponse(StudySession session){
        return new SessionResponse(session.getSubject(),session.getDescription(),session.getStartTime(),session.getEndTime());
    }
}

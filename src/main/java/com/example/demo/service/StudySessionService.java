package com.example.demo.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.StudySessionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.model.User;
import com.example.demo.dto.CreateSessionRequest;
import com.example.demo.dto.SessionResponse;
import com.example.demo.dto.UpdateSessionRequest;
import com.example.demo.model.StudySession;

import jakarta.transaction.Transactional;
@Service
public class StudySessionService {
    
    private final StudySessionRepository studySessionRepository;
    private final UserRepository userRepository;

    @Autowired
    public StudySessionService(StudySessionRepository studySessionRepository, UserRepository userRepository){
        this.studySessionRepository=studySessionRepository;
        this.userRepository=userRepository;
    }

    //CREATE //NEED TO IMPLEMENT USERID
    public SessionResponse createSession(CreateSessionRequest create){
        User user = userRepository.findById(create.getUserId())
            .orElseThrow(()-> new IllegalArgumentException("User not found with id "+create.getUserId()));
        StudySession session = new StudySession(create.getSubject(),create.getDescription(),create.getStartTime(),create.getEndTime());
        session.setUser(user);
        validateSession(session);
        studySessionRepository.save(session);
        return sessionToResponse(session);
    }

    //GETTERS
    public List<SessionResponse> getSessions(Integer userId, String subject){
        List<StudySession> sessions= studySessionRepository.getTasks(userId, subject);
        return sessions.stream().map(this::sessionToResponse).toList();
    }
    public SessionResponse getSessionById(Integer id){
        StudySession session= studySessionRepository.findById(id)
         .orElseThrow(()-> new IllegalArgumentException("Session not found with id "+ id));
        return sessionToResponse(session);
    }
    
    //DELETE
    public void deleteById(Integer id){
        studySessionRepository.deleteById(id);
    }
    @Transactional
    public void deleteAllUserSessions(Integer userId){
        User user = userRepository.findById(userId)
            .orElseThrow(()-> new IllegalArgumentException("User not found with id" + userId));
        studySessionRepository.deleteAllByUser(user);
    }

    //UPDATE
    public SessionResponse updateSession(Integer id,UpdateSessionRequest update){
        //find verify userId param is the same as task's userId
        //code here
        StudySession session = studySessionRepository.findById(id)
            .orElseThrow(()-> new IllegalArgumentException("Study Session not found with id "));
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
            studySessionRepository.existsActiveSession(session.getUser().getId());
        }else{
            studySessionRepository.existsOverlappingSession(session.getUser().getId(),session.getStartTime(),session.getEndTime());
        }
    }

    private SessionResponse sessionToResponse(StudySession session){
        return new SessionResponse(session.getSubject(),session.getDescription(),session.getStartTime(),session.getEndTime());
    }
}

package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.User;
import com.example.demo.model.StudySession;

public interface StudySessionRepository extends JpaRepository<StudySession, Integer> {
    //FIND
    Optional<StudySession> findByIdAndUser(Integer id, User user);
    List<StudySession> findByUser(User user);
    List<StudySession> findByUserAndSubject(User user, String subject); 
   
    //DELETE
    void deleteAllByUser(User user); 

    //EXISTS
        //DOESNT WORK IF NEWEND IS NULL
    @Query("""
            SELECT COUNT(s) >0
            FROM StudySession s
            WHERE s.user = :user
            AND (
                    s.endTime IS NULL
                OR
                    (s.startTime <:newEnd AND s.endTime > :newStart )
            )
            """)
    boolean existsOverlappingSession(
        @Param("user") User user,
        @Param("newStart") LocalDateTime newStart,
        @Param("newEnd") LocalDateTime newEnd
    );

    @Query("""
            SELECT COUNT(s) >0
            From StudySession s
            WHERE s.user =:user
            AND s.endTime IS NULL
            """)
    boolean existsActiveSession(
        @Param("user") User user
    );

     @Query("""
            SELECT s from StudySession s
            WHERE s.user = :user 
            AND (:subject IS NULL OR s.subject = :subject)
            """)
            List<StudySession> getSessions(User user, String subject);

    //  EVENTUALLY WANNA SORT BY DATE
}

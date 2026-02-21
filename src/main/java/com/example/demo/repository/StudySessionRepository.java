package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.User;
import com.example.demo.model.StudySession;

public interface StudySessionRepository extends JpaRepository<StudySession, Integer> {
    //FIND
    List<StudySession> findByUser(User user);
    List<StudySession> findByUserAndSubject(User user, String subject); //not tested
   
    //DELETE
    void deleteAllByUser(User user); //not tested

    //EXISTS
        //DOESNT WORK IF NEWEND IS NULL
    @Query("""
            SELECT COUNT(s) >0
            FROM StudySession s
            WHERE s.user.id =:userId
            AND (
                    s.endTime IS NULL
                OR
                    (s.startTime <:newEnd AND s.endTime > :newStart )
            )
            """)
    boolean existsOverlappingSession(
        @Param("userId") Integer userId,
        @Param("newStart") LocalDateTime newStart,
        @Param("newEnd") LocalDateTime newEnd
    );

    @Query("""
            SELECT COUNT(s) >0
            From StudySession s
            WHERE s.user.id =:userId
            AND s.endTime IS NULL
            """)
    boolean existsActiveSession(
        @Param("userId") Integer userid
    );

     @Query("""
            SELECT s from StudySession s
            WHERE s.user.id =:userId 
            AND (:subject IS NULL OR s.subject = :subject)
            """)
            List<StudySession> getTasks(Integer userId, String subject);

    //  EVENTUALLY WANNA SORT BY DATE
}

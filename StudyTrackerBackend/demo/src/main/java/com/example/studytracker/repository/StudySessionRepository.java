package com.example.studytracker.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.studytracker.model.StudySession;
import com.example.studytracker.model.User;

/**
 * Repository for study-session persistence operations.
 *
 * @author Calista LaPorte
 */
public interface StudySessionRepository extends JpaRepository<StudySession, Integer> {

    /**
     * Finds a study session by identifier and owner.
     *
     * @param id session identifier
     * @param user owning user
     * @return optional session match
     */
    Optional<StudySession> findByIdAndUser(Integer id, User user);

    /**
     * Finds all sessions for a user.
     *
     * @param user owning user
     * @return user sessions
     */
    List<StudySession> findByUser(User user);

    /**
     * Finds sessions for a user filtered by subject.
     *
     * @param user owning user
     * @param subject subject filter
     * @return matching sessions
     */
    List<StudySession> findByUserAndSubject(User user, String subject);

    /**
     * Deletes all sessions for a user.
     *
     * @param user owning user
     */
    void deleteAllByUser(User user);

    /**
     * Checks whether the user has a session overlapping a proposed time range.
     *
     * @param user owning user
     * @param newStart proposed session start time
     * @param newEnd proposed session end time
     * @return true if an overlap exists
     */
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

    /**
     * Checks whether the user currently has an active session.
     *
     * @param user owning user
     * @return true if an active session exists
     */
    @Query("""
            SELECT COUNT(s) >0
            From StudySession s
            WHERE s.user =:user
            AND s.endTime IS NULL
            """)
    boolean existsActiveSession(
        @Param("user") User user
    );

    /**
     * Retrieves sessions for a user with an optional subject filter.
     *
     * @param user owning user
     * @param subject optional subject filter
     * @return matching sessions
     */
    @Query("""
            SELECT s from StudySession s
            WHERE s.user = :user 
            AND (:subject IS NULL OR s.subject = :subject)
            """)
    List<StudySession> getSessions(User user, String subject);
}

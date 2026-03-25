package com.example.studytracker.model;

/**
 * Represents task progress states used by the {@link Task} entity.
 *
 * @author Calista LaPorte
 */
public enum Status {
    /** Task has not been started. */
    NOT_STARTED,

    /** Task is currently in progress. */
    IN_PROGRESS,

    /** Task has been completed. */
    COMPLETED
}

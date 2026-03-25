package com.example.studytracker.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Global REST exception handler that maps exceptions to structured responses.
 *
 * @author Calista LaPorte
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles resource-not-found exceptions.
     *
     * @param ex thrown not-found exception
     * @param request incoming HTTP request
     * @return standardized 404 response
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException ex, HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse(404, "Not Found", ex.getMessage(), LocalDateTime.now(), request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles bean-validation failures and returns field-level validation output.
     *
     * @param ex validation exception
     * @param request incoming HTTP request
     * @return standardized 400 response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String msg = ex.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.joining(", "));
        ErrorResponse response = new ErrorResponse(400, "Invalid Argument", msg, LocalDateTime.now(), request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles uncaught exceptions.
     *
     * @param e unexpected exception
     * @param request incoming HTTP request
     * @return standardized 500 response
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleError(Exception e, HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse(500, "Unexpected Error", "An unexpected error occurred", LocalDateTime.now(), request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

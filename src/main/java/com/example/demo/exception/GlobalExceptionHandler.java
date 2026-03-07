package com.example.demo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.stream.Collectors;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException ex, HttpServletRequest request){
        ErrorResponse response = new ErrorResponse(404,"Not Found",ex.getMessage(),LocalDateTime.now(),request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleNotValid(MethodArgumentNotValidException ex, HttpServletRequest request){
        String msg = ex.getBindingResult().getFieldErrors().stream().map(error -> error.getField()+ ": " + error.getDefaultMessage()).collect(Collectors.joining(", ")); //this is not enough
        ErrorResponse response = new ErrorResponse(400, "Invalid Argument",msg,LocalDateTime.now(),request.getRequestURI());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleError(Exception e, HttpServletRequest request){
        ErrorResponse response = new ErrorResponse(500,"Unexpected Error","An unexpected error occurred",LocalDateTime.now(),request.getRequestURI());
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

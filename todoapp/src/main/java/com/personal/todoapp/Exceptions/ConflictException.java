package com.personal.todoapp.Exceptions;

public class ConflictException extends RuntimeException {
    
    public ConflictException(String message) {
        super(message);
    }
}

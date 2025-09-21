package com.personal.todoapp.Exceptions;

public class ErrorResponse {
    public final int statusCode;
    public final String message;

    public ErrorResponse(int statusCode,String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}

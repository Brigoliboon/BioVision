package com.example.biovision.API.Request.exception;

public class RequestErrorException extends RuntimeException {
    public RequestErrorException(String message) {
        super(message);
    }
}

package com.example.biovision.data.API.Connection.exception;

public class NetworkErrorException extends RuntimeException {
    private Throwable type;
    public NetworkErrorException(Throwable type, String message) {
        super(message);
        this.type = type;
    }

    public Throwable getType(){
        return type;
    }

    public Class getErrorType(){
        return type.getClass();
    }
}

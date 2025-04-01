package com.example.biovision.data.API.Connection;

public enum ConnectionState {
    Success(200, "OK"),
    Unauthorized(401, "Unauthorized Access"),
    Timeout(408, "Request Timeout");

    private int code;
    private String message;
    ConnectionState(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}

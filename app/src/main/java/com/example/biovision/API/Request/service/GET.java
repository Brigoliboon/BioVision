package com.example.biovision.API.Request.service;

import java.util.concurrent.TimeoutException;

import okhttp3.Response;

public interface GET {

    public Response GET() throws TimeoutException;
}

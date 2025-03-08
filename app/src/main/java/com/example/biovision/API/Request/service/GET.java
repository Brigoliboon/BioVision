package com.example.biovision.API.Request.service;

import com.example.biovision.API.Connection.exception.RuntimeTimeoutException;
import com.example.biovision.API.Connection.exception.UnauthorizedException;

import java.util.HashMap;

import okhttp3.Response;

public interface GET {

    // requests the default url and api key in an instantiated Request
    public Response GET() throws RuntimeTimeoutException, UnauthorizedException;

    /* TODO: TO BE IMPLEMENTED ON FUTURE VERSIONS

    // Requests the provided url with api key
    public static Response GET(String url, String api_key) throws RuntimeTimeoutException, UnauthorizedException {
        return null;
    }

    // Requests the provided url with api key and with queries
    public static Response GET(String url, String api_key, HashMap<String, String> params) throws RuntimeTimeoutException, UnauthorizedException {
        return null;
    }
    */

    // requests the default url and api key in an instantiated Request but with Queries
    public Response GET(HashMap<String, String> params)throws UnauthorizedException, RuntimeTimeoutException;

}

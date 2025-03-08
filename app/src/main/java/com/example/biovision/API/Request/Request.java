package com.example.biovision.API.Request;

import com.example.biovision.API.Connection.ConnectionState;
import com.example.biovision.API.Connection.exception.RuntimeTimeoutException;
import com.example.biovision.API.Connection.exception.UnauthorizedException;
import com.example.biovision.API.Request.service.GET;
import com.example.biovision.API.Request.service.POST;
import com.example.biovision.API.Request.util.JSONParser;

import org.json.JSONObject;

import java.io.IOException;
import java.sql.Time;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Request implements GET, POST{
    private final String api_key;
    private String url;

    private final RequestBuilder requestBuilder;

    private final OkHttpClient CLIENT = new OkHttpClient();

    public Request(String api_key, String url){
        this.api_key = api_key;
        this.url = url;
        this.requestBuilder = new RequestBuilder(api_key, url, RequestType.GET);
    }

    // Helper method
    private Response processResponse(Response response) throws UnauthorizedException, RuntimeTimeoutException {
        if (response.isSuccessful()) {
            return response;

        } else if (response.code() == ConnectionState.Unauthorized.getCode()) {
            throw new UnauthorizedException("User unauthorized access");

        } else if (response.code() == ConnectionState.Timeout.getCode()) {
            throw new RuntimeTimeoutException("Runtime timeout response from the server");
        }

        // Returns null if the response was not successful and has not been caught by exceptions
        return null;
    }

    /*
    * THIS SECTION BEGINS THE GET METHOD OVERLOADS
    * */
    public Response GET(HashMap<String, String> params)throws UnauthorizedException, RuntimeTimeoutException{
        okhttp3.Request request = requestBuilder.BuildGET(params);

        try {
            Response response = CLIENT.newCall(request).execute();

            // processes and handles the return events from the response calls
            processResponse(response);
        } catch (IOException e) {
            e.printStackTrace();

        }

        return null;
    }

    public Response GET() throws UnauthorizedException, RuntimeTimeoutException {
            okhttp3.Request request = requestBuilder.BuildGET();

        try {
            Response response = CLIENT.newCall(request).execute();

            // processes and handles the return events from the response calls
            processResponse(response);
        }

        catch (IOException e){
            e.printStackTrace();
            System.err.println(e);
        }
        return  null;

    }

    public boolean isConnected() throws UnauthorizedException, RuntimeTimeoutException {
        okhttp3.Request request = requestBuilder.BuildGET();

        try {
            Response response = CLIENT.newCall(request).execute();

            // Checks if the processResponse has an uncaught error and returned null
            if (processResponse(response) != null) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Always returns to false when the connection is not successful - Not received a 200 status
        return false;
    }

    /*
     * THIS SECTION BEGINS THE POST METHOD OVERLOADS
     * */
    public Response POST(JSONObject payload){
        //TODO: Implement POST method

        String json = payload.toString();
        RequestBody requestBody = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));

        okhttp3.Request request = requestBuilder.BuildPOST(requestBody);

        try {
            Response response = CLIENT.newCall(request).execute();
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


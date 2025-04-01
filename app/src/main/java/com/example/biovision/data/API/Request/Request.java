package com.example.biovision.data.API.Request;

import com.example.biovision.data.API.Connection.ConnectionState;
import com.example.biovision.data.API.Connection.exception.NetworkErrorException;
import com.example.biovision.data.API.Connection.exception.UnauthorizedException;
import com.example.biovision.data.API.Request.service.GET;
import com.example.biovision.data.API.Request.service.POST;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import javax.annotation.Nullable;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

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

    @Nullable
    // Helper methods
    private Response processResponse(Response response) throws UnauthorizedException, NetworkErrorException{
        if (response.isSuccessful()) {
            return response;

        } else if (response.code() == ConnectionState.Unauthorized.getCode()) {
            throw new UnauthorizedException("User unauthorized access");
        }

        // Returns null if the response was not successful and has not been caught by exceptions
        return null;
    }

    private Response establishConnection(okhttp3.Request request) throws NetworkErrorException {

        try {
            Response response = CLIENT.newCall(request).execute();

            return response;
        } catch (IOException e) {
            throw new NetworkErrorException(e, e.getMessage());
        }

    }

    public boolean isConnected() throws UnauthorizedException, NetworkErrorException {
        okhttp3.Request request = requestBuilder.BuildGET();

        Response response = establishConnection(request);

        // Checks if the processResponse has an uncaught error and returned null
        if (processResponse(response) != null) {
            return true;
        }

        // Always returns to false when the connection is not successful - Not received a 200 status
        return false;
    }

    /*
    * THIS SECTION BEGINS THE GET METHOD OVERLOADS
    * */
    public Response GET() throws UnauthorizedException, NetworkErrorException {
        okhttp3.Request request = requestBuilder.BuildGET();

        Response response = establishConnection(request);

        // processes and handles the return events from the response calls
        return processResponse(response);

    }

    public Response GET(HashMap<String, String> params)throws UnauthorizedException, NetworkErrorException{
        okhttp3.Request request = requestBuilder.BuildGET(params);

        Response response = establishConnection(request);

        // processes and handles the return events from the response calls
        return processResponse(response);
    }

    /*
     * THIS SECTION BEGINS THE POST METHOD OVERLOADS
     * */
    public Response POST(JSONObject payload) throws RuntimeException{
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


package com.example.biovision.API.Request;

import com.example.biovision.API.Request.service.GET;
import com.example.biovision.API.Request.service.POST;
import com.example.biovision.API.Request.util.JSONParser;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Request implements GET, POST{
    private final String api_key;
    private String url;

    private final RequestBuilder requestBuilder;

    private final OkHttpClient CLIENT = new OkHttpClient();
    private final JSONParser PARSE = new JSONParser();

    public Request(String api_key, String url){
        this.api_key = api_key;
        this.url = url;
        this.requestBuilder = new RequestBuilder(api_key, url, RequestType.GET);
    }

    public Response GET(HashMap<String, String> params) throws IOException {
        okhttp3.Request request = requestBuilder.BuildGET(params);

        try {
            Response response = CLIENT.newCall(request).execute();
            if (response.isSuccessful()) {
                return response;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response GET() {
            okhttp3.Request request = requestBuilder.BuildGET();

        try {
            Response response = CLIENT.newCall(request).execute();
            if (response.isSuccessful()) {
                return response;
            }
        }

        catch (IOException e){
            e.printStackTrace();
            System.err.println(e);
        }
        return  null;
    }

    public boolean isConnected() {
        okhttp3.Request request = requestBuilder.BuildGET();

        try(Response response = CLIENT.newCall(request).execute()) {
                return response.code() == 200;
        } catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

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


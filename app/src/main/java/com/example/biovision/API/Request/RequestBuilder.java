package com.example.biovision.API.Request;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class RequestBuilder {
    private String api_key;
    private String url;
    private String request_body;

    private final OkHttpClient client = new OkHttpClient();

    public RequestBuilder(String api_key, String url, RequestType type){
        this.api_key = api_key;
        this.url = url;
    }
    public Request BuildPOST(RequestBody requestBody){
        Request request = new Request.Builder()
                .url(url)
                .header("api-key", api_key)
                .post(requestBody)
                .build();
        return request;
    }

    public Request BuildGET(){
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        return request;
    }

    public Request BuildGET(){
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        return request;
    }

}

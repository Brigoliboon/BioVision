package com.example.biovision.API.Request;

import com.example.biovision.API.Request.util.QueryBuilder;

import java.util.HashMap;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RequestBuilder {
    private String api_key;
    private String url;

    public RequestBuilder(String api_key, String url, RequestType type){
        this.api_key = api_key;
        this.url = url;
    }
    public Request BuildPOST(RequestBody requestBody){
        Request request = new Request.Builder()
                .url(url)
                .header("x-api-key", api_key)
                .post(requestBody)
                .build();
        return request;
    }

    public Request BuildGET(){
        Request request = new Request.Builder()
                .url(url)
                .header("x-api-key", api_key)
                .build();

        return request;
    }

    public Request BuildGET(HttpUrl url){
        Request request = new Request.Builder()
                .url(url)
                .header("x-api-key", api_key)
                .build();

        return request;
    }

    public Request BuildGET(HashMap<String, String> queries){
        // Instantiates QueryBuilder class
        QueryBuilder querybuilder = new QueryBuilder(queries);

        // Generates a url with the queries attached
        HttpUrl completeUrl = querybuilder.queriedUrl(url);

        //Builds the Request class
        Request request = this.BuildGET(completeUrl);


        return request;
    }

}

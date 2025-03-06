package com.example.biovision.API.Request;

import com.example.biovision.API.Request.service.GET;
import com.example.biovision.API.Request.service.POST;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Request implements GET, POST{
    private String api_key;
    private String url;

    private RequestBuilder requestBuilder;

    private final OkHttpClient CLIENT = new OkHttpClient();
    public Request(String api_key, String url){
        this.api_key = api_key;
        this.url = url;

        RequestBuilder requestBuilder = new RequestBuilder(api_key, url, RequestType.GET);
    }

    public ResponseBody GET(HashMap<String, String> params) throws IOException {
        okhttp3.Request request = requestBuilder.BuildGET(params);

        try(Response response = CLIENT.newCall(request).execute()) {
            if (response.isSuccessful()){
                return response.body();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }


    public ResponseBody GET() {
        okhttp3.Request request = requestBuilder.BuildGET();

        try(Response response = CLIENT.newCall(request).execute()) {
            if (response.isSuccessful()){
                return response.body();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public void POST(){
        //TODO: Implement POST method

    }
}


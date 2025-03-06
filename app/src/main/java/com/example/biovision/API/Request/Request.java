package com.example.biovision.API.Request;

import com.example.biovision.API.Request.service.GET;
import com.example.biovision.API.Request.service.POST;

import java.util.HashMap;

public class Request implements GET, POST{
    private static RequestBuilder r = new RequestBuilder("https://sample.com", "3242443", RequestType.GET);

    public void GET(HashMap<String, String> params){

    }

    public void POST(){

    }


    @Override
    public okhttp3.Request GET() {
        return null;
    }
}

package com.example.biovision.API.Plant;

import com.example.biovision.API.Request.Request;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;

public class PlantRequest {

    private String api_key;
    public PlantRequest(String api_key){
        this.api_key = api_key;
    }

    public ResponseBody plantSearch(String q) throws IOException {
        Request request = new Request(api_key, "https://bio-vision-api.vercel.app/api/v1/plant/search");

        HashMap<String, String> params = new HashMap<>();
        params.put("q", q);

        ResponseBody response = request.GET((HashMap<String, String>) params);
        return  response;
    }
}

package com.example.biovision.API.Plant;

import com.example.biovision.API.Request.Request;
import com.example.biovision.API.Request.util.QueryBuilder;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class PlantRequest {

    private String api_key;

    public PlantRequest(String api_key) {
        this.api_key = api_key;
    }

    public Response plantSearch(String q) throws IOException {
        Request request = new Request(api_key, "https://bio-vision-api.vercel.app/api/v1/plant/search");

        HashMap<String, String> params = new HashMap<>();
        params.put("q", q);


        Response response = request.GET((HashMap<String, String>) params);
        return response;
    }

    public Response plantScan(JSONObject payload) {
        Request request = new Request(api_key, "bio-vision-api.vercel.app/identification");
        request.POST(payload);

        return null;
    }
}

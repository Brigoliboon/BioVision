package com.example.biovision;

import com.example.biovision.API.Plant.PlantRequest;
import com.example.biovision.API.Request.Request;

import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Response;
import okhttp3.ResponseBody;

public class ResponseUnitTest {

    @Test
    public void testPlantSearch() throws IOException {
        PlantRequest plantAPI = new PlantRequest("qzG7VtS3JdK9pL6Rwx2YhQ8Zb5No3KfE4M1sTzAqB7FvXjC8hL");
        Response response= plantAPI.plantSearch("Strawberry");

        System.out.println(response.body().string());
    }
    @Test
    public void testPlantSearchv2() throws IOException {
        Request request = new Request("qzG7VtS3JdK9pL6Rwx2YhQ8Zb5No3KfE4M1sTzAqB7FvXjC8hL", "https://bio-vision-api.vercel.app/api/v1/plant/search?");
        HashMap<String, String> map = new HashMap<>();
        map.put("q", "strawberry");
        System.out.println(request.GET(map).body().string());
    }

    @Test
    public void ScanPlant(){

    }
}

package com.example.biovision;

import com.example.biovision.API.Plant.PlantRequest;

import org.junit.Test;

import java.io.IOException;

import okhttp3.ResponseBody;

public class ResponseUnitTest {

    @Test
    public void testPlantSearch() throws IOException {
        PlantRequest plantAPI = new PlantRequest("qzG7VtS3JdK9pL6Rwx2YhQ8Zb5No3KfE4M1sTzAqB7FvXjC8hL");
        ResponseBody response = plantAPI.plantSearch("Straw berry");
        String responseStr = response.string();
        System.out.println(responseStr);
    }
}

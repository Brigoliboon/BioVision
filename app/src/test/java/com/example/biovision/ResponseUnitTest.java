package com.example.biovision;

import com.example.biovision.core.Result;
import com.example.biovision.data.API.Connection.exception.RuntimeTimeoutException;
import com.example.biovision.data.API.Connection.exception.UnauthorizedException;
import com.example.biovision.data.API.Plant.PlantRequest;
import com.example.biovision.data.API.Plant.model.PlantResult;
import com.example.biovision.data.API.Plant.util.PayloadGenerator;
import com.example.biovision.data.API.Request.Request;
import com.example.biovision.Camera.util.ImageProcess;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.io.IOException;
import java.util.HashMap;

import okhttp3.Response;
import okhttp3.ResponseBody;

public class ResponseUnitTest {

    @Test
    public void testPlantSearch() throws IOException, RuntimeTimeoutException, UnauthorizedException {
        PlantRequest plantAPI = new PlantRequest("qzG7VtS3JdK9pL6Rwx2YhQ8Zb5No3KfE4M1sTzAqB7FvXjC8hL");
        Response response= plantAPI.plantSearch("Strawberry");

        System.out.println(response.body().string());
    }
    @Test
    public void testPlantSearchv2() throws IOException, RuntimeTimeoutException, UnauthorizedException {
        Request request = new Request("qzG7VtS3JdK9pL6Rwx2YhQ8Zb5No3KfE4M1sTzAqB7FvXjC8hL", "https://bio-vision-api.vercel.app/api/v1/plant/search?");
        HashMap<String, String> map = new HashMap<>();
        map.put("q", "strawberry");
        System.out.println(request.GET(map).body().string());
    }

    @Test
    public void ScanPlant() throws JSONException, IOException {
        PlantRequest plantAPI = new PlantRequest("qzG7VtS3JdK9pL6Rwx2YhQ8Zb5No3KfE4M1sTzAqB7FvXjC8hL");
        // Parse sample image into b64
        String b64 = ImageProcess.encodeImageToBase64("D:\\Central Mindanao University\\projects\\BioVision\\app\\src\\test\\java\\com\\example\\biovision\\ladybug.png");
        JSONObject payload = PayloadGenerator.generatePayload(b64);
        Result<PlantResult> r = plantAPI.plantScan(payload);

        System.out.println(r);
    }
}

package com.example.biovision.API.Request;

import com.example.biovision.API.Plant.PlantRequest;
import com.example.biovision.API.Plant.util.PayloadGenerator;
import com.example.biovision.Camera.util.ImageProcess;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;

public class Main {

    public static void main(String[] args){
        PlantRequest plantAPI = new PlantRequest("qzG7VtS3JdK9pL6Rwx2YhQ8Zb5No3KfE4M1sTzAqB7FvXjC8hL");
        String b64Image = ImageProcess.encodeImageToBase64("com/example/biovision/img.png");
        JSONObject payload = null;
        try {
            payload = PayloadGenerator.generatePayload(b64Image);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        ResponseBody result = plantAPI.plantScan(payload);
        System.out.println(result.toString());
    }
}

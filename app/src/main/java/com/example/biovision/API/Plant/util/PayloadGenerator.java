package com.example.biovision.API.Plant.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;

public class PayloadGenerator {
    public static JSONObject generatePayload(String imgB64) throws JSONException {
        JSONObject data = new JSONObject();
        JSONArray imagesArray = new JSONArray();
        imagesArray.put(imgB64);

        data.put("images", imagesArray);
        // Implement location (latitude and longitude)
        data.put("latitude", 8.4770816);
        data.put("longitude", 124.6461952);
        data.put("datetime", LocalDate.now());
        data.put("similar_mages", true);

        return data;
    }
}

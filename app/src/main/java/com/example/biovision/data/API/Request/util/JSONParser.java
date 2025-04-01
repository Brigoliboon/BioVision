package com.example.biovision.data.API.Request.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;

public class JSONParser {

    public static JSONObject parsetoJSON(ResponseBody response) throws IOException, JSONException {
        // TODO
        JSONObject json = new JSONObject(response.string());

        return  json;
    }

    public static JSONObject parsetoJSON(String response){
        //TODO
        return null;
    }


}

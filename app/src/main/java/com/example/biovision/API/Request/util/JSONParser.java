package com.example.biovision.API.Request.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;

public class JSONParser {

    public JSONObject parsetoJSON(ResponseBody response) throws IOException, JSONException {
        // TODO
        JSONObject json = new JSONObject(response.string());

        return  json;
    }

    public JSONObject parsetoJSON(String response){
        //TODO
        return null;
    }


}

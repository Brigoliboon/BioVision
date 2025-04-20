package com.example.biovision.data.API.Plant;

import com.example.biovision.core.Result;
import com.example.biovision.data.API.Connection.exception.NetworkErrorException;
import com.example.biovision.data.API.Connection.exception.UnauthorizedException;
import com.example.biovision.data.API.Plant.model.PlantResult;
import com.example.biovision.data.API.Plant.util.PlantResultBuilder;
import com.example.biovision.data.API.Request.Request;
import com.example.biovision.data.API.Request.util.JSONParser;

import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.Response;
import okhttp3.ResponseBody;

public class PlantRequest {

    private String api_key;

    public PlantRequest(String api_key) {
        this.api_key = api_key;
    }

    public Response plantSearch(String q) throws UnauthorizedException, NetworkErrorException {
        Request request = new Request(api_key, "https://bio-vision-api.vercel.app/api/v1/plant/search");

        HashMap<String, String> params = new HashMap<>();
        params.put("q", q);

        Response response = request.GET(params);
        return response;
    }

    public Result<PlantResult> plantScan(JSONObject payload) {
        try {
            Request request = new Request(api_key, "https://bio-vision-api.vercel.app/api/v1/plant/identification");
            Response r = request.POST(payload);

            JSONObject data = JSONParser.parsetoJSON(r.body());
            PlantResult plantResponse = PlantResultBuilder.plantResultBuilder(data);

            return new Result.Success<>(plantResponse);
        } catch (Exception e) {
            return new Result.Error<>(e);
        }
    }
}

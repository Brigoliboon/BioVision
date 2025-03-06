package com.example.biovision.API.Request;

import com.example.biovision.API.Request.service.GET;
import com.example.biovision.API.Request.service.POST;
import com.example.biovision.API.Request.util.ResponseBodyParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.Response;

public class Request implements GET, POST{
    private String api_key;
    private String url;

    private RequestBuilder requestBuilder;

    private final OkHttpClient CLIENT = new OkHttpClient();
    private final ResponseBodyParser PARSE = new ResponseBodyParser();

    public Request(String api_key, String url){
        this.api_key = api_key;
        this.url = url;
        this.requestBuilder = new RequestBuilder(api_key, url, RequestType.GET);
    }

    public JSONObject GET(HashMap<String, String> params) throws IOException {
        okhttp3.Request request = requestBuilder.BuildGET(params);

        try(Response response = CLIENT.newCall(request).execute()) {
            if (response.isSuccessful()){
                return PARSE.parsetoJSON(response.body());
            }


        } catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public JSONObject GET() {
        okhttp3.Request request = requestBuilder.BuildGET();

        try {
            Response response = CLIENT.newCall(request).execute();
            if (response.isSuccessful()) {
                return PARSE.parsetoJSON(response.body());
            }


        }catch (IOException e){
            e.printStackTrace();
            System.err.println(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return  null;
    }

    public boolean isConnected() {
        okhttp3.Request request = requestBuilder.BuildGET(api_key);

        try(Response response = CLIENT.newCall(request).execute()) {
                return response.code() == 200;
        } catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    public void POST(){
        //TODO: Implement POST method

    }
}


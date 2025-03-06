package com.example.biovision.API.Connection;

import com.example.biovision.API.Request.Request;

import okhttp3.ResponseBody;

public class Connection {

    public boolean isAuthorized(){
        Request request = new Request("rAq4eAZ3wRE4sJFyVC5Y8wgH6Vcb0USuRZStsJTbp0nES2V2vN6IvIGxMiaLGl4", "https://bio-vision-api.vercel.app/auth");

        return request.isConnected();
    }
}

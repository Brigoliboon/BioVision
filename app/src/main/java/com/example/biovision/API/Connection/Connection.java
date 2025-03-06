package com.example.biovision.API.Connection;

import com.example.biovision.API.Request.Request;

import okhttp3.ResponseBody;

public class Connection {

    public boolean isAuthorized(){
        Request request = new Request("qzG7VtS3JdK9pL6Rwx2YhQ8Zb5No3KfE4M1sTzAqB7FvXjC8hL", "https://bio-vision-api.vercel.app/auth");
        return request.isConnected();
    }
}

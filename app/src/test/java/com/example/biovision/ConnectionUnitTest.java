package com.example.biovision;

import com.example.biovision.API.Connection.Connection;
import com.example.biovision.API.Request.Request;

import org.junit.Test;

public class ConnectionUnitTest {

    @Test
    public void isAuthorized(){
        Connection connection = new Connection();
        System.out.println("Testing Authorization: ");
        System.out.println(connection.isAuthorized());
    }

    @Test
    public void LandingPage() {
        Request request = new Request("qzG7VtS3JdK9pL6Rwx2YhQ8Zb5No3KfE4M1sTzAqB7FvXjC8hL", "https://bio-vision-api.vercel.app/");
        System.out.println("Checking Landing Page: ");
        System.out.println(request.isConnected());
    }

    @Test
    public void API_LandingPage(){
        Request request = new Request("qzG7VtS3JdK9pL6Rwx2YhQ8Zb5No3KfE4M1sTzAqB7FvXjC8hL", "https://bio-vision-api.vercel.app/api");
        System.out.println("Checking API Anchor: ");
        System.out.println(request.isConnected());
    }

}

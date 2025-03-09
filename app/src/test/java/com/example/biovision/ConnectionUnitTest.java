package com.example.biovision;

import android.content.Context;

import com.example.biovision.API.Connection.Connection;
import com.example.biovision.API.Connection.exception.NetworkErrorException;
import com.example.biovision.API.Connection.exception.RuntimeTimeoutException;
import com.example.biovision.API.Connection.exception.UnauthorizedException;
import com.example.biovision.API.Request.Request;

import androidx.test.InstrumentationRegistry;


import org.checkerframework.checker.units.qual.C;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.Response;

public class ConnectionUnitTest {

    @Test
    public void test(){
        System.out.println(SocketTimeoutException.class.equals(SocketTimeoutException.class));
    }
    public void displayTitle(String title){
        System.out.printf("-------------[%s]-------------\n", title);
    }

    public void printError(Class cls, String message){
        System.err.printf("Error type: %s\n", cls.getSimpleName());
        System.err.printf("Message: %s\n", message);
    }

    @Test
    public void isAuthorized() throws RuntimeTimeoutException {
        Connection connection = new Connection();
        displayTitle("Testing Authorization");

        try{
            boolean status = connection.isAuthorized();
            System.out.printf("Status: %s\n", status);

            assertEquals(true, status);
        } catch (NetworkErrorException e){
            System.out.printf("Error type: %s\n", e.getType().getClass().getSimpleName());
            System.out.printf("Error Message: %s\n", e.getMessage());
        }
    }

    @Test
    public void isNotAuthorized() throws NetworkErrorException {
        // Invalid API key
        Connection connection = new Connection("qzG7VtS3JdK9pL6Rwx2YhQ8Zb5No3KfE4M1sTzAqB7FvXjC8h");

        System.out.println("-------------Testing Authorization-------------");
        try {
            boolean status = connection.isAuthorized();
            System.out.printf("Status: %s\n", status);

            assertEquals(false, status);
        } catch (NetworkErrorException e){
            System.err.printf("Error type: %s\n", e.getType().getClass().getSimpleName());
            System.err.printf("Error Message: %s\n", e.getMessage());
        }
    }

    // Need to connect to an internet with no/slow internet connection
//    @Test
//    public void runtimeTimeoutError() {
//        Request request = new Request("qzG7VtS3JdK9pL6Rwx2YhQ8Zb5No3KfE4M1sTzAqB7FvXjC8hL", "https://bio-vision-api.vercel.app/");
//        displayTitle("Runtime Timeout Exception");
//
//        try {
//            request.isConnected();
//        } catch (NetworkErrorException e) {
//            Class cls = e.getType().getClass();
//            String message = e.getMessage();
//
//            printError(cls, message);
//            // Asserts if the NetworkError is due to timeout
//            assertEquals(SocketTimeoutException.class, cls);
//
//        } catch (UnauthorizedException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Test
    public void LandingPage() throws UnauthorizedException {
        Request request = new Request("qzG7VtS3JdK9pL6Rwx2YhQ8Zb5No3KfE4M1sTzAqB7FvXjC8hL", "https://bio-vision-api.vercel.app/");
        System.out.println("Checking Landing Page: ");
        System.out.println(request.isConnected());
    }

    @Test
    public void API_LandingPage() throws RuntimeTimeoutException, UnauthorizedException {
        Request request = new Request("qzG7VtS3JdK9pL6Rwx2YhQ8Zb5No3KfE4M1sTzAqB7FvXjC8hL", "https://bio-vision-api.vercel.app/api");
        System.out.println("Checking API Anchor: ");
        System.out.println(request.isConnected());
    }

}

package com.example.biovision;

import org.junit.Test;

import com.example.biovision.API.Connection.Connection;
import com.example.biovision.API.Request.Request;

import java.io.IOException;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws IOException {
        Connection connection = new Connection();
        System.out.println("Testing Connection: ");
        Request request = new Request("qzG7VtS3JdK9pL6Rwx2YhQ8Zb5No3KfE4M1sTzAqB7FvXjC8hL", "https://bio-vision-api.vercel.app/auth");
        boolean response = request.isConnected();
        System.out.println(connection.isAuthorized());
    }
}
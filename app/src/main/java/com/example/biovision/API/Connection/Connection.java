package com.example.biovision.API.Connection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;

import com.example.biovision.API.Connection.exception.NetworkErrorException;
import com.example.biovision.API.Connection.exception.RuntimeTimeoutException;
import com.example.biovision.API.Connection.exception.UnauthorizedException;
import com.example.biovision.API.Request.Request;

import okhttp3.Response;

import java.io.IOException;
import java.net.*;

public class Connection {
    private String apiKey;

    public Connection(String api_key){
        this.apiKey = api_key;
    }

    public Connection(){
        this.apiKey = "qzG7VtS3JdK9pL6Rwx2YhQ8Zb5No3KfE4M1sTzAqB7FvXjC8hL";
    }

    public boolean isAuthorized() throws NetworkErrorException {
        Request request = new Request(apiKey, "https://bio-vision-api.vercel.app/auth");

        try {
            Response response = request.GET();
            return response.isSuccessful();

        } catch (UnauthorizedException e) {
            return false;
        }


        // Catches the Unauthorized exception and returns to false
    }

// Checks for the enabled networks
    public static NetworkType getNetworkStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // For Android 6.0 (API 23+) and above
                android.net.Network network = cm.getActiveNetwork();
                if (network != null) {
                    NetworkCapabilities capabilities = cm.getNetworkCapabilities(network);
                    if (capabilities != null) {
                        if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                            return NetworkType.WIFI;
                        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                            return NetworkType.DATA;
                        }
                    }
                }
                throw new NetworkErrorException(new IOException(), "No network");
            }
        }
        return null;
    }
}

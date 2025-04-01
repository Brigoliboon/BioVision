package com.example.biovision.data.API.Connection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;

import com.example.biovision.data.API.Connection.exception.NetworkErrorException;
import com.example.biovision.data.API.Connection.exception.UnauthorizedException;
import com.example.biovision.data.API.Request.Request;

import okhttp3.Response;

import java.io.IOException;

public class Connection {
    private String apiKey;

    public Connection(String api_key){
        this.apiKey = api_key;
    }

    public Connection(){
        this.apiKey = "qzG7VtS3JdK9pL6Rwx2YhQ8Zb5No3KfE4M1sTzAqB7FvXjC8hL";
    }

    public boolean isAuthorized(){
        // TODO: IMPORTANT! implement immediately
        return true;
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

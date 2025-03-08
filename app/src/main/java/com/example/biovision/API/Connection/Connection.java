package com.example.biovision.API.Connection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;

import com.example.biovision.API.Connection.exception.RuntimeTimeoutException;
import com.example.biovision.API.Connection.exception.UnauthorizedException;
import com.example.biovision.API.Request.Request;

import okhttp3.ResponseBody;

public class Connection {

    public boolean isAuthorized() throws RuntimeTimeoutException {
        Request request = new Request("qzG7VtS3JdK9pL6Rwx2YhQ8Zb5No3KfE4M1sTzAqB7FvXjC8hL", "https://bio-vision-api.vercel.app/auth");

        try {

            return request.isConnected();
        }
        // Catches the Unauthorized exception and returns to false
        catch (UnauthorizedException e) {
            return false;
        }
    }

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
            }
        }
    }
}

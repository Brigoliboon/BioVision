package com.example.biovision;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.content.Intent;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.biovision.API.Connection.AuthenticationStatus;
import com.example.biovision.API.Connection.Connection;
import com.example.biovision.API.Connection.exception.NetworkErrorException;
import com.example.biovision.API.Connection.exception.RuntimeTimeoutException;

import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        final Handler handler = new Handler();

        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /* Problem: The Connection network returns an error when it is attempted to run on MainThread
        * Solution: The Connection network will be running on different thread
        * */
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            // Runs on different thread
            AuthenticationStatus finalStatus = getAuthenticationStatus();
            runOnUiThread(() -> {

                /*
                 * Displays splashscreen for a specific range of time
                 * Ends in starting the Home Activity.
                 * */
                if (finalStatus == AuthenticationStatus.AUTHENTICATED) {
                    Toast.makeText(MainActivity.this,"Connection Established", Toast.LENGTH_SHORT).show();
                    handler.postDelayed(() -> {
                        Intent i = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(i);
                        finish();
                    }, 3000);
                }else if(finalStatus == AuthenticationStatus.BLOCKED) {
                    displayUnauthorizedAccess();
                }else if(finalStatus == AuthenticationStatus.TIMEOUT){
                    displayTimeoutRequest();
                }else{
                    displayAlertBanner("Network Issue", "Please make sure you are connected to the internet and try again");
                }
            });
        });

//        getSupportActionBar().hide();
    }

    @NonNull
    private static AuthenticationStatus getAuthenticationStatus() {
        Connection connection = new Connection();
        AuthenticationStatus status = null;

        try {
            status = connection.isAuthorized()? AuthenticationStatus.AUTHENTICATED: AuthenticationStatus.BLOCKED;

        } catch (NetworkErrorException e) {
            if (e.getErrorType().equals(SocketTimeoutException.class)){
                status = AuthenticationStatus.TIMEOUT;
            }else{
                status = AuthenticationStatus.NETWORKISSUE;
            }
        }
        // Updates UI
        AuthenticationStatus finalStatus = status;
        return finalStatus;
    }

    /*
     * Displays alert banner when the user is not authorized to access the application
     *
     * */
    public void displayAlertBanner(String title, String message){
        // Create the object of AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        // Set the message show for the Alert time
        builder.setMessage(message);

        // Set Alert Title
        builder.setTitle(title);

        // Set Cancelable false for when the user clicks
        // on the outside the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name Lambda
        // OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton("Exit", (DialogInterface.OnClickListener) (dialog, which) -> {

            // When the user click yes button then app will close
            finish();
        });

        builder.show();
    }
    public void displayTimeoutRequest() {
        displayAlertBanner("Request Timeout", "Please make sure you are connected to the internet and try again");
    }
    public void displayUnauthorizedAccess(){
        displayAlertBanner("Unauthorized Access", "Locked by system administrator. Kindly contact the system administrator for further details");
    }
}
package com.example.biovision.ui.main;

import android.app.AlertDialog;
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

import com.example.biovision.data.API.Connection.ConnectionStatus;
import com.example.biovision.data.API.Connection.Connection;
import com.example.biovision.data.API.Connection.exception.NetworkErrorException;
import com.example.biovision.R;
import com.example.biovision.ui.camera.CameraActivity;
import com.example.biovision.ui.login.LoginActivity;

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
            ConnectionStatus finalStatus = getAuthenticationStatus();
            runOnUiThread(() -> {

                /*
                 * Displays splashscreen for a specific range of time
                 * Ends in starting the Home Activity.
                 * */
                if (finalStatus == ConnectionStatus.AUTHENTICATED) {
                    Toast.makeText(MainActivity.this,"Connection Established", Toast.LENGTH_SHORT).show();
                    handler.postDelayed(() -> {
                        Intent i = new Intent(MainActivity.this, CameraActivity.class);
                        startActivity(i);
                        finish();
                    }, 3000);
                }else if(finalStatus == ConnectionStatus.BLOCKED) {
                    displayUnauthorizedAccess();
                }else if(finalStatus == ConnectionStatus.TIMEOUT){
                    displayTimeoutRequest();
                }else{
                    displayAlertBanner("Network Issue", "Please make sure you are connected to the internet and try again");
                }
            });
        });

//        getSupportActionBar().hide();
    }

    @NonNull
    private static ConnectionStatus getAuthenticationStatus() {
        Connection connection = new Connection();
        ConnectionStatus status = ConnectionStatus.BLOCKED;

        try {
            status = connection.isAuthorized()? ConnectionStatus.AUTHENTICATED: ConnectionStatus.BLOCKED;

        } catch (NetworkErrorException e) {
            if (e.getErrorType().equals(SocketTimeoutException.class)){
                status = ConnectionStatus.TIMEOUT;
            }else{
                status = ConnectionStatus.NETWORKISSUE;
            }
        }
        // Updates UI
        ConnectionStatus finalStatus = status;
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
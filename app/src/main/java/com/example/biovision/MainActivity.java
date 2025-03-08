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
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.biovision.API.Connection.Connection;
import com.example.biovision.API.Connection.exception.RuntimeTimeoutException;

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
            Connection connection = new Connection();

            boolean isAuthorized;

            try {
                isAuthorized = connection.isAuthorized();

            } catch (RuntimeTimeoutException e) {
                throw new RuntimeException(e);
            }

            // Updates UI
            runOnUiThread(() -> {

                /*
                 * Displays splashscreen for a specific range of time
                 * Ends in starting the Home Activity.
                 * */
                if (isAuthorized) {
                    Toast.makeText(MainActivity.this,"Connection Established", Toast.LENGTH_SHORT).show();
                    handler.postDelayed(() -> {
                        Intent i = new Intent(MainActivity.this, HomeActivity.class);

                        startActivity(i);
                        finish();
                    }, 3000);
                }else{
                    displayUnauthorizedAccess();
                }
            });
        });

//        getSupportActionBar().hide();
    }

    /*
     * Displays alert banner when the user is not authorized to access the application
     *
     * */
    public void displayUnauthorizedAccess(){
        // Create the object of AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        // Set the message show for the Alert time
        builder.setMessage("Locked by system administrator");

        // Set Alert Title
        builder.setTitle("Unauthorized Access");

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
}
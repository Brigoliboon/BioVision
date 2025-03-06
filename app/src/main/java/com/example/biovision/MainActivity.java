package com.example.biovision;

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

        /*
        * Displays splashscreen for a specific range of time
        * Ends in starting the Home Activity.
        * */
        if (new Connection().isAuthorized()) {
//            Toast.makeText(MainActivity.this,"Connection Established", Toast.LENGTH_SHORT).show();
            handler.postDelayed(() -> {
                Intent i = new Intent(MainActivity.this, TestActivity.class);

                startActivity(i);
                finish();
            }, 3000);
        }else{
//            Toast.makeText(MainActivity.this,"Connection Denied", Toast.LENGTH_SHORT).show();
        }

//        getSupportActionBar().hide();
    }
}
package com.example.biovision.ui.camera;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.Manifest;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.biovision.data.API.Plant.PlantRequest;
import com.example.biovision.data.API.Plant.model.PlantResult;
import com.example.biovision.Camera.util.ImageProcess;
import com.example.biovision.R;
import com.example.biovision.data.API.Plant.util.PlantResultBuilder;
import com.example.biovision.ui.components.BottomSheetFragmentv2;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.BreakIterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONException;
import org.json.JSONObject;

public class CameraActivity extends AppCompatActivity {
    ImageButton capture; // The id element for the capture button
    SearchView search_bar; // The id element for search compat query
    private PreviewView previewView; // The id element for the preview surface of camera view

    private static final PlantRequest plantAPI = new PlantRequest("qzG7VtS3JdK9pL6Rwx2YhQ8Zb5No3KfE4M1sTzAqB7FvXjC8hL");

    /*
    * Camera Lens State
    *
    * 0: Front Camera
    * 1: Back Camera
    *-1: Camera Facing Unknown
    * */
    int cameraFacing = CameraSelector.LENS_FACING_BACK; // Start the camera on state 1 by default
    private FusedLocationProviderClient fusedLocationClient;
    private final ActivityResultLauncher<String> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
        @Override
        public void onActivityResult(Boolean result) {
            if (result){
                startCamera(cameraFacing);
            }
        }
    });

    // onCreate Event
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Finds and assigns the given element compat
        search_bar = findViewById(R.id.search_bar);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        previewView = findViewById(R.id.cameraPreview);
        capture = findViewById(R.id.capture);
        // Checks in android manifest files if the permission is listed and is granted
        if (ContextCompat.checkSelfPermission(CameraActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            activityResultLauncher.launch(Manifest.permission.CAMERA);
        }else {
            startCamera(cameraFacing);
        }
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        fusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    double latitude = location.getLatitude();
                    double altitude = location.getAltitude(); // Altitude in meters
                    BreakIterator locationText = null;
                    locationText.setText("Latitude: " + latitude + "\nAltitude: " + altitude + " meters");
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getLocation();
        }
    }

    public void debugger(String message){
        Toast.makeText(CameraActivity.this,message, Toast.LENGTH_SHORT).show();
    }
    public void startCamera(int cameraFacing){
        /*
        * Starts the camera and displays on a surfaceView
        * */
        ListenableFuture listenableFuture = ProcessCameraProvider.getInstance(this);
        listenableFuture.addListener(() -> {
            try {
                // Displays to indicate that the Camera is initialized.
                // TODO needs to be removed; for debugging purposes only
                Toast.makeText(CameraActivity.this, "Camera Started", Toast.LENGTH_SHORT).show();

                ProcessCameraProvider cameraProvider = (ProcessCameraProvider) listenableFuture.get();

                Preview preview = new Preview.Builder()
                        .build();

                //sets the surface view for the previewView
                preview.setSurfaceProvider(previewView.getSurfaceProvider());
                ImageCapture imageCapture = new ImageCapture.Builder().setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY).setTargetRotation(getWindowManager().getDefaultDisplay().getRotation()).build();

                CameraSelector cameraSelector = new CameraSelector.Builder()
                        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                        .build(); // Builds the camera to utilize

                cameraProvider.unbindAll(); // Unbinds the camera to ensure that no camera is provided before the the cycle starts or resumes.

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Camera camera = cameraProvider.bindToLifecycle(this, cameraSelector, imageCapture, preview);
                    Toast.makeText(CameraActivity.this,camera.getCameraInfo().getCameraState().getValue().toString(), Toast.LENGTH_SHORT).show();

                }

                capture.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {

                        if (ContextCompat.checkSelfPermission(CameraActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                            activityResultLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                        }else {
                            takePicture(imageCapture);
                        }
                    }
                });
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }, ContextCompat.getMainExecutor(this));
    }

    //TODO Organize the folder-file saving structure
    public void takePicture(ImageCapture imageCapture){
        debugger("Taking a Picture");
        //saves the picture after taking it
        final File file = new File(getExternalFilesDir(null), System.currentTimeMillis() + ".jpg");
        ImageCapture.OutputFileOptions outputFileOptions = new ImageCapture.OutputFileOptions.Builder(file).build();
        imageCapture.takePicture(outputFileOptions, Executors.newCachedThreadPool(), new ImageCapture.OnImageSavedCallback() {
            @Override
            public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                String b64Image = ImageProcess.compressAndEncodeImage(file.getPath());
                JSONObject payload = null;
                PlantResult plantResult;

                try {
                    /**
                     * Disabled for testing purposes
                     */
//                    payload = PayloadGenerator.generatePayload(b64Image);
//                    ResponseBody result = plantAPI.plantScan(payload);
//                    plantResult = PlantResultBuilder.plantResultBuilder(JSONParser.parsetoJSON(result));
                    /**
                     * Preloaded Response result
                     * after testing, change the minimum sdk to 26
                     */
                    InputStream inputStream = getAssets().open("sampleResponse.json");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder jsonData = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        jsonData.append(line);
                    }
                    reader.close();
                    inputStream.close();
                    JSONObject json = new JSONObject(String.valueOf(jsonData));
                    plantResult = PlantResultBuilder.plantResultBuilder(json);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        {
                            try {
                                showDialog(plantResult);
                            } catch (IOException | JSONException e) {
                                throw new RuntimeException(e);
                            }
                            capture.setEnabled(true);
                        }
                    }
                });
                // To avoid spamming, disable the button during the saving process
                if (!capture.isEnabled()) {
                    capture.setEnabled(true);
                    startCamera(cameraFacing);
                }
            }

            @Override
            public void onError(@NonNull ImageCaptureException exception) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(CameraActivity.this, "Failed to save: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                startCamera(cameraFacing);
            }
        });
    }

    public void showDialog(PlantResult result) throws IOException, JSONException {
        BottomSheetFragmentv2 bottomSheet = new BottomSheetFragmentv2(result);
        bottomSheet.show(getSupportFragmentManager(), "BottomSheetFragment");
    }
}
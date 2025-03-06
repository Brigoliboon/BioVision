package com.example.biovision.Camera;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;

import com.google.common.util.concurrent.ListenableFuture;

public class Camera {
    private int cameraFacing;
    private PreviewView cameraDisplay;

    private Context context;

    public Camera(int cameraFacing, PreviewView cameraDisplay, Context context) {
        this.cameraFacing = cameraFacing;
        this.cameraDisplay = cameraDisplay;
        this.context = context;
    }
}
/*
    public void startCamera(int cameraFacing){
        ListenableFuture listenableFuture = ProcessCameraProvider.getInstance(context);
        listenableFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = (ProcessCameraProvider) listenableFuture.get();

                Preview preview = new Preview.Builder()
                        .build();

                //sets the surface view for tbe previewView
                preview.setSurfaceProvider(cameraDisplay.getSurfaceProvider());
                ImageCapture imageCapture = new ImageCapture.Builder().setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY).setTargetRotation(getWindowManager().getDefaultDisplay().getRotation()).build();

                CameraSelector cameraSelector = new CameraSelector.Builder()
                        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                        .build();

                cameraProvider.unbindAll();
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    androidx.camera.core.Camera camera = cameraProvider.bindToLifecycle(this, cameraSelector, imageCapture, preview);

                }
            }
        }
}

 */

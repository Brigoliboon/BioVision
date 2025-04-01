package com.example.biovision.Camera.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class ImageProcess {
    public static String encodeImageToBase64(String imagePath) {
        File file = new File(imagePath);
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] imageBytes = new byte[(int) file.length()];
            fileInputStream.read(imageBytes);
            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *  Encodes the image into base64 string format.
     * @param imagePath
     * @return The base 64 of the image
     */
    public static String compressAndEncodeImage(String imagePath) {
        // Load the image
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2; // Reduce the image by 50% (adjust as needed)
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);

        // Compress the image (JPEG, 50% quality)
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);

        // Convert to Base64
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.getEncoder().encodeToString(byteArray);
    }

    /**
     * Checks the resolution of an image whether it is recognizable enough to be accepted or not
     * @param imagePath the path of the image
     * @return The Image Quality enum
     */
    public static void checkImgQuality(String imagePath){
        // TODO: Implement to check the quality of an image
    }
}

package com.example.biovision;

import android.util.JsonReader;

import com.example.biovision.data.API.Plant.model.PlantResult;
import com.example.biovision.data.API.Plant.util.PlantResultBuilder;
import com.example.biovision.data.API.Request.util.JSONParser;
import com.example.biovision.viewmodel.PlantViewModel;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;

import kotlin.io.ByteStreamsKt;

public class SampleResponseUnitTest {

    @Test
    public void testSearchPlant(){

    }

    @Test
    public  void testGetPlantInfo(){

    }

    @Test
    public void testScanPlant() throws JSONException, IOException {
        String data = new String(
                Files.readAllBytes(Path.of("D:\\Central Mindanao University\\projects\\BioVision\\app\\src\\main\\assets\\sampleResponse.json"))
        );

        JSONObject json = new JSONObject(data);
        PlantResult result = PlantResultBuilder.plantResultBuilder(json);

        PlantViewModel plantViewModel = new PlantViewModel();
        System.out.println(plantViewModel);
    }
}

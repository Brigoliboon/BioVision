package com.example.biovision.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.biovision.data.API.Plant.PlantRequest;
import com.example.biovision.data.API.Plant.model.Plant;
import com.example.biovision.data.API.Plant.model.PlantResult;
import com.example.biovision.data.API.Plant.util.PlantResultBuilder;
import com.example.biovision.data.API.Request.util.JSONParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;

public class PlantViewModel extends ViewModel {
    private MutableLiveData<JSONObject> rawData = new MutableLiveData<>();
    private MutableLiveData<PlantResult> result = new MutableLiveData<>();
    private MutableLiveData<Plant> chosenPlant = new MutableLiveData<>();
    public LiveData<PlantResult> getResult() {
        return result;
    }
    public LiveData<Plant> getChosenPlant(){
        return chosenPlant;
    }

    public void plantScan(JSONObject payload){
        // TODO: IMPORTANT! Exposed secret
        PlantRequest plantAPI = new PlantRequest("qzG7VtS3JdK9pL6Rwx2YhQ8Zb5No3KfE4M1sTzAqB7FvXjC8hL");
        ResponseBody response = plantAPI.plantScan(payload);
        try {
            JSONObject data = JSONParser.parsetoJSON(response);
            rawData.setValue(data);

            PlantResult plantResponse = PlantResultBuilder.plantResultBuilder(data);
            result.setValue(plantResponse);
            chosenPlant.setValue(plantResponse.getClosestMatch());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}

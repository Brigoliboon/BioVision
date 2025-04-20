package com.example.biovision.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.biovision.core.Result;
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
    private MutableLiveData<Result<PlantResult>> result = new MutableLiveData<>();
    private MutableLiveData<Plant> chosenPlant = new MutableLiveData<>();
    public LiveData<Result<PlantResult>> getResult() {
        return result;
    }
    public LiveData<Plant> getChosenPlant(){
        return chosenPlant;
    }

    public void setChosenPlant(Plant plant){
        chosenPlant.setValue(plant);
    }

    public void plantSearch(String q){
        // TODO: Next Release
    }
    public void plantScan(JSONObject payload){
        // TODO: IMPORTANT! Exposed secret
        PlantRequest plantAPI = new PlantRequest("qzG7VtS3JdK9pL6Rwx2YhQ8Zb5No3KfE4M1sTzAqB7FvXjC8hL");
        result.setValue(new Result.Loading<>());

        new Thread(() -> {
            Result<PlantResult> response = plantAPI.plantScan(payload);
            result.postValue(response);
        }).start();
    }
}

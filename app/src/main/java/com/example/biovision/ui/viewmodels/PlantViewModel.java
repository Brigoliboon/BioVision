package com.example.biovision.ui.viewmodels;

import androidx.lifecycle.ViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PlantViewModel extends ViewModel {
    JSONObject data;

    PlantViewModel(JSONObject data){
        this.data = data;
    }

    public JSONArray getMatchList() throws JSONException {
        JSONObject result = (JSONObject) data.get("classification");
        return result.getJSONArray("suggestions");
    }


}

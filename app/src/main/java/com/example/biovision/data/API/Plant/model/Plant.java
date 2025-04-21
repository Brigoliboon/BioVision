package com.example.biovision.data.API.Plant.model;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public record Plant(String id, String scientificName, double probability, ArrayList<Image> similarImages, PlantDetail detail) {
    /**
     *JSON Keys
     */
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PROBABILITY = "probability";
    public static final String DETAILS = "details";

    public String getMatchPercent(){
        return (probability * 100) + "%";
    }

    public String getCommonName(){
        if (detail.commonNames() !=null){
            JSONArray array = (JSONArray) detail().commonNames();
            try {
                return (String) array.get(0);
            } catch (JSONException e) {
                return "";
            }
        }
        return "";
    }
    public String getConcatNames(){
        try {
            if (detail.commonNames() != null) {
                JSONArray array = (JSONArray) detail().commonNames();
                String s = "";
                for (int i = 0; i < array.length() - 1; i++) {
                    s += array.get(i) + ", ";
                }
                s += array.get(array.length() - 1);
                return s;
            }
        } catch (Exception e) {
            return "";
        }
        return "";
    }
}

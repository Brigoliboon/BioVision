package com.example.biovision.data.API.Plant.model;

import java.util.ArrayList;

public record Plant(String id, String name, double probability, ArrayList<Image> similarImages, Detail detail) {
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
}

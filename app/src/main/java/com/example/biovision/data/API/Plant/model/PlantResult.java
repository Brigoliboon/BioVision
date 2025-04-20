package com.example.biovision.data.API.Plant.model;

import com.example.biovision.data.API.Plant.enums.HealthStats;

import java.util.ArrayList;

public record PlantResult(Health health,ArrayList<Disease> disease, ArrayList<Plant> plantArray){

    public Plant getClosestMatch(){
        return plantArray.get(0);
    }

    public int getDiseaseCount(){
        return disease.size();
    }
}


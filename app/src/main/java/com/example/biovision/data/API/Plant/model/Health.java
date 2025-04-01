package com.example.biovision.data.API.Plant.model;

import com.example.biovision.data.API.Plant.enums.HealthStats;

public record Health(double threshold, double percentile){

    public HealthStats getHealthStats(){
        if (percentile >= 76){
            return HealthStats.HEALTHY;
        } else if (percentile >= 51 && percentile <= 75) {
            return HealthStats.MODERATE;
        } else if (percentile >= 26 && percentile <= 50) {
            return HealthStats.POOR;
        }else {
            return HealthStats.CRITICAL;
        }
    }

}

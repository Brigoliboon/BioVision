package com.example.biovision.data.API.Plant.enums;

public enum HealthStats {
    CRITICAL("#D32F2F"),
    POOR("#F57C00"),
    MODERATE("#FBC02D"),
    HEALTHY("#388E3C");

    String hexCode;
    HealthStats(String hexCode){
        this.hexCode = hexCode;
    }

    public String getHexCode() {
        return hexCode;
    }

}

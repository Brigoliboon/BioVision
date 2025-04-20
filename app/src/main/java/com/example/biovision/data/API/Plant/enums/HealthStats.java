package com.example.biovision.data.API.Plant.enums;

public enum HealthStats {
    CRITICAL("#FF6363"),
    POOR("#FFAB76"),
    MODERATE("#FFFDA2"),
    HEALTHY("#B3E283");

    String hexCode;
    HealthStats(String hexCode){
        this.hexCode = hexCode;
    }

    public String getColor() {
        return hexCode;
    }
}

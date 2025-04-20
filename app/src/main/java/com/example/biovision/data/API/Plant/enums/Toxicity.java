package com.example.biovision.data.API.Plant.enums;

public enum Toxicity {

    TOXIC("@drawable"), NONTOXIC("@drawable");

    String drawable_asset;
    Toxicity(String drawable_asset){
        this.drawable_asset = drawable_asset;
    }
}

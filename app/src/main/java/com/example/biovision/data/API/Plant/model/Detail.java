package com.example.biovision.data.API.Plant.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public record Detail(JSONArray commonNames, Taxonomy taxonomy, String wikiURL, double gbifId, double inaturalistId,
                     String rank, Object edibleParts, Object watering,
                     String bestLightCondition, String bestSoilType, String CommonUses,
                     String culturalSignificance, String toxicity, String bestWatering){}

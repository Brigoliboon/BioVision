package com.example.biovision.data.API.Plant.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public record Detail(Object commonNames, Taxonomy taxonomy, String wikiURL, double gbifId, double inaturalistId,
                     String rank,String description, Object edibleParts, Object watering,
                     String bestLightCondition, String bestSoilType, String CommonUses,
                     String culturalSignificance, String toxicity, String bestWatering){
    /**
     * JSON Keys
     */
    public static final String COMMON_NAMES = "common_names";
    public static final String TAXONOMY = "taxonomy";
    public static final String URL = "url";
    public static final String GBIF_ID = "gbif_id";
    public static final String INATURALIST_ID = "inaturalist_id";
    public static final String RANK = "rank";
    public static final String EDIBLE_PARTS = "edible_parts";
    public static final String WATERING = "watering";
    public static final String BEST_LIGHT_CONDITION = "best_light_condition";
    public static final String BEST_SOIL_TYPE = "best_soil_type";
    public static final String COMMON_USES = "common_uses";
    public static final String CULTURAL_SIGNIFICANCE = "cultural_significance";
    public static final String TOXICITY = "toxicity";
    public static final String BEST_WATERING = "best_watering";





}

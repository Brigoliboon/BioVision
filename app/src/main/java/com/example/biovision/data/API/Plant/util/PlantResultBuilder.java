package com.example.biovision.data.API.Plant.util;

import com.example.biovision.data.API.Plant.model.Detail;
import com.example.biovision.data.API.Plant.model.Health;
import com.example.biovision.data.API.Plant.model.Image;
import com.example.biovision.data.API.Plant.model.Plant;
import com.example.biovision.data.API.Plant.model.PlantResult;
import com.example.biovision.data.API.Plant.model.Taxonomy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PlantResultBuilder {

    private static Health healthBuilder(JSONObject data) throws JSONException {
        return new Health(data.getDouble("threshold"),
                data.getDouble("probability"));
    }

    private static Taxonomy taxonomyBuilder(JSONObject data) throws JSONException {
        return new Taxonomy(
                data.getString("class"),
                data.getString("genus"),
                data.getString("order"),
                data.getString("family"),
                data.getString("phylum"),
                data.getString("kingdom")
        );
    }
    private static Detail detailBuilder(JSONObject data) throws JSONException {

        data.get("edible_parts");

        return new Detail(
                data.getJSONArray("common_names"),
                taxonomyBuilder(data.getJSONObject("taxonomy")),
                data.getString("url"),
                data.getDouble("gbif_id"),
                data.getDouble("inaturalist_id"),
                data.getString("rank"),
                data.get("edible_parts"),
                data.get("watering"),
                data.getString("best_light_condition"),
                data.getString("best_soil_type"),
                data.getString("common_uses"),
                data.getString("cultural_significance"),
                data.getString("toxicity"),
                data.getString("best_watering")
        );
    }

    private static ArrayList<Image> imageListBuilder(JSONArray data) throws JSONException {
        ArrayList<Image> imgArray = new ArrayList<>();
        for (int j = 0; j < data.length(); j++) {
            JSONObject imgData = (JSONObject) data.get(j);
            imgArray.add(new Image(
                            imgData.getString("id"),
                            imgData.getString("url"),
                            imgData.getString("license_name"),
                            imgData.getString("license_url"),
                            imgData.getString("citation"),
                            imgData.getDouble("similarity"),
                            imgData.getString("url_small")
                    )
            );
        }
        return imgArray;
    }

    public static PlantResult plantResultBuilder(JSONObject data) throws JSONException {
        JSONObject result = data.getJSONObject("result");
        JSONObject searchClassification = result
                .getJSONObject("classification");
        JSONArray searchResults = searchClassification.getJSONArray("suggestions");

        ArrayList<Plant> plantArr= new ArrayList<>();
        for (int i=0; i < searchResults.length(); i++){
            JSONObject temp = (JSONObject) searchResults.get(i);
            JSONArray images = temp.getJSONArray("similar_images");

            Plant plant = new Plant(temp.getString("id"), temp.getString("name"),
                    temp.getDouble("probability"),
                    imageListBuilder(images),
                    detailBuilder(temp.getJSONObject("details"))
                );
            plantArr.add(plant);
        }

        return new PlantResult(
                healthBuilder(result.getJSONObject("is_healthy")),
                plantArr
        );
    }
}

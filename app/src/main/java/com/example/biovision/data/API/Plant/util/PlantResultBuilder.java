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
                data.getString(Taxonomy.CLASS),
                data.getString(Taxonomy.GENUS),
                data.getString(Taxonomy.ORDER),
                data.getString(Taxonomy.FAMILY),
                data.getString(Taxonomy.PHYLUM),
                data.getString(Taxonomy.KINGDOM)
        );
    }
    private static Detail detailBuilder(JSONObject data) throws JSONException {

        data.get("edible_parts");

        String desc;
        try {
            desc = data.getJSONObject("description").getString("value");

        } catch (JSONException e) {
            desc = "";
        }
        return new Detail(
                data.get(Detail.COMMON_NAMES),
                taxonomyBuilder(data.getJSONObject(Detail.TAXONOMY)),
                data.getString(Detail.URL),
                data.getDouble(Detail.GBIF_ID),
                data.getDouble(Detail.INATURALIST_ID),
                data.getString(Detail.RANK),
                desc,
                data.get(Detail.EDIBLE_PARTS),
                data.get(Detail.WATERING),
                data.getString(Detail.BEST_LIGHT_CONDITION),
                data.getString(Detail.BEST_SOIL_TYPE),
                data.getString(Detail.COMMON_USES),
                data.getString(Detail.CULTURAL_SIGNIFICANCE),
                data.getString(Detail.TOXICITY),
                data.getString(Detail.BEST_WATERING)
        );
    }

    private static ArrayList<Image> imageListBuilder(JSONArray data) throws JSONException {
        ArrayList<Image> imgArray = new ArrayList<>();
        for (int j = 0; j < data.length(); j++) {
            JSONObject imgData = (JSONObject) data.get(j);
            imgArray.add(new Image(
                            imgData.getString(Image.ID),
                            imgData.getString(Image.URL),
                            imgData.getString(Image.LICENSE_NAME),
                            imgData.getString(Image.LICENSE_URL),
                            imgData.getString(Image.CITATION),
                            imgData.getDouble(Image.SIMILARITY),
                            imgData.getString(Image.SMALL_IMG_LINK)
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

            Plant plant = new Plant(temp.getString(Plant.ID), temp.getString(Plant.NAME),
                    temp.getDouble(Plant.PROBABILITY),
                    imageListBuilder(images),
                    detailBuilder(temp.getJSONObject(Plant.DETAILS))
                );
            plantArr.add(plant);
        }

        return new PlantResult(
                healthBuilder(result.getJSONObject("is_healthy")),
                plantArr
        );
    }
}

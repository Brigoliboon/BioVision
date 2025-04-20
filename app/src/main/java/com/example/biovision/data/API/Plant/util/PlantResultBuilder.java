package com.example.biovision.data.API.Plant.util;

import android.support.annotation.Nullable;

import com.example.biovision.data.API.Plant.model.Disease;
import com.example.biovision.data.API.Plant.model.PlantDetail;
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
        return new Health(data.getDouble(Health.THRESHOLD),
                data.getDouble(Health.PROBABILITY));
    }

    private static ArrayList<Disease> diseaseListBuilder(@Nullable JSONObject data) throws JSONException {
        ArrayList<Disease> diseaseArr = new ArrayList<>();
        if (!data.isNull("disease")){
            JSONArray arr = data.getJSONObject("disease").getJSONArray("suggestions");
            for (int i=0; i < arr.length(); i++){
                JSONObject diseaseData = arr.getJSONObject(i);

                diseaseArr.add(new Disease(
                        diseaseData.getString("id"),
                        diseaseData.getString("name"),
                        diseaseData.getDouble("probability"),
                        imageListBuilder(diseaseData.getJSONArray("similar_images")),
                        diseaseData.getJSONObject("details").getString("description"),
                        diseaseData.getJSONObject("details").get("common_names")
                ));
            }
        }
        return diseaseArr;
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
    private static PlantDetail detailBuilder(JSONObject data) throws JSONException {

        data.get("edible_parts");

        String desc;
        try {
            desc = data.getJSONObject("description").getString("value");

        } catch (JSONException e) {
            desc = "";
        }
        return new PlantDetail(
                data.get(PlantDetail.COMMON_NAMES),
                taxonomyBuilder(data.getJSONObject(PlantDetail.TAXONOMY)),
                data.getString(PlantDetail.URL),
                data.getDouble(PlantDetail.GBIF_ID),
                data.getDouble(PlantDetail.INATURALIST_ID),
                data.getString(PlantDetail.RANK),
                desc,
                data.get(PlantDetail.EDIBLE_PARTS),
                data.get(PlantDetail.WATERING),
                data.getString(PlantDetail.BEST_LIGHT_CONDITION),
                data.getString(PlantDetail.BEST_SOIL_TYPE),
                data.getString(PlantDetail.COMMON_USES),
                data.getString(PlantDetail.CULTURAL_SIGNIFICANCE),
                data.getString(PlantDetail.TOXICITY),
                data.getString(PlantDetail.BEST_WATERING)
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
                diseaseListBuilder(result),
                plantArr
        );
    }
}

package com.example.biovision.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.biovision.core.Result;
import com.example.biovision.data.API.Plant.PlantRequest;
import com.example.biovision.data.API.Plant.model.Plant;
import com.example.biovision.data.API.Plant.model.PlantResult;
import com.example.biovision.data.API.Plant.util.PlantResultBuilder;
import com.example.biovision.data.API.Request.util.JSONParser;
import android.view.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.ResponseBody;

public class PlantViewModel extends ViewModel {
    private MutableLiveData<Result<PlantResult>> result = new MutableLiveData<>();
    private MutableLiveData<Plant> chosenPlant = new MutableLiveData<>();
    public LiveData<Result<PlantResult>> getResult() {
        return result;
    }
    public LiveData<Plant> getChosenPlant(){
        return chosenPlant;
    }

    public void setChosenPlant(Plant plant){
        chosenPlant.setValue(plant);
    }

    public void plantSearch(String q){
        // TODO: Next Release
    }
    public void plantScan(JSONObject payload, boolean test){
        // TODO: IMPORTANT! Exposed secret
        PlantRequest plantAPI = new PlantRequest("qzG7VtS3JdK9pL6Rwx2YhQ8Zb5No3KfE4M1sTzAqB7FvXjC8hL");
        result.postValue(new Result.Loading<>());
        if (!test) {
            new Thread(() -> {
                Result<PlantResult> response = plantAPI.plantScan(payload);
                result.postValue(response);
            }).start();
        }

        /**
         * Preloaded Response result
         * after testing, change the minimum sdk to 26
         */
//        InputStream inputStream = context.getResources().openRawResource(R.raw.plant_data); // Direct access to raw resource
//        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//        StringBuilder jsonData = new StringBuilder();
//        String line;
//        while ((line = reader.readLine()) != null) {
//            jsonData.append(line);
//        }
//        reader.close();
//        inputStream.close();
        String rawData = "{\n" +
                "  \"access_token\": \"Du3LBGJIHV0RbhC\",\n" +
                "  \"model_version\": \"plant_id:5.0.0\",\n" +
                "  \"custom_id\": null,\n" +
                "  \"input\": {\n" +
                "    \"latitude\": 8.4770816,\n" +
                "    \"longitude\": 124.6461952,\n" +
                "    \"similar_images\": true,\n" +
                "    \"health\": \"all\",\n" +
                "    \"images\": [\n" +
                "      \"https://plant.id/media/imgs/b0137953786e4d089aa0a3644a8dfea2.jpg\"\n" +
                "    ],\n" +
                "    \"datetime\": \"2025-03-31\"\n" +
                "  },\n" +
                "  \"result\": {\n" +
                "    \"classification\": {\n" +
                "      \"suggestions\": [\n" +
                "        {\n" +
                "          \"id\": \"bf10d7fdefbe4b9c\",\n" +
                "          \"name\": \"Argyranthemum frutescens\",\n" +
                "          \"probability\": 0.1656,\n" +
                "          \"similar_images\": [\n" +
                "            {\n" +
                "              \"id\": \"2faf820f0c561d3fe5c5b5803b40d6004db84980\",\n" +
                "              \"url\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/2fa/f820f0c561d3fe5c5b5803b40d6004db84980.jpeg\",\n" +
                "              \"license_name\": \"CC BY-SA 4.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by-sa/4.0/\",\n" +
                "              \"citation\":  \"Tony Wills\",\n" +
                "              \"similarity\": 0.667,\n" +
                "              \"url_small\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/2fa/f820f0c561d3fe5c5b5803b40d6004db84980.small.jpeg\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"id\": \"75afda794fedd09e0eeab9e6d7039c5a18cdc93c\",\n" +
                "              \"url\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/75a/fda794fedd09e0eeab9e6d7039c5a18cdc93c.jpg\",\n" +
                "              \"license_name\": \"CC BY 4.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by/4.0/\",\n" +
                "              \"citation\": \"Line Juul Nielsen\",\n" +
                "              \"similarity\": 0.651,\n" +
                "              \"url_small\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/75a/fda794fedd09e0eeab9e6d7039c5a18cdc93c.small.jpg\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"details\": {\n" +
                "            \"common_names\": [\n" +
                "              \"marguerite\",\n" +
                "              \"Marguerite daisy\",\n" +
                "              \"Federation daisy\"\n" +
                "            ],\n" +
                "            \"taxonomy\": {\n" +
                "              \"class\": \"Magnoliopsida\",\n" +
                "              \"genus\": \"Argyranthemum\",\n" +
                "              \"order\": \"Asterales\",\n" +
                "              \"family\": \"Asteraceae\",\n" +
                "              \"phylum\": \"Tracheophyta\",\n" +
                "              \"kingdom\": \"Plantae\"\n" +
                "            },\n" +
                "            \"url\": \"https://en.wikipedia.org/wiki/Argyranthemum_frutescens\",\n" +
                "            \"gbif_id\": 3104491,\n" +
                "            \"inaturalist_id\": 181635,\n" +
                "            \"rank\": \"species\",\n" +
                "            \"description\": {\n" +
                "              \"value\": \"Argyranthemum frutescens, known as Paris daisy, marguerite or marguerite daisy, is a perennial plant known for its flowers. It is native to the Canary Islands (part of Spain). Hybrids derived from this species (garden marguerites) are widely cultivated as ornamental plants in private gardens and public parks in many countries, and have naturalized in Italy and southern California. There are many cultivars, but the most common has white petals.\",\n" +
                "              \"citation\": \"https://en.wikipedia.org/wiki/Argyranthemum_frutescens\",\n" +
                "              \"license_name\": \"CC BY-SA 3.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by-sa/3.0/\"\n" +
                "            },\n" +
                "            \"synonyms\": [\n" +
                "              \"Anthemis frutescens\",\n" +
                "              \"Argyranthemum frutescens var. frutescens\",\n" +
                "              \"Chrysanthemum carnosulum\",\n" +
                "              \"Chrysanthemum foliosum\",\n" +
                "              \"Chrysanthemum frutescens\",\n" +
                "              \"Chrysanthemum frutescens var. frutescens\",\n" +
                "              \"Phymaspermum carnosulum\",\n" +
                "              \"Pyrethrum frutescens\"\n" +
                "            ],\n" +
                "            \"image\": {\n" +
                "              \"value\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/knowledge_base/wikidata/0ab/0aba662bfc5fbec60f99e19185eb52225d6e812b.jpg\",\n" +
                "              \"citation\": \"//commons.wikimedia.org/wiki/User:Alvesgaspar\",\n" +
                "              \"license_name\": \"CC BY-SA 3.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by-sa/3.0/\"\n" +
                "            },\n" +
                "            \"edible_parts\": null,\n" +
                "            \"watering\": {\n" +
                "              \"max\": 2,\n" +
                "              \"min\": 2\n" +
                "            },\n" +
                "            \"best_light_condition\": \"This plant thrives in full sun, needing at least six hours of direct sunlight each day. It can tolerate partial shade, but too little light can result in fewer blooms and leggy growth. If you're growing it indoors, place it near a south-facing window where it can get plenty of light. In areas with very hot summers, some afternoon shade can help prevent the leaves from scorching.\",\n" +
                "            \"best_soil_type\": \"For optimal growth, this plant prefers well-draining soil. A mix that includes sand, loam, and organic matter works well. Good drainage is essential to prevent water from pooling around the roots, which can cause rot. If you're planting in a container, make sure it has drainage holes and use a high-quality potting mix designed for flowering plants.\",\n" +
                "            \"common_uses\": \"This plant is commonly used as an ornamental in gardens, borders, and containers. Its vibrant flowers make it a popular choice for adding color to outdoor spaces. It's also used in hanging baskets and window boxes. Some people grow it indoors as a houseplant, especially in areas where outdoor gardening is limited by climate. Its versatility and ease of care make it a popular choice for both novice and experienced gardeners.\",\n" +
                "            \"cultural_significance\": \"In various cultures, this plant is often associated with cheerfulness and positivity due to its bright, daisy-like flowers. It's commonly used in gardens and landscapes to add a splash of color. In some regions, it's also used in traditional floral arrangements and ceremonies. Its long blooming season makes it a favorite for both public and private gardens.\",\n" +
                "            \"toxicity\": \"This plant is generally considered non-toxic to both humans and animals. However, it's always a good idea to discourage pets and children from chewing on any plant material. While it doesn't pose a significant risk, ingesting large amounts of any plant can cause digestive upset. If you have concerns, consult with a veterinarian or a medical professional.\",\n" +
                "            \"best_watering\": \"Watering this plant properly is crucial for its health. It prefers consistently moist soil but doesn't like to sit in water. Overwatering can lead to root rot, so it's important to let the top inch of soil dry out between waterings. During the hotter months, you might need to water more frequently, while in cooler weather, less frequent watering is usually sufficient. Always check the soil moisture before watering to avoid any issues.\",\n" +
                "            \"language\": \"en\",\n" +
                "            \"entity_id\": \"bf10d7fdefbe4b9c\"\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": \"870270493d867ba5\",\n" +
                "          \"name\": \"Anthemis cotula\",\n" +
                "          \"probability\": 0.1477,\n" +
                "          \"similar_images\": [\n" +
                "            {\n" +
                "              \"id\": \"a486d3179c76e433c904ce32b9a5e7e62ebcfab2\",\n" +
                "              \"url\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/a48/6d3179c76e433c904ce32b9a5e7e62ebcfab2.jpeg\",\n" +
                "              \"license_name\": \"CC BY 4.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by/4.0/\",\n" +
                "              \"citation\": \"Severin Ronald\",\n" +
                "              \"similarity\": 0.733,\n" +
                "              \"url_small\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/a48/6d3179c76e433c904ce32b9a5e7e62ebcfab2.small.jpeg\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"id\": \"33e87c503e4d3bc69aabf9e2fb8119434cc0886a\",\n" +
                "              \"url\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/33e/87c503e4d3bc69aabf9e2fb8119434cc0886a.jpeg\",\n" +
                "              \"license_name\": \"CC BY 4.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by/4.0/\",\n" +
                "              \"citation\": \"Millie Basden\",\n" +
                "              \"similarity\": 0.726,\n" +
                "              \"url_small\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/33e/87c503e4d3bc69aabf9e2fb8119434cc0886a.small.jpeg\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"details\": {\n" +
                "            \"common_names\": [\n" +
                "              \"stinking chamomile\",\n" +
                "              \"Mayweed\",\n" +
                "              \"Stinking mayweed\",\n" +
                "              \"Common Dogfennel\",\n" +
                "              \"Dogfennel\",\n" +
                "              \"Foetid Chamomile\",\n" +
                "              \"Poison Daisy\"\n" +
                "            ],\n" +
                "            \"taxonomy\": {\n" +
                "              \"class\": \"Magnoliopsida\",\n" +
                "              \"genus\": \"Anthemis\",\n" +
                "              \"order\": \"Asterales\",\n" +
                "              \"family\": \"Asteraceae\",\n" +
                "              \"phylum\": \"Tracheophyta\",\n" +
                "              \"kingdom\": \"Plantae\"\n" +
                "            },\n" +
                "            \"url\": \"https://en.wikipedia.org/wiki/Anthemis_cotula\",\n" +
                "            \"gbif_id\": 8035698,\n" +
                "            \"inaturalist_id\": 52841,\n" +
                "            \"rank\": \"species\",\n" +
                "            \"description\": {\n" +
                "              \"value\": \"Anthemis cotula, also known as stinking chamomile, or mayweed, is a flowering annual plant with a noticeable and strong odor. The odor is often considered unpleasant, and it is from this that it gains the common epithet \\\"stinking\\\". In pre-colonial times, its distribution was limited to the Old Continent and Africa; though it was established in most of Europe, it was not present in Finland, Ireland, or the northernmost reaches of Scotland, in spite of the fact that these countries feature climatic regions favorable to this plant and are in proximity to countries where the species is native, such as Russia, Estonia, Lithuania and England. It has successfully migrated to the New Europes where it can be found growing in meadows, alongside roads, and in fields.The name \\\"cotula\\\" is the Latin form of κοτύλη kotylē,  the Greek word for \\\"small cup\\\", describing the shape of the flowers; it was assigned by Carl Linnaeus in his work Species Plantarum in 1753.Anthemis cotula is also known by a wide variety of other names, including mather, dog- or hog's-fennel, dog-finkle, dog-daisy, pig-sty-daisy, chigger-weed, mayweed, Johnnyweed, maroute, Maruta cotula, Cotula Maruta foetida, Manzanilla loca, wild chamomile, Camomille puante. Foetid Chamomile, maithes, maithen, mathor mayweed chamomile, camomille des chiens, camomille puante, stinkende Hundskamille, camomila-de-cachorro, macéla-fétida, and manzanilla hedionda.\",\n" +
                "              \"citation\": \"https://en.wikipedia.org/wiki/Anthemis_cotula\",\n" +
                "              \"license_name\": \"CC BY-SA 3.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by-sa/3.0/\"\n" +
                "            },\n" +
                "            \"synonyms\": [\n" +
                "              \"Anthemis abyssinica\",\n" +
                "              \"Anthemis coronata\",\n" +
                "              \"Anthemis cotula subsp. cotula\",\n" +
                "              \"Anthemis cotula subsp. psorosperma\",\n" +
                "              \"Anthemis cotula var. coronata\",\n" +
                "              \"Anthemis cotula var. cotula\",\n" +
                "              \"Anthemis cotula var. lithuanica\",\n" +
                "              \"Anthemis cotula var. vectensis\",\n" +
                "              \"Anthemis cotula-foetida\",\n" +
                "              \"Anthemis cotuloides\",\n" +
                "              \"Anthemis foetida\",\n" +
                "              \"Anthemis foetida var. foetida\",\n" +
                "              \"Anthemis foetida var. vectensis\",\n" +
                "              \"Anthemis psorosperma\",\n" +
                "              \"Anthemis ramosa\",\n" +
                "              \"Anthemis sulphurea\",\n" +
                "              \"Chamaemelum cotula\",\n" +
                "              \"Chamaemelum foetidum\",\n" +
                "              \"Maruta cotula\",\n" +
                "              \"Maruta cotula var. cotula\",\n" +
                "              \"Maruta foetida\",\n" +
                "              \"Maruta vulgaris\",\n" +
                "              \"Matricaria cotula\"\n" +
                "            ],\n" +
                "            \"image\": {\n" +
                "              \"value\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/knowledge_base/wikidata/2a2/2a2bc03b3facac8b16658a33dfd6115b7efa0fcb.png\",\n" +
                "              \"citation\": null,\n" +
                "              \"license_name\": \"CC0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/publicdomain/zero/1.0/\"\n" +
                "            },\n" +
                "            \"edible_parts\": [\n" +
                "              \"leaves\"\n" +
                "            ],\n" +
                "            \"watering\": null,\n" +
                "            \"best_light_condition\": \"This plant thrives in full sun, requiring at least six hours of direct sunlight each day. It can tolerate partial shade, but too little light can result in leggy growth and fewer flowers. If growing indoors, place it near a south-facing window to ensure it gets enough light. In areas with intense afternoon sun, some light shade can help prevent scorching.\",\n" +
                "            \"best_soil_type\": \"The best soil for this plant is well-draining and slightly sandy. It prefers a neutral to slightly acidic pH but can tolerate a range of soil types as long as they are not too heavy or clayey. Adding organic matter like compost can improve soil structure and fertility, promoting healthier growth. Avoid soils that retain too much moisture, as this can lead to root problems.\",\n" +
                "            \"common_uses\": \"Common uses for this plant include ornamental purposes in gardens and landscapes due to its attractive flowers. It is sometimes used in dried flower arrangements and potpourri. Historically, it has been used in traditional medicine, although caution is advised due to its toxic properties. Some people also use it as a natural insect repellent in gardens.\",\n" +
                "            \"cultural_significance\": \"Culturally, this plant has been used in various traditional practices and folklore. In some cultures, it is believed to have medicinal properties and has been used in herbal remedies. However, due to its toxicity, its use in modern herbal medicine is limited. It has also been associated with certain rituals and symbolic meanings in different regions.\",\n" +
                "            \"toxicity\": \"This plant is toxic to both humans and animals if ingested. It can cause skin irritation upon contact, so it's advisable to wear gloves when handling it. Ingesting any part of the plant can lead to symptoms such as nausea, vomiting, and diarrhea. Keep it out of reach of pets and children to prevent accidental ingestion.\",\n" +
                "            \"best_watering\": \"For optimal watering, it's important to keep the soil consistently moist but not waterlogged. Water the plant deeply once the top inch of soil feels dry to the touch. Overwatering can lead to root rot, so ensure good drainage. During hot, dry periods, you may need to water more frequently, but always check the soil moisture before adding more water.\",\n" +
                "            \"language\": \"en\",\n" +
                "            \"entity_id\": \"870270493d867ba5\"\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": \"b59dca487f2b3bcc\",\n" +
                "          \"name\": \"Tripleurospermum inodorum\",\n" +
                "          \"probability\": 0.1054,\n" +
                "          \"similar_images\": [\n" +
                "            {\n" +
                "              \"id\": \"17cdd3291d0b2b30a542635343306018237c04a5\",\n" +
                "              \"url\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/17c/dd3291d0b2b30a542635343306018237c04a5.jpeg\",\n" +
                "              \"license_name\": \"CC BY 4.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by/4.0/\",\n" +
                "              \"citation\": \"Yurii Basov\",\n" +
                "              \"similarity\": 0.705,\n" +
                "              \"url_small\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/17c/dd3291d0b2b30a542635343306018237c04a5.small.jpeg\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"id\": \"22765557e931615b0abecc88f8564e2007667bfa\",\n" +
                "              \"url\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/227/65557e931615b0abecc88f8564e2007667bfa.jpeg\",\n" +
                "              \"license_name\": \"CC0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/publicdomain/zero/1.0/\",\n" +
                "              \"citation\": \"bulatik\",\n" +
                "              \"similarity\": 0.662,\n" +
                "              \"url_small\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/227/65557e931615b0abecc88f8564e2007667bfa.small.jpeg\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"details\": {\n" +
                "            \"common_names\": [\n" +
                "              \"scentless mayweed\",\n" +
                "              \"false chamomile\",\n" +
                "              \"Scentless False Mayweed\",\n" +
                "              \"Scentless Chamomile\"\n" +
                "            ],\n" +
                "            \"taxonomy\": {\n" +
                "              \"class\": \"Magnoliopsida\",\n" +
                "              \"genus\": \"Tripleurospermum\",\n" +
                "              \"order\": \"Asterales\",\n" +
                "              \"family\": \"Asteraceae\",\n" +
                "              \"phylum\": \"Tracheophyta\",\n" +
                "              \"kingdom\": \"Plantae\"\n" +
                "            },\n" +
                "            \"url\": \"https://en.wikipedia.org/wiki/Tripleurospermum_inodorum\",\n" +
                "            \"gbif_id\": 3104179,\n" +
                "            \"inaturalist_id\": 79441,\n" +
                "            \"rank\": \"species\",\n" +
                "            \"description\": {\n" +
                "              \"value\": \"Tripleurospermum inodorum, common names scentless false mayweed, scentless mayweed, scentless chamomile, wild chamomile, mayweed, false chamomile, and Baldr's brow, is the type species of Tripleurospermum. This plant is native to Eurasia and North Africa, and introduced to North America, where it is commonly found in fields, fallow land and gardens.Historically included the genus Matricaria, Tripleurospermum inodorum has been the subject of some controversy, with many revisions in recent years. The Flora Europaea uses Matricaria perforata for this species. Synonyms/other scientific names include Tripleurospermum perforatum (Mérat) Lainz, Tripleurospermum maritimum subsp. inodorum.\",\n" +
                "              \"citation\": \"https://en.wikipedia.org/wiki/Tripleurospermum_inodorum\",\n" +
                "              \"license_name\": \"CC BY-SA 3.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by-sa/3.0/\"\n" +
                "            },\n" +
                "            \"synonyms\": [\n" +
                "              \"Anthemis perforata\",\n" +
                "              \"Anthemis vulgaris\",\n" +
                "              \"Camomilla inodora\",\n" +
                "              \"Chamaemelum inodorum\",\n" +
                "              \"Chamaemelum inodorum var. inodorum\",\n" +
                "              \"Chamomilla inodora\",\n" +
                "              \"Chamomilla praecox\",\n" +
                "              \"Chrysanthemum grandiflorum\",\n" +
                "              \"Chrysanthemum inodorum\",\n" +
                "              \"Chrysanthemum inodorum liguliflorum\",\n" +
                "              \"Chrysanthemum maritimum subsp. inodorum\",\n" +
                "              \"Chrysanthemum maritimum var. agreste\",\n" +
                "              \"Chrysanthemum maritimum var. inodorum\",\n" +
                "              \"Dibothrospermum agreste\",\n" +
                "              \"Dibothrospermum pusillum\",\n" +
                "              \"Gastrostylum praecox\",\n" +
                "              \"Matricaria elegans\",\n" +
                "              \"Matricaria inodora\",\n" +
                "              \"Matricaria inodora agrestis\",\n" +
                "              \"Matricaria inodora biennis\",\n" +
                "              \"Matricaria inodora f. agrestis\",\n" +
                "              \"Matricaria inodora inodora\",\n" +
                "              \"Matricaria inodora raii\",\n" +
                "              \"Matricaria inodora tubulosa\",\n" +
                "              \"Matricaria inodora var. agrestis\",\n" +
                "              \"Matricaria inodora var. grandiflora\",\n" +
                "              \"Matricaria inodora var. inodora\",\n" +
                "              \"Matricaria inodora var. pusilla\",\n" +
                "              \"Matricaria maritima\",\n" +
                "              \"Matricaria maritima subsp. agrestis\",\n" +
                "              \"Matricaria maritima subsp. inodora\",\n" +
                "              \"Matricaria maritima var. agrestis\",\n" +
                "              \"Matricaria maritima var. inodora\",\n" +
                "              \"Matricaria perforata\",\n" +
                "              \"Matricaria pumila\",\n" +
                "              \"Matricaria salina\",\n" +
                "              \"Matricaria suaveolens\",\n" +
                "              \"Matricaria suaveolens suaveolens\",\n" +
                "              \"Metricaria perforata\",\n" +
                "              \"Pyrethrum inodorum\",\n" +
                "              \"Pyrethrum inodorum var. inodorum\",\n" +
                "              \"Pyrethrum maritimum var. salinum\",\n" +
                "              \"Rhytidospermum inodorum\",\n" +
                "              \"Trallesia matricarioides\",\n" +
                "              \"Tripleurospermum bienne\",\n" +
                "              \"Tripleurospermum maritimum subsp. inodorum\",\n" +
                "              \"Tripleurospermum maritimum var. agreste\",\n" +
                "              \"Tripleurospermum maritimum var. pusillum\",\n" +
                "              \"Tripleurospermum perforatum\",\n" +
                "              \"Tripleurospermum praecox\",\n" +
                "              \"Tripleurospermuminodorum\"\n" +
                "            ],\n" +
                "            \"image\": {\n" +
                "              \"value\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/knowledge_base/wikidata/dcb/dcbbbc626edcb5dffc70d65b36abe11c7a64c0cf.jpg\",\n" +
                "              \"citation\": null,\n" +
                "              \"license_name\": \"CC0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/publicdomain/zero/1.0/\"\n" +
                "            },\n" +
                "            \"edible_parts\": null,\n" +
                "            \"watering\": null,\n" +
                "            \"best_light_condition\": \"This plant prefers full sun to partial shade. It grows best in areas where it can receive at least six hours of direct sunlight each day. While it can tolerate some shade, too much can result in leggy growth and fewer flowers. Positioning it in a sunny spot will encourage robust growth and abundant blooming.\",\n" +
                "            \"best_soil_type\": \"The ideal soil for this plant is well-draining and moderately fertile. It can grow in a variety of soil types, including sandy, loamy, and clay soils, as long as there is good drainage. Adding organic matter like compost can improve soil fertility and structure, promoting healthier growth.\",\n" +
                "            \"common_uses\": \"Common uses for this plant are mostly ornamental. It is often found in wildflower mixes and used in naturalized garden settings. Its daisy-like flowers can add a cheerful touch to garden borders and meadows. Additionally, it can be used in erosion control due to its ability to establish quickly and cover ground effectively.\",\n" +
                "            \"cultural_significance\": \"Culturally, this plant does not have significant historical or symbolic importance. It is often considered a common wildflower or weed in many regions. Its presence in fields and meadows is more a testament to its hardiness and adaptability rather than any deep cultural significance.\",\n" +
                "            \"toxicity\": \"This plant is generally non-toxic to both humans and animals. It does not contain any known harmful substances that could cause poisoning if ingested. However, as with any plant, it's always best to prevent pets and children from eating it to avoid any potential digestive upset.\",\n" +
                "            \"best_watering\": \"Watering this plant requires a balanced approach. It thrives in moderately moist soil but can tolerate short periods of drought. Overwatering can lead to root rot, so it's important to let the soil dry out slightly between waterings. During hot, dry spells, more frequent watering may be necessary, but always ensure good drainage to prevent waterlogging.\",\n" +
                "            \"language\": \"en\",\n" +
                "            \"entity_id\": \"b59dca487f2b3bcc\"\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": \"5699212e5346abf8\",\n" +
                "          \"name\": \"Leucanthemum vulgare\",\n" +
                "          \"probability\": 0.087,\n" +
                "          \"similar_images\": [\n" +
                "            {\n" +
                "              \"id\": \"859720588763e47c06417b018e6ff0b72a33031c\",\n" +
                "              \"url\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/859/720588763e47c06417b018e6ff0b72a33031c.jpeg\",\n" +
                "              \"license_name\": \"CC BY 4.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by/4.0/\",\n" +
                "              \"citation\": \"María Regina Silva\",\n" +
                "              \"similarity\": 0.702,\n" +
                "              \"url_small\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/859/720588763e47c06417b018e6ff0b72a33031c.small.jpeg\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"id\": \"8e5541e70b4702723a62fd9754cdd983610433c7\",\n" +
                "              \"url\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/8e5/541e70b4702723a62fd9754cdd983610433c7.jpeg\",\n" +
                "              \"license_name\": \"CC BY-NC-SA 4.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by-nc-sa/4.0/\",\n" +
                "              \"citation\": \"FlowerChecker s.r.o.\",\n" +
                "              \"similarity\": 0.678,\n" +
                "              \"url_small\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/8e5/541e70b4702723a62fd9754cdd983610433c7.small.jpeg\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"details\": {\n" +
                "            \"common_names\": [\n" +
                "              \"Oxeye Daisy\",\n" +
                "              \"Dog Daisy\",\n" +
                "              \"Garden Chrysanthemum\",\n" +
                "              \"White Daisy\",\n" +
                "              \"White Weed\",\n" +
                "              \"Marguerite Daisy\"\n" +
                "            ],\n" +
                "            \"taxonomy\": {\n" +
                "              \"class\": \"Magnoliopsida\",\n" +
                "              \"genus\": \"Leucanthemum\",\n" +
                "              \"order\": \"Asterales\",\n" +
                "              \"family\": \"Asteraceae\",\n" +
                "              \"phylum\": \"Tracheophyta\",\n" +
                "              \"kingdom\": \"Plantae\"\n" +
                "            },\n" +
                "            \"url\": \"https://en.wikipedia.org/wiki/Leucanthemum_vulgare\",\n" +
                "            \"gbif_id\": 8848598,\n" +
                "            \"inaturalist_id\": 56057,\n" +
                "            \"rank\": \"species\",\n" +
                "            \"description\": {\n" +
                "              \"value\": \"Leucanthemum vulgare, commonly known as the ox-eye daisy, oxeye daisy, dog daisy, marguerite (French: Marguerite commune, \\\"common marguerite\\\") and other common names, is a widespread flowering plant native to Europe and the temperate regions of Asia, and an introduced plant to North America, Australia and New Zealand.\",\n" +
                "              \"citation\": \"https://en.wikipedia.org/wiki/Leucanthemum_vulgare\",\n" +
                "              \"license_name\": \"CC BY-SA 3.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by-sa/3.0/\"\n" +
                "            },\n" +
                "            \"synonyms\": [\n" +
                "              \"Chamaemelum leucanthemum\",\n" +
                "              \"Chrysanthemum ircutianum\",\n" +
                "              \"Chrysanthemum leucanthemum\",\n" +
                "              \"Chrysanthemum leucanthemum leucanthemum\",\n" +
                "              \"Chrysanthemum leucanthemum subsp. lanceolatum\",\n" +
                "              \"Chrysanthemum leucanthemum subsp. leucanthemum\",\n" +
                "              \"Chrysanthemum leucanthemum subsp. subpinnatifidum\",\n" +
                "              \"Chrysanthemum leucanthemum var. leucanthemum\",\n" +
                "              \"Chrysanthemum montanum subsp. heterophyllum\",\n" +
                "              \"Chrysanthemum praecox\",\n" +
                "              \"Chrysanthemum vulgare\",\n" +
                "              \"Chrysanthemum vulgare subsp. vulgare\",\n" +
                "              \"Chrysanthemum vulgare var. vulgare\",\n" +
                "              \"Leucanthemum atratum subsp. heterophyllum\",\n" +
                "              \"Leucanthemum ceratophylloides var. barrelieri\",\n" +
                "              \"Leucanthemum leucanthemum\",\n" +
                "              \"Leucanthemum praecox\",\n" +
                "              \"Leucanthemum vulagre\",\n" +
                "              \"Leucanthemum vulgare subsp. incisum\",\n" +
                "              \"Leucanthemum vulgare subsp. pinnatifidum\",\n" +
                "              \"Leucanthemum vulgare subsp. praecox\",\n" +
                "              \"Leucanthemum vulgare var. alpicola\",\n" +
                "              \"Leucanthemum vulgare var. pyrenaicum\",\n" +
                "              \"Leucanthemum vulgare var. vulgare\",\n" +
                "              \"Matricaria leucanthemum\",\n" +
                "              \"Pontia vulgaris\",\n" +
                "              \"Pyrethrum leucanthemum\",\n" +
                "              \"Tanacetum leucanthemum\"\n" +
                "            ],\n" +
                "            \"image\": {\n" +
                "              \"value\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/knowledge_base/wikidata/beb/beb81f88c35d2c33960e4ec3f9a730d9b8a8c896.jpg\",\n" +
                "              \"citation\": null,\n" +
                "              \"license_name\": \"CC0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/publicdomain/zero/1.0/\"\n" +
                "            },\n" +
                "            \"edible_parts\": [\n" +
                "              \"leaves\",\n" +
                "              \"root\",\n" +
                "              \"shoots\"\n" +
                "            ],\n" +
                "            \"watering\": {\n" +
                "              \"max\": 2,\n" +
                "              \"min\": 1\n" +
                "            },\n" +
                "            \"best_light_condition\": \"This plant thrives in full sun, requiring at least six hours of direct sunlight each day. It can tolerate partial shade, but too much shade can result in fewer blooms and leggy growth. If grown indoors, place it near a south-facing window to ensure it gets enough light. Adequate sunlight is crucial for healthy growth and abundant flowering.\",\n" +
                "            \"best_soil_type\": \"The plant prefers well-draining soil with a neutral to slightly acidic pH. Sandy loam or loamy soil types are ideal, as they provide good drainage while retaining some moisture. Avoid heavy clay soils that can retain too much water and lead to root rot. Adding organic matter like compost can improve soil structure and fertility.\",\n" +
                "            \"common_uses\": \"This plant is commonly used for ornamental purposes in gardens and landscapes due to its attractive, daisy-like flowers. It is also used in floral arrangements and bouquets. Some people use it in traditional medicine for its purported health benefits, although scientific evidence is limited. Additionally, it can be used in natural pest control, as it attracts beneficial insects like bees and butterflies.\",\n" +
                "            \"cultural_significance\": \"In various cultures, this plant holds symbolic meanings such as purity, innocence, and simplicity. It is often associated with childhood and is a common motif in folklore and literature. In some traditions, it is used in wedding bouquets and floral arrangements to symbolize new beginnings and true love. Its cheerful appearance makes it a popular choice for gardens and public spaces.\",\n" +
                "            \"toxicity\": \"This plant is generally non-toxic to humans and animals. However, some people may experience mild skin irritation or allergic reactions upon contact with the plant. It's always a good idea to wear gloves when handling it, especially if you have sensitive skin. Pets are usually safe around this plant, but it's best to prevent them from chewing on it to avoid any potential digestive upset.\",\n" +
                "            \"best_watering\": \"For optimal watering, it's important to keep the soil consistently moist but not waterlogged. Water the plant deeply once a week, allowing the water to reach the roots. During hot, dry periods, you may need to water more frequently. Avoid overhead watering to prevent fungal diseases; instead, water at the base of the plant. Mulching can help retain soil moisture and reduce the need for frequent watering.\",\n" +
                "            \"language\": \"en\",\n" +
                "            \"entity_id\": \"5699212e5346abf8\"\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": \"70d82558e93e28f7\",\n" +
                "          \"name\": \"Senecio elegans\",\n" +
                "          \"probability\": 0.0273,\n" +
                "          \"similar_images\": [\n" +
                "            {\n" +
                "              \"id\": \"6a37a644a103e3b5198592aef716287c800070dd\",\n" +
                "              \"url\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/6a3/7a644a103e3b5198592aef716287c800070dd.jpg\",\n" +
                "              \"license_name\": \"CC BY 4.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by/4.0/\",\n" +
                "              \"citation\": \"Dave U\",\n" +
                "              \"similarity\": 0.624,\n" +
                "              \"url_small\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/6a3/7a644a103e3b5198592aef716287c800070dd.small.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"id\": \"a0b803a12c80a26e7eedf996bedf1e641561a7bd\",\n" +
                "              \"url\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/a0b/803a12c80a26e7eedf996bedf1e641561a7bd.jpg\",\n" +
                "              \"license_name\": \"CC BY 4.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by/4.0/\",\n" +
                "              \"citation\": \"Nathan Odgers\",\n" +
                "              \"similarity\": 0.609,\n" +
                "              \"url_small\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/a0b/803a12c80a26e7eedf996bedf1e641561a7bd.small.jpg\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"details\": {\n" +
                "            \"common_names\": [\n" +
                "              \"redpurple ragwort\",\n" +
                "              \"Purple Groundsel\",\n" +
                "              \"Beach Ragwort\",\n" +
                "              \"Veld Ragwort\",\n" +
                "              \"Veld Cineraria\",\n" +
                "              \"American Groundsel\",\n" +
                "              \"Wild Jacobaea\"\n" +
                "            ],\n" +
                "            \"taxonomy\": {\n" +
                "              \"class\": \"Magnoliopsida\",\n" +
                "              \"genus\": \"Senecio\",\n" +
                "              \"order\": \"Asterales\",\n" +
                "              \"family\": \"Asteraceae\",\n" +
                "              \"phylum\": \"Tracheophyta\",\n" +
                "              \"kingdom\": \"Plantae\"\n" +
                "            },\n" +
                "            \"url\": \"https://en.wikipedia.org/wiki/Senecio_elegans\",\n" +
                "            \"gbif_id\": 7819589,\n" +
                "            \"inaturalist_id\": 79044,\n" +
                "            \"rank\": \"species\",\n" +
                "            \"description\": {\n" +
                "              \"value\": \"Senecio elegans is a species of flowering plant in the aster family known by the common names redpurple ragwort, purple groundsel, wild cineraria and purple ragwort.\",\n" +
                "              \"citation\": \"https://en.wikipedia.org/wiki/Senecio_elegans\",\n" +
                "              \"license_name\": \"CC BY-SA 3.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by-sa/3.0/\"\n" +
                "            },\n" +
                "            \"synonyms\": [\n" +
                "              \"Brachyscome carnosa\",\n" +
                "              \"Cacalia carnosa\",\n" +
                "              \"Jacobaea elegans\",\n" +
                "              \"Kleinia carnosa\",\n" +
                "              \"Senecio elegans\",\n" +
                "              \"Senecio elegans var. diffusus\",\n" +
                "              \"Senecio elegans var. elegans\",\n" +
                "              \"Senecio elegans var. erectus\",\n" +
                "              \"Senecio pseudoelegans\"\n" +
                "            ],\n" +
                "            \"image\": {\n" +
                "              \"value\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/knowledge_base/wikidata/10f/10f34f1eb7d0ef20dcf6dc5d4ee98b74b0f8911e.jpg\",\n" +
                "              \"citation\": \"//commons.wikimedia.org/wiki/User:SAplants\",\n" +
                "              \"license_name\": \"CC BY-SA 4.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by-sa/4.0/\"\n" +
                "            },\n" +
                "            \"edible_parts\": null,\n" +
                "            \"watering\": null,\n" +
                "            \"best_light_condition\": \"This plant thrives in bright, indirect light. It can tolerate some direct sunlight, especially in the morning or late afternoon, but too much direct sun can scorch its leaves. If grown indoors, placing it near a window with filtered light is ideal. In outdoor settings, a spot with partial shade will help it grow well without the risk of sunburn.\",\n" +
                "            \"best_soil_type\": \"For optimal growth, this plant prefers well-draining soil. A mix that includes sand, perlite, or pumice can help improve drainage and prevent water from sitting around the roots. A standard potting mix with added organic matter like compost can also work well. Avoid heavy clay soils that retain too much moisture, as they can lead to root problems.\",\n" +
                "            \"common_uses\": \"Common uses for this plant include ornamental purposes in gardens and homes. Its bright, attractive flowers make it a popular choice for adding color to flower beds, borders, and containers. Additionally, it can be used in floral arrangements and as a ground cover in landscaping projects. Despite its toxicity, some traditional practices may use it in controlled, medicinal applications, but this is not recommended without expert guidance.\",\n" +
                "            \"cultural_significance\": \"In some cultures, this plant holds significance due to its vibrant flowers and hardy nature. It is often used in traditional medicine, although its toxic properties mean it must be used with caution. The plant is also sometimes featured in local folklore and is appreciated for its ability to thrive in various conditions, symbolizing resilience and adaptability.\",\n" +
                "            \"toxicity\": \"This plant is toxic to both humans and animals if ingested. It contains pyrrolizidine alkaloids, which can cause liver damage and other health issues. Symptoms of poisoning may include nausea, vomiting, and abdominal pain in humans, and drooling, vomiting, and lethargy in pets. It's important to keep this plant out of reach of children and animals to prevent accidental ingestion.\",\n" +
                "            \"best_watering\": \"Watering this plant properly is crucial for its health. It prefers soil that is consistently moist but not waterlogged. Overwatering can lead to root rot, so it's important to let the top inch of soil dry out between waterings. During the growing season, water it more frequently, but reduce the amount in the winter when the plant's growth slows down. Always use a pot with drainage holes to prevent excess water from accumulating.\",\n" +
                "            \"language\": \"en\",\n" +
                "            \"entity_id\": \"70d82558e93e28f7\"\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": \"d6a8af8991b922e5\",\n" +
                "          \"name\": \"Chamaemelum fuscatum\",\n" +
                "          \"probability\": 0.0256,\n" +
                "          \"similar_images\": [\n" +
                "            {\n" +
                "              \"id\": \"19ca4976f0330ead17a294be80460785b00d21da\",\n" +
                "              \"url\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/19c/a4976f0330ead17a294be80460785b00d21da.jpg\",\n" +
                "              \"license_name\": \"CC BY 4.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by/4.0/\",\n" +
                "              \"citation\": \"Tim Vochten\",\n" +
                "              \"similarity\": 0.706,\n" +
                "              \"url_small\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/19c/a4976f0330ead17a294be80460785b00d21da.small.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"id\": \"0c8243bfc447bd24f5d86a4b643cb61cdf5cebea\",\n" +
                "              \"url\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/0c8/243bfc447bd24f5d86a4b643cb61cdf5cebea.jpeg\",\n" +
                "              \"license_name\": \"CC BY 4.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by/4.0/\",\n" +
                "              \"citation\": \"Karim Haddad\",\n" +
                "              \"similarity\": 0.704,\n" +
                "              \"url_small\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/0c8/243bfc447bd24f5d86a4b643cb61cdf5cebea.small.jpeg\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"details\": {\n" +
                "            \"common_names\": [\n" +
                "              \"dusky dogfennel\",\n" +
                "              \"chamomile\"\n" +
                "            ],\n" +
                "            \"taxonomy\": {\n" +
                "              \"class\": \"Magnoliopsida\",\n" +
                "              \"genus\": \"Chamaemelum\",\n" +
                "              \"order\": \"Asterales\",\n" +
                "              \"family\": \"Asteraceae\",\n" +
                "              \"phylum\": \"Tracheophyta\",\n" +
                "              \"kingdom\": \"Plantae\"\n" +
                "            },\n" +
                "            \"url\": \"https://www.gbif.org/species/5391595\",\n" +
                "            \"gbif_id\": 5391595,\n" +
                "            \"inaturalist_id\": 76238,\n" +
                "            \"rank\": \"species\",\n" +
                "            \"description\": null,\n" +
                "            \"synonyms\": [\n" +
                "              \"Anthemis fallax\",\n" +
                "              \"Anthemis fuscata\",\n" +
                "              \"Anthemis fuscata subsp. minor\",\n" +
                "              \"Anthemis fuscata var. fuscata\",\n" +
                "              \"Anthemis fuscata var. minor\",\n" +
                "              \"Anthemis praecox\",\n" +
                "              \"Chamaemelum fuscatum f. minor\",\n" +
                "              \"Chamaemelum fuscatum fuscatum\",\n" +
                "              \"Chamaemelum fuscatum minor\",\n" +
                "              \"Chamaemelumfuscata\",\n" +
                "              \"Chamomilla fuscata\",\n" +
                "              \"Maruta fuscata\",\n" +
                "              \"Ormenis fuscata\",\n" +
                "              \"Ormenis maialis\",\n" +
                "              \"Perideraea fuscata\",\n" +
                "              \"Periderea fuscata\"\n" +
                "            ],\n" +
                "            \"image\": {\n" +
                "              \"value\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/knowledge_base/wikidata/069/0699b997235bd7937c75746c491a1994fba5c452.jpg\",\n" +
                "              \"citation\": \"//commons.wikimedia.org/w/index.php?title=User:Israelsierra&action=edit&redlink=1\",\n" +
                "              \"license_name\": \"CC BY 3.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by/3.0/\"\n" +
                "            },\n" +
                "            \"edible_parts\": null,\n" +
                "            \"watering\": null,\n" +
                "            \"best_light_condition\": \"This plant thrives in full sun to partial shade. It prefers at least six hours of direct sunlight each day to grow robustly. In hotter climates, providing some afternoon shade can help protect the plant from scorching. If grown indoors, place it near a south-facing window to ensure it receives adequate light.\",\n" +
                "            \"best_soil_type\": \"The plant grows best in well-draining soil with a neutral to slightly acidic pH. Sandy or loamy soils are ideal as they provide good drainage and aeration. Adding organic matter like compost can improve soil fertility and structure, promoting healthier growth. Avoid heavy clay soils that retain too much moisture.\",\n" +
                "            \"common_uses\": \"Common uses for this plant include its application in herbal teas and natural remedies. It is often used for its calming and soothing effects. Additionally, the plant's essential oils are sometimes extracted for use in aromatherapy and skincare products. Its flowers can also be used in potpourri and other decorative crafts.\",\n" +
                "            \"cultural_significance\": \"Historically, this plant has been valued in various cultures for its medicinal properties and aromatic qualities. It has been used in traditional remedies and herbal practices. Its pleasant fragrance and attractive appearance have also made it a popular choice in ornamental gardening.\",\n" +
                "            \"toxicity\": \"This plant is generally considered non-toxic to both humans and animals. It can be safely grown in gardens and homes without posing a risk to pets or children. However, as with any plant, it's always a good idea to monitor for any allergic reactions or sensitivities.\",\n" +
                "            \"best_watering\": \"For optimal growth, water the plant regularly but avoid overwatering. The soil should be kept consistently moist, especially during the growing season. However, ensure that the soil is well-draining to prevent waterlogging, which can lead to root rot. During the winter months, reduce the frequency of watering as the plant's growth slows down.\",\n" +
                "            \"language\": \"en\",\n" +
                "            \"entity_id\": \"d6a8af8991b922e5\"\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": \"1160bd8ec6097b56\",\n" +
                "          \"name\": \"Senecio glastifolius\",\n" +
                "          \"probability\": 0.0254,\n" +
                "          \"similar_images\": [\n" +
                "            {\n" +
                "              \"id\": \"ee7648c6f3b6730c72f8b6e3807a20c2af2942e8\",\n" +
                "              \"url\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/ee7/648c6f3b6730c72f8b6e3807a20c2af2942e8.jpeg\",\n" +
                "              \"license_name\": \"CC BY 4.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by/4.0/\",\n" +
                "              \"citation\": \"Joe Dillon\",\n" +
                "              \"similarity\": 0.683,\n" +
                "              \"url_small\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/ee7/648c6f3b6730c72f8b6e3807a20c2af2942e8.small.jpeg\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"id\": \"7e6b524358ea8585a2109504132a3e97521adef5\",\n" +
                "              \"url\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/7e6/b524358ea8585a2109504132a3e97521adef5.jpeg\",\n" +
                "              \"license_name\": \"CC BY 4.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by/4.0/\",\n" +
                "              \"citation\": \"Heidi Meudt\",\n" +
                "              \"similarity\": 0.59,\n" +
                "              \"url_small\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/7e6/b524358ea8585a2109504132a3e97521adef5.small.jpeg\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"details\": {\n" +
                "            \"common_names\": [\n" +
                "              \"Woad-leaved ragwort\",\n" +
                "              \"Pink ragwort\",\n" +
                "              \"Holly-leaved senecio\",\n" +
                "              \"Woadleaf Ragwort\"\n" +
                "            ],\n" +
                "            \"taxonomy\": {\n" +
                "              \"class\": \"Magnoliopsida\",\n" +
                "              \"genus\": \"Senecio\",\n" +
                "              \"order\": \"Asterales\",\n" +
                "              \"family\": \"Asteraceae\",\n" +
                "              \"phylum\": \"Tracheophyta\",\n" +
                "              \"kingdom\": \"Plantae\"\n" +
                "            },\n" +
                "            \"url\": \"https://en.wikipedia.org/wiki/Senecio_glastifolius\",\n" +
                "            \"gbif_id\": 8419686,\n" +
                "            \"inaturalist_id\": 336367,\n" +
                "            \"rank\": \"species\",\n" +
                "            \"description\": {\n" +
                "              \"value\": \"Senecio glastifolius is a species of flowering plant in the aster family known by the common names woad-leaved ragwort, holly-leaved senecio, and pink ragwort. A tall perennial herb, it is native to southern Africa, and it is cultivated as an ornamental plant for its colorful flowers. It has been known to escape cultivation and become naturalized in areas of appropriate climate; it can be found growing wild in parts of New Zealand and Australia.It favours a Mediterranean climate, often coastal, typically colonizing disturbed soil. It is an annual herb producing a single, erect, branching stem which grows to a height 1 to 1.5 metres tall. The serrated leaves are often more coarsely toothed near the leaf base, and feel prickly. The inflorescence bears flower heads lined with black-tipped bracts. They contain a yellowish disc at the centre, with mauve petals.  \\n\\nSenecio glastifolius is sometimes confused with Senecio elegans, (which also grows by the coast), but the leaves of the two species are different.\",\n" +
                "              \"citation\": \"https://en.wikipedia.org/wiki/Senecio_glastifolius\",\n" +
                "              \"license_name\": \"CC BY-SA 3.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by-sa/3.0/\"\n" +
                "            },\n" +
                "            \"synonyms\": [\n" +
                "              \"Jacobaea glastifolia\",\n" +
                "              \"Senecio serratulifolius\"\n" +
                "            ],\n" +
                "            \"image\": {\n" +
                "              \"value\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/knowledge_base/wikidata/5f9/5f9444a105014ad9d413425db748f8d1ae18e0d2.jpg\",\n" +
                "              \"citation\": \"//commons.wikimedia.org/wiki/User:SAplants\",\n" +
                "              \"license_name\": \"CC BY-SA 4.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by-sa/4.0/\"\n" +
                "            },\n" +
                "            \"edible_parts\": null,\n" +
                "            \"watering\": null,\n" +
                "            \"best_light_condition\": \"This plant thrives in bright light conditions. It does best in full sun to partial shade. If grown indoors, place it near a south or west-facing window where it can get plenty of light. However, it can tolerate some light shade, especially in hotter climates where too much direct sunlight can scorch its leaves. Ensuring it gets enough light will help it grow strong and healthy.\",\n" +
                "            \"best_soil_type\": \"For optimal growth, this plant prefers well-draining soil. A sandy or loamy soil mix works well, as it allows excess water to drain away easily. Adding organic matter like compost can improve soil fertility and structure, providing the plant with the nutrients it needs. Avoid heavy clay soils that retain too much moisture, as this can lead to root problems.\",\n" +
                "            \"common_uses\": \"Common uses for this plant include ornamental gardening and traditional medicine. Its attractive flowers make it a popular choice for garden borders, rock gardens, and containers. In some traditional practices, it is used to treat various ailments, although scientific evidence supporting these uses is limited. Additionally, it can be used in floral arrangements due to its long-lasting blooms. Its versatility makes it a valuable addition to both gardens and homes.\",\n" +
                "            \"cultural_significance\": \"This plant has cultural significance in some regions. It is often used in traditional medicine for its purported healing properties. In some cultures, it is also associated with certain rituals or beliefs. Its vibrant flowers and hardy nature make it a popular choice for gardens and landscapes, symbolizing resilience and beauty. Understanding its cultural background can enhance appreciation for this versatile plant.\",\n" +
                "            \"toxicity\": \"This plant is toxic to both humans and animals. Ingesting any part of it can cause symptoms like nausea, vomiting, and diarrhea. In severe cases, it can lead to more serious health issues. It's important to keep it out of reach of children and pets. If you suspect ingestion, seek medical attention immediately. Always handle the plant with care, and consider wearing gloves when pruning or handling it.\",\n" +
                "            \"best_watering\": \"Watering this plant properly is crucial for its health. It prefers consistently moist soil but doesn't like to sit in water. Overwatering can lead to root rot, so it's important to let the top inch of soil dry out between waterings. During the growing season, water it more frequently, but reduce the amount in the winter when the plant is not actively growing. Using a well-draining pot or soil mix can help prevent water from accumulating at the roots.\",\n" +
                "            \"language\": \"en\",\n" +
                "            \"entity_id\": \"1160bd8ec6097b56\"\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": \"3a7ce67f35484ed7\",\n" +
                "          \"name\": \"Chrysanthemum\",\n" +
                "          \"probability\": 0.0208,\n" +
                "          \"similar_images\": [\n" +
                "            {\n" +
                "              \"id\": \"94e6135718f01117025db26afe5d249c82742eb0\",\n" +
                "              \"url\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/94e/6135718f01117025db26afe5d249c82742eb0.jpg\",\n" +
                "              \"license_name\": \"CC BY 4.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by/4.0/\",\n" +
                "              \"citation\": \"Oleg Kosterin\",\n" +
                "              \"similarity\": 0.58,\n" +
                "              \"url_small\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/94e/6135718f01117025db26afe5d249c82742eb0.small.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"id\": \"a4665f481480c210c49ed7107fe8935b34545759\",\n" +
                "              \"url\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/a46/65f481480c210c49ed7107fe8935b34545759.jpeg\",\n" +
                "              \"license_name\": \"CC BY-NC-SA 4.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by-nc-sa/4.0/\",\n" +
                "              \"citation\": \"FlowerChecker s.r.o.\",\n" +
                "              \"similarity\": 0.516,\n" +
                "              \"url_small\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/a46/65f481480c210c49ed7107fe8935b34545759.small.jpeg\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"details\": {\n" +
                "            \"common_names\": [\n" +
                "              \"daisy\",\n" +
                "              \"chrysanthemums\",\n" +
                "              \"mums\"\n" +
                "            ],\n" +
                "            \"taxonomy\": {\n" +
                "              \"class\": \"Magnoliopsida\",\n" +
                "              \"genus\": \"Chrysanthemum\",\n" +
                "              \"order\": \"Asterales\",\n" +
                "              \"family\": \"Asteraceae\",\n" +
                "              \"phylum\": \"Tracheophyta\",\n" +
                "              \"kingdom\": \"Plantae\"\n" +
                "            },\n" +
                "            \"url\": \"https://en.wikipedia.org/wiki/Chrysanthemum\",\n" +
                "            \"gbif_id\": 3150746,\n" +
                "            \"inaturalist_id\": 53808,\n" +
                "            \"rank\": \"genus\",\n" +
                "            \"description\": {\n" +
                "              \"value\": \"Chrysanthemums (), sometimes called mums or chrysanths, are flowering plants of the genus Chrysanthemum in the family Asteraceae. They are native to East Asia and northeastern Europe. Most species originate from East Asia and the center of diversity is in China. Countless horticultural varieties and cultivars exist.\",\n" +
                "              \"citation\": \"https://en.wikipedia.org/wiki/Chrysanthemum\",\n" +
                "              \"license_name\": \"CC BY-SA 3.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by-sa/3.0/\"\n" +
                "            },\n" +
                "            \"synonyms\": [\n" +
                "              \"Arctanthemum\",\n" +
                "              \"Chrisanthemum\",\n" +
                "              \"Dendranthema\",\n" +
                "              \"Hulteniella\",\n" +
                "              \"Leucanthemum\"\n" +
                "            ],\n" +
                "            \"image\": {\n" +
                "              \"value\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/knowledge_base/wikidata/fc0/fc0b6a5b17041797f109c680646e56f4e1bd7d75.jpg\",\n" +
                "              \"citation\": \"https://en.wikipedia.org/wiki/User:Muhammad_Mahdi_Karim\",\n" +
                "              \"license_name\": \"GFDL 1.2\",\n" +
                "              \"license_url\": \"https://www.gnu.org/licenses/old-licenses/fdl-1.2.html\"\n" +
                "            },\n" +
                "            \"edible_parts\": [\n" +
                "              \"flowers\",\n" +
                "              \"leaves\",\n" +
                "              \"oil\",\n" +
                "              \"seeds\"\n" +
                "            ],\n" +
                "            \"watering\": {\n" +
                "              \"max\": 2,\n" +
                "              \"min\": 2\n" +
                "            },\n" +
                "            \"best_light_condition\": \"This plant thrives in full sun, requiring at least six hours of direct sunlight each day. While it can tolerate partial shade, too little light can result in leggy growth and fewer blooms. A sunny spot in the garden or a bright windowsill indoors is ideal. If grown indoors, rotating the plant occasionally ensures even light exposure and balanced growth.\",\n" +
                "            \"best_soil_type\": \"Well-draining soil is crucial for healthy growth. A mix of garden soil, compost, and sand or perlite works well. The soil should be rich in organic matter to provide essential nutrients. Avoid heavy clay soils that retain too much water, as this can lead to root rot. Regularly amending the soil with compost or well-rotted manure can improve its structure and fertility.\",\n" +
                "            \"common_uses\": \"This plant is widely used for ornamental purposes, both in gardens and as cut flowers. Its vibrant blooms add color to landscapes and floral arrangements. Additionally, it is used in traditional medicine in some cultures for its purported health benefits. The flowers can also be dried and used in teas, which are believed to have various therapeutic properties.\",\n" +
                "            \"cultural_significance\": \"In many cultures, this plant holds significant meaning. In some Asian countries, it symbolizes longevity and rejuvenation, often used in festivals and traditional medicine. In Europe, it is commonly associated with remembrance and is used in funeral arrangements. The plant's cultural significance varies widely, reflecting different traditions and beliefs around the world.\",\n" +
                "            \"toxicity\": \"This plant can be toxic to both humans and animals if ingested. It contains compounds that can cause skin irritation, gastrointestinal upset, and other symptoms. Pets, particularly cats and dogs, may experience drooling, vomiting, and diarrhea if they chew on the plant. It's important to keep it out of reach of children and pets to prevent accidental ingestion.\",\n" +
                "            \"best_watering\": \"Watering should be done consistently but not excessively. The soil should be kept moist, but not waterlogged. It's best to water at the base of the plant to avoid wetting the foliage, which can lead to disease. Early morning watering is ideal, allowing the plant to absorb moisture before the heat of the day. During hot, dry periods, more frequent watering may be necessary, but always check the soil moisture first.\",\n" +
                "            \"language\": \"en\",\n" +
                "            \"entity_id\": \"3a7ce67f35484ed7\"\n" +
                "          }\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    \"disease\": {\n" +
                "      \"suggestions\": [\n" +
                "        {\n" +
                "          \"id\": \"d24ebab7c0155e4b\",\n" +
                "          \"name\": \"Animalia\",\n" +
                "          \"probability\": 0.99,\n" +
                "          \"similar_images\": [\n" +
                "            {\n" +
                "              \"id\": \"d0f85dc17cd2f4fffc21ee6e504b5748cbc0f076\",\n" +
                "              \"url\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/d0f/85dc17cd2f4fffc21ee6e504b5748cbc0f076.jpg\",\n" +
                "              \"license_name\": \"CC BY-NC-SA 4.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by-nc-sa/4.0/\",\n" +
                "              \"citation\": \"FlowerChecker s.r.o.\",\n" +
                "              \"similarity\": 0.249,\n" +
                "              \"url_small\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/d0f/85dc17cd2f4fffc21ee6e504b5748cbc0f076.small.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"id\": \"e3094413684a1f10e1946fdd5a8613ad993c8063\",\n" +
                "              \"url\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/e30/94413684a1f10e1946fdd5a8613ad993c8063.jpg\",\n" +
                "              \"license_name\": \"CC BY-NC-SA 4.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by-nc-sa/4.0/\",\n" +
                "              \"citation\": \"FlowerChecker s.r.o.\",\n" +
                "              \"similarity\": 0.248,\n" +
                "              \"url_small\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/e30/94413684a1f10e1946fdd5a8613ad993c8063.small.jpg\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"redundant\": true,\n" +
                "          \"details\": {\n" +
                "            \"description\": \"Disorders induced by organisms from the animal kingdom. These pests cause direct damage by feeding on leaves, stems, roots, and other parts of the plant or by inhabiting plant tissues. Pests can also spread bacterial and viral diseases.\",\n" +
                "            \"url\": \"https://en.wikipedia.org/wiki/Animal\",\n" +
                "            \"common_names\": [\n" +
                "              \"pests\"\n" +
                "            ],\n" +
                "            \"language\": \"en\",\n" +
                "            \"entity_id\": \"d24ebab7c0155e4b\"\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": \"76e471140b1b55e2\",\n" +
                "          \"name\": \"Insecta\",\n" +
                "          \"probability\": 0.99,\n" +
                "          \"similar_images\": [\n" +
                "            {\n" +
                "              \"id\": \"8a3ccd568661ece1a9459b7f5a2511f20e46027a\",\n" +
                "              \"url\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/8a3/ccd568661ece1a9459b7f5a2511f20e46027a.jpg\",\n" +
                "              \"license_name\": \"CC BY-NC-SA 4.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by-nc-sa/4.0/\",\n" +
                "              \"citation\": \"FlowerChecker s.r.o.\",\n" +
                "              \"similarity\": 0.248,\n" +
                "              \"url_small\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/8a3/ccd568661ece1a9459b7f5a2511f20e46027a.small.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"id\": \"3afc8abef3f2c4796443ad3c0d9b4bdcf14b8957\",\n" +
                "              \"url\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/3af/c8abef3f2c4796443ad3c0d9b4bdcf14b8957.jpg\",\n" +
                "              \"license_name\": \"CC BY-NC-SA 4.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by-nc-sa/4.0/\",\n" +
                "              \"citation\": \"FlowerChecker s.r.o.\",\n" +
                "              \"similarity\": 0.239,\n" +
                "              \"url_small\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/3af/c8abef3f2c4796443ad3c0d9b4bdcf14b8957.small.jpg\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"details\": {\n" +
                "            \"description\": \"Class Insecta contains many major plant pests such as aphids and thrips. Damage can be caused by both larval and adult stages. They can cause feeding damage or transmit a bacterial, viral, or fungal infection.\",\n" +
                "            \"url\": \"https://en.wikipedia.org/wiki/Insect\",\n" +
                "            \"common_names\": [\n" +
                "              \"insects\"\n" +
                "            ],\n" +
                "            \"language\": \"en\",\n" +
                "            \"entity_id\": \"76e471140b1b55e2\"\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": \"bf75abeaaafd6c6f\",\n" +
                "          \"name\": \"feeding damage by insects\",\n" +
                "          \"probability\": 0.2824,\n" +
                "          \"similar_images\": [\n" +
                "            {\n" +
                "              \"id\": \"26714b7af74aa5ff3a2ebb9d6e943104b52b0556\",\n" +
                "              \"url\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/267/14b7af74aa5ff3a2ebb9d6e943104b52b0556.jpg\",\n" +
                "              \"license_name\": \"CC BY-NC-SA 4.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by-nc-sa/4.0/\",\n" +
                "              \"citation\": \"FlowerChecker s.r.o.\",\n" +
                "              \"similarity\": 0.271,\n" +
                "              \"url_small\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/267/14b7af74aa5ff3a2ebb9d6e943104b52b0556.small.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"id\": \"9833ed83050ee7cf7d9ea3700bacd1dafbee6827\",\n" +
                "              \"url\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/983/3ed83050ee7cf7d9ea3700bacd1dafbee6827.jpg\",\n" +
                "              \"license_name\": \"CC BY-NC-SA 4.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by-nc-sa/4.0/\",\n" +
                "              \"citation\": \"FlowerChecker s.r.o.\",\n" +
                "              \"similarity\": 0.254,\n" +
                "              \"url_small\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/983/3ed83050ee7cf7d9ea3700bacd1dafbee6827.small.jpg\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"details\": {\n" +
                "            \"description\": \"Feeding damage by insects includes holes or notches in leaves, removal of the tissue between leaf veins, tunnels in the plant tissue, root consumption, and others.\",\n" +
                "            \"url\": \"https://gardeningsolutions.ifas.ufl.edu/care/pests-and-diseases/pests/management/different-pests-different-damage.html\",\n" +
                "            \"common_names\": null,\n" +
                "            \"language\": \"en\",\n" +
                "            \"entity_id\": \"bf75abeaaafd6c6f\"\n" +
                "          }\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": \"98fc3d10c2d369c7\",\n" +
                "          \"name\": \"leaf miners\",\n" +
                "          \"probability\": 0.047,\n" +
                "          \"similar_images\": [\n" +
                "            {\n" +
                "              \"id\": \"3e4d5724e0ee04107b2cc89ce681fc0f5482a29f\",\n" +
                "              \"url\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/3e4/d5724e0ee04107b2cc89ce681fc0f5482a29f.jpg\",\n" +
                "              \"license_name\": \"CC BY-NC-SA 4.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by-nc-sa/4.0/\",\n" +
                "              \"citation\": \"FlowerChecker s.r.o.\",\n" +
                "              \"similarity\": 0.225,\n" +
                "              \"url_small\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/3e4/d5724e0ee04107b2cc89ce681fc0f5482a29f.small.jpg\"\n" +
                "            },\n" +
                "            {\n" +
                "              \"id\": \"ab2edbb5740a155d3bc04b958b55a9396d980aa3\",\n" +
                "              \"url\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/ab2/edbb5740a155d3bc04b958b55a9396d980aa3.jpg\",\n" +
                "              \"license_name\": \"CC BY-NC-SA 4.0\",\n" +
                "              \"license_url\": \"https://creativecommons.org/licenses/by-nc-sa/4.0/\",\n" +
                "              \"citation\": \"FlowerChecker s.r.o.\",\n" +
                "              \"similarity\": 0.224,\n" +
                "              \"url_small\": \"https://plant-id.ams3.cdn.digitaloceanspaces.com/similar_images/5/ab2/edbb5740a155d3bc04b958b55a9396d980aa3.small.jpg\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"details\": {\n" +
                "            \"description\": \"Larval stages of leaf-mining insects are feeding on the leaf tissue of plants, building little tunnels under the leaf surface.\",\n" +
                "            \"url\": \"https://en.wikipedia.org/wiki/Leaf_miner\",\n" +
                "            \"common_names\": [\n" +
                "              \"leaf-mining insects\"\n" +
                "            ],\n" +
                "            \"language\": \"en\",\n" +
                "            \"entity_id\": \"98fc3d10c2d369c7\"\n" +
                "          }\n" +
                "        }\n" +
                "      ],\n" +
                "      \"question\": null\n" +
                "    },\n" +
                "    \"is_plant\": {\n" +
                "      \"probability\": 0.80672354,\n" +
                "      \"threshold\": 0.5,\n" +
                "      \"binary\": true\n" +
                "    },\n" +
                "    \"is_healthy\": {\n" +
                "      \"binary\": false,\n" +
                "      \"threshold\": 0.525,\n" +
                "      \"probability\": 0.2420053482055664\n" +
                "    }\n" +
                "  },\n" +
                "  \"status\": \"COMPLETED\",\n" +
                "  \"sla_compliant_client\": true,\n" +
                "  \"sla_compliant_system\": true,\n" +
                "  \"created\": 1743402170.617139,\n" +
                "  \"completed\": 1743402170.985512\n" +
                "}";
        try {
            JSONObject json = new JSONObject(rawData);
            PlantResult plantResult = PlantResultBuilder.plantResultBuilder(json);
            result.postValue(new Result.Success<>(plantResult));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
}

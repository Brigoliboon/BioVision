package com.example.biovision.data.API.Plant.model;

public record Image(String id, String url, String license_name, String license_url,
             String citation, double similarity, String imgSmallLink){
    /**
     * JSON Keys
     */
    public static final String ID = "id";
    public static final String URL = "url";
    public static final String LICENSE_NAME = "license_name";
    public static final String LICENSE_URL = "license_url";
    public static final String CITATION = "citation";
    public static final String SIMILARITY = "similarity";
    public static final String SMALL_IMG_LINK = "url_small";
}

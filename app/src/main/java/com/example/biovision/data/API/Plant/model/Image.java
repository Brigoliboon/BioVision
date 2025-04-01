package com.example.biovision.data.API.Plant.model;

public record Image(String id, String url, String license_name, String license_url,
             String citation, double similarity, String imgSmallLink){

}

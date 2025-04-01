package com.example.biovision.data.API.Plant.model;

import java.util.ArrayList;

public record Plant(String id, String name, double probability, ArrayList<Image> similarImages, Detail detail) {
}

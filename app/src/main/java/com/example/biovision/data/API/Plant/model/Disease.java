package com.example.biovision.data.API.Plant.model;

import java.util.ArrayList;

public record Disease(String id, String name, double probability, ArrayList<Image> images, String desc, Object common_names) {
}

package com.example.biovision.data.API.Plant.model;

public record Taxonomy(String cls, String genus, String order, String family, String phylum, String kingdom){
    public static final String CLASS = "class";
    public static final String GENUS = "genus";
    public static final String ORDER = "order";
    public static final String FAMILY = "family";
    public static final String PHYLUM = "phylum";
    public static final String KINGDOM = "kingdom";

}

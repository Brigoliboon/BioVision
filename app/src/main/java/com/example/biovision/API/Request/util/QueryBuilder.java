package com.example.biovision.API.Request.util;

import java.util.HashMap;
import java.util.Map;

import okhttp3.HttpUrl;

public class QueryBuilder {

    private HashMap<String, String> queries;
    public QueryBuilder(HashMap<String, String> queries){
        this.queries = queries;
    }

    public HttpUrl queriedUrl(String baseUrl){
        // Parses the url into a HttpUrl builder
        HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl).newBuilder();

        // Adds the queries on the HttpUrl Recursively
        for (Map.Entry<String, String> entry : queries.entrySet()) {
            urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
        }

        // Parses the builder into HttpUrl
        HttpUrl url = urlBuilder.build();

        return url;
    }

}

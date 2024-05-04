package com.example.cs310_project;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class GeocodeResponse {
    @SerializedName("results")
    private List<Result> results;

    public String getFirstPlaceId() {
        if (results != null && !results.isEmpty()) {
            return results.get(0).getPlaceId();
        }
        return null;
    }

    private static class Result {
        @SerializedName("place_id")
        private String placeId;

        public String getPlaceId() {
            return placeId;
        }
    }
}



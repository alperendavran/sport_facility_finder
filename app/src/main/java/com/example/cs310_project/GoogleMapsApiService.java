package com.example.cs310_project;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleMapsApiService {

    @GET("/maps/api/geocode/json")
    Call<GeocodeResponse> getPlaceIdFromLatLng(@Query("latlng") String latlng, @Query("key") String apiKey);

    @GET("maps/api/distancematrix/json")
     Call<DistanceMatrixResponse> getDistanceAndDuration(
            @Query("destinations") String destination,
            @Query("origins") String origin,
            @Query("key") String apiKey
    );

}
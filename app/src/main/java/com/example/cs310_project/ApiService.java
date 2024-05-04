package com.example.cs310_project;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiService {
    @POST("SportFacilityFinder/sportPlaces/searchSportPlaceByTypeAndCityAndDistrict")
    Call<List<SportPlace>> searchFacilities(@Body RequestBody body);

    @POST("SportFacilityFinder/sportPlaces/{id}/addRating")
    Call<Double> addRate(@Path("id") String id, @Query("rating") double rating);
    @GET("SportFacilityFinder/sportPlaces/{id}/getAverageRating")
    Call<Double> getAverageRatingOfSportPlace(@Path("id") String id);
}

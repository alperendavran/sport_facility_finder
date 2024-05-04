package com.example.cs310_project;

import static android.content.Intent.getIntent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SportPlacesActivity extends AppCompatActivity {
    private List<SportPlace> sportPlaces;

    private RecyclerView recyclerView;
    private SportPlaceAdapter adapter;
    private boolean sortRatingAscending = true;
    private boolean sortDistanceAscending = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_places);

        recyclerView = findViewById(R.id.recyclerViewSportPlaces);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

         sportPlaces = getIntent().getParcelableArrayListExtra("sportPlaces");

        getDistancesForSportPlaces(sportPlaces);



        Button backButton = findViewById(R.id.button_back);
        backButton.setOnClickListener(view -> {
            onBackPressed();
        });

        adapter = new SportPlaceAdapter(sportPlaces);
        recyclerView.setAdapter(adapter);

        Button sortRatingButton = findViewById(R.id.button_sort_rating);
        Button sortDistanceButton = findViewById(R.id.button_sort_distance);

        sortRatingButton.setOnClickListener(v -> sortRating());
        sortDistanceButton.setOnClickListener(v -> sortDistance());

    }
    
    @Override
    protected void onResume() {
        super.onResume();
        refreshAverageRatings();
    }

    private void refreshAverageRatings() {
        for (SportPlace sportPlace : sportPlaces) {
            fetchUpdatedRating(sportPlace);
        }
    }

    private void fetchUpdatedRating(SportPlace sportPlace) {

            ApiService apiService = ApiClient.getClient().create(ApiService.class);
            Call<Double> call = apiService.getAverageRatingOfSportPlace(sportPlace.getId());

            call.enqueue(new Callback<Double>() {
                @Override
                public void onResponse(Call<Double> call, Response<Double> response) {
                    if (response.isSuccessful()) {
                        double updatedRating = response.body();
                        sportPlace.setScore(updatedRating);
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<Double> call, Throwable t) {
                    // Handle the failure case
                }
            });
        }


    private void sortRating() {
        // Sorting logic for rating
        Collections.sort(sportPlaces, (sp1, sp2) -> sortRatingAscending ?
                Float.compare((float) sp1.getScore(), (float) sp2.getScore()) :
                Float.compare((float) sp2.getScore(), (float) sp1.getScore()));
        sortRatingAscending = !sortRatingAscending;
        adapter.notifyDataSetChanged();
    }

    private void sortDistance() {
        Collections.sort(sportPlaces, (sp1, sp2) -> {
            float distance1 = parseDistance(sp1.getDistance());
            float distance2 = parseDistance(sp2.getDistance());
            return sortDistanceAscending ? Float.compare(distance1, distance2) : Float.compare(distance2, distance1);
        });
        sortDistanceAscending = !sortDistanceAscending;
        adapter.notifyDataSetChanged();
    }

    private float parseDistance(String distanceStr) {
        try {
            return Float.parseFloat(distanceStr.split(" ")[0]);
        } catch (NumberFormatException e) {
            Log.e("ParseError", "Failed to parse distance", e);
            return Float.MAX_VALUE; // Return a large value on parse failure
        }
    }

    private void getDistancesForSportPlaces(List<SportPlace> sportPlaces) {
        // Create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GoogleMapsApiService googleMapsApiService = retrofit.create(GoogleMapsApiService.class);
        for (SportPlace sportPlace : sportPlaces) {
            String apiKey = ""; // API KEY NEEDED
            SharedPreferences sharedPreferences = getSharedPreferences("GeocodeVerisi", MODE_PRIVATE);

            String origin = sharedPreferences.getString("firstPlaceId", "default_value_if_not_found");
            origin="place_id:"+origin;
            Log.i("BAKALIM SHARED MISAN",origin);
            String destination = "place_id:"+sportPlace.getGoogleMapsLink();
            Log.i("BAKALIM DEST MISAN",destination);

            Call<DistanceMatrixResponse> call = googleMapsApiService.getDistanceAndDuration(destination, origin, apiKey);

            call.enqueue(new Callback<DistanceMatrixResponse>() {
                @Override
                public void onResponse(Call<DistanceMatrixResponse> call, Response<DistanceMatrixResponse> response) {
                    if (response.isSuccessful()) {
                        DistanceMatrixResponse matrixResponse = response.body();
                        Log.d("LÜTFEN DISTANCE NE OLUR", String.valueOf(response));
                        if (matrixResponse != null && matrixResponse.getRows().size() > 0) {
                            DistanceMatrixRow row = matrixResponse.getRows().get(0);
                            if (row.getElements().size() > 0) {
                                DistanceMatrixElement element = row.getElements().get(0);
                                Distance distance = element.getDistance();
                                if (distance != null) {
                                    int distanceValue = distance.getValue();
                                    float distanceInKm = distanceValue / 1000f; // Convert to kilometers
                                    String formattedDistance = String.format("%.1f", distanceInKm); // Format to one decimal place
                                    formattedDistance=formattedDistance+" km";
                                    sportPlace.setDistance(formattedDistance);
                                } else {
                                    sportPlace.setDistance("");
                                }


                                // RecyclerView'ı güncellemek için adapter.notifyDataSetChanged() kullanın
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<DistanceMatrixResponse> call, Throwable t) {
                    // Hata durumunu ele alın
                }
            });


        }
    }
}



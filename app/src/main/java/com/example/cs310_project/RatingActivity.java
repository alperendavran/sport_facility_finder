package com.example.cs310_project;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.RatingBar;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs310_project.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RatingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating_screen);

        TextView nameView = findViewById(R.id.textViewSportPlaceNameRating);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        Button submitButton = findViewById(R.id.buttonSubmitRating);

        String name = getIntent().getStringExtra("NAME");
        name="Rate "+name;
        nameView.setText(name);

        Button backButton = findViewById(R.id.button_back);
        backButton.setOnClickListener(view -> {
            onBackPressed();
        });

        submitButton.setOnClickListener(view -> {
            float rating = ratingBar.getRating();
            submitRating(rating);

            finish(); // Closes the RatingActivity and returns to the previous activity
        });
    }

    private void submitRating(float rating) {
        String sportPlaceId = getIntent().getStringExtra("ID");
        Log.i("RATING",sportPlaceId);

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<Double> call = apiService.addRate(sportPlaceId, rating);

        call.enqueue(new Callback<Double>() {
            @Override
            public void onResponse(Call<Double> call, Response<Double> response) {
                if (response.isSuccessful()) {
                    // Handle successful response
                    Toast.makeText(RatingActivity.this, "Rating submitted successfully", Toast.LENGTH_SHORT).show();
                    finish(); // Close the activity
                } else {
                    // Handle request errors
                    Toast.makeText(RatingActivity.this, "Failed to submit rating", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Double> call, Throwable t) {

                Log.i("RATING", String.valueOf(t));

            }
        });
    }
}



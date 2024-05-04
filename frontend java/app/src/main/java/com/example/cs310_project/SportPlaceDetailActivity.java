package com.example.cs310_project;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cs310_project.R;
import com.squareup.picasso.Picasso;
import androidx.appcompat.app.AppCompatActivity;

public class SportPlaceDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_place_detail);

        ImageView imageView = findViewById(R.id.imageViewSportPlace);
        TextView nameView = findViewById(R.id.textViewSportPlaceName);
        TextView descriptionView = findViewById(R.id.textViewSportPlaceDescription);

        // Retrieve data from intent
        String imageUrl = getIntent().getStringExtra("IMAGE"); // Resim URL'sini al
        String ID = getIntent().getStringExtra("ID");
        String name = getIntent().getStringExtra("NAME");
        String description = getIntent().getStringExtra("DESCRIPTION");
        Button rateButton = findViewById(R.id.RATE); // Reference to the RATE button


        Button backButton = findViewById(R.id.button_back);
        backButton.setOnClickListener(view -> {
            onBackPressed();
        });



        // Set data to views using Picasso
        if (imageUrl != null) {
            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.filter_spinner_dropdown_bg) // İndirilirken gösterilecek yer tutucu
                    .error(R.drawable.ic_launcher_background) // Hata durumunda gösterilecek yer tutucu
                    .into(imageView);
        }
        nameView.setText(name);
        descriptionView.setText(description);

        rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SportPlaceDetailActivity.this, RatingActivity.class);

                intent.putExtra("NAME", name);
                intent.putExtra("ID", ID);

                startActivity(intent);
            }
        });

    }
}

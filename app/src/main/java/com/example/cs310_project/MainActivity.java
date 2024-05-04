package com.example.cs310_project;


import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView autoCompleteCity, autoCompleteDistrict, autoCompleteSportType;
    private Button buttonSearch;

    private HashMap<String, List<String>> cityDistrictMap = new HashMap<>();
    private List<String> cities = new ArrayList<>();
    private ArrayAdapter<String> districtAdapter;
    private SharedPreferences sharedPreferences;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("GeocodeVerisi", MODE_PRIVATE);

        // Check location permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // If permission is not granted, request it
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            // If permission is granted, proceed to initialize the app
            initializeApp();
        }
    }

    private void initializeApp() {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.apply();

        // Konum izni kontrolü
        Retrofit retrofit = null;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        }

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://maps.googleapis.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        GoogleMapsApiService apiService = retrofit.create(GoogleMapsApiService.class);

        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    if (location != null) {
                        double latitude = location.getLatitude(); // Enlem
                        double longitude = location.getLongitude(); // Boylam
                        Log.e("Koordinatlar", "Enlem: " + latitude + ", Boylam: " + longitude);
                        String latlng = latitude + "," + longitude;
                        String apiKey = "AIzaSyAng5cCIumnjd07VUmuD9_eo7cuxNyVVoI"; // Replace with your Google Maps API key
                        Call<GeocodeResponse> call = apiService.getPlaceIdFromLatLng(latlng, apiKey);

                        call.enqueue(new Callback<GeocodeResponse>() {
                            @Override
                            public void onResponse(Call<GeocodeResponse> call, Response<GeocodeResponse> response) {
                                if (response.isSuccessful()) {

                                    GeocodeResponse geocodeResponse = response.body();

                                    String firstPlaceId = geocodeResponse.getFirstPlaceId();
                                    editor.putString("firstPlaceId", firstPlaceId);
                                    editor.apply();
                                    // Handle the geocodeResponse object here
                                    Log.e("OOOOO",geocodeResponse.getFirstPlaceId());
                                } else {
                                    Log.e("OOOOO","MAALESEF MAALESED...");
                                }
                            }

                            @Override
                            public void onFailure(Call<GeocodeResponse> call, Throwable t) {
                                // Handle failure
                            }
                        });

                        // Adım 3: Konum bilgilerini kullanarak istediğiniz işlemi gerçekleştirin
                    } else {
                        Log.e("Hata", "Konum bilgileri alınamadı.");
                    }
                });




        autoCompleteCity = findViewById(R.id.spinner_city);
        autoCompleteDistrict = findViewById(R.id.spinner_district);
        autoCompleteSportType = findViewById(R.id.spinner_sport_type);
        buttonSearch = findViewById(R.id.button_search);

        prepareCityDistrictData();

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cities);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        autoCompleteCity.setAdapter(cityAdapter);

        districtAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>());
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        autoCompleteDistrict.setAdapter(districtAdapter);

        autoCompleteCity.setDropDownBackgroundDrawable(
                ResourcesCompat.getDrawable(
                        getResources(),
                        R.drawable.filter_spinner_dropdown_bg,
                        null
                )
        );

        autoCompleteDistrict.setDropDownBackgroundDrawable(
                ResourcesCompat.getDrawable(
                        getResources(),
                        R.drawable.filter_spinner_dropdown_bg,
                        null
                )
        );

        autoCompleteCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCity = parent.getItemAtPosition(position).toString();
                updateDistrictSpinner(selectedCity);
            }
        });

        String[] sports = {"Basketball", "Football", "Tennis", "Swimming"};
        ArrayAdapter<String> sportTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sports);
        sportTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        autoCompleteSportType.setAdapter(sportTypeAdapter);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Burada API isteği yapabilirsiniz
                searchFacilities();
            }
        });
    }

    private void prepareCityDistrictData() {
        cityDistrictMap = readJsonData("il_ilce_data.json");
        cities = new ArrayList<>(cityDistrictMap.keySet());

        // İller için Spinner Adapter
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cities);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        autoCompleteCity.setAdapter(cityAdapter);

        // İlçeler için Spinner Adapter (İlk başta boş)
        districtAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>());
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        autoCompleteDistrict.setAdapter(districtAdapter);
    }

    private void updateDistrictSpinner(String city) {
        List<String> districts = cityDistrictMap.get(city);
        districtAdapter.clear();
        if (districts != null) {
            districtAdapter.addAll(districts);
        }
        districtAdapter.notifyDataSetChanged();
    }

    private void searchFacilities() {
        String selectedCity = autoCompleteCity.getText().toString();
        String selectedDistrict = autoCompleteDistrict.getText().toString();
        String selectedSportType = autoCompleteSportType.getText().toString();

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Locale turkishLocale = new Locale("tr", "TR");

        // Prepare the request body
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("sportType", selectedSportType);
            JSONObject address = new JSONObject();

            address.put("city", selectedCity.toLowerCase(turkishLocale));
            address.put("district", selectedDistrict);
            requestBody.put("address", address);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), requestBody.toString());
        Log.e("Debug", "BAK BUNA HEMEN: " + body);

        // Make the network request
        Call<List<SportPlace>> call = apiService.searchFacilities(body);
        call.enqueue(new Callback<List<SportPlace>>() {

            @Override
            public void onResponse(Call<List<SportPlace>> call, Response<List<SportPlace>> response) {
                if (response.isSuccessful()) {
                    List<SportPlace> sportPlaces = response.body();

                    for (SportPlace sp : sportPlaces) {
                        if (sp.getSportType() == null) {
                            Log.e("Debug", "SportType is null for SportPlace: " + sp.getName());
                        }
                    }

                    Intent sportPlacesIntent = new Intent(MainActivity.this, SportPlacesActivity.class);
                    sportPlacesIntent.putParcelableArrayListExtra("sportPlaces", new ArrayList<>(sportPlaces));
                    startActivity(sportPlacesIntent);
                }
            }

            @Override
            public void onFailure(Call<List<SportPlace>> call, Throwable t) {
                Log.i("HOP HEMŞERİM", "API BAĞLANITIS BAŞARILI değil");
                Log.i("HOP HEMŞERİM", call.toString());

                // TODO: Handle failure
            }
        });
    }

    public HashMap<String, List<String>> readJsonData(String filename) {
        HashMap<String, List<String>> cityDistrictMap = new HashMap<>();
        try {
            InputStream is = getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String il = obj.getString("il").trim();
                String ilce = obj.getString("ilçe").trim();

                List<String> districtList = cityDistrictMap.getOrDefault(il, new ArrayList<>());
                districtList.add(ilce);
                cityDistrictMap.put(il, districtList);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return cityDistrictMap;
    }

    // İzin isteme sonuçları
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.e("GELDİ GOL GELDİ", "OLEY OLEY OLEU");
                // Konum izni verildi, konum bilgilerini alabilirsiniz.
                // Google Distance Matrix API'yi kullanarak place ID'yi alabilirsiniz.
            } else {
                Log.e("GELMEDİ GOL GELMEDİ", "HAYIR HAYIR HAYIR");
                // Kullanıcı izin vermedi, gerekli işlemi yapın (örneğin, hata mesajı gösterin).
            }
        }
    }
}

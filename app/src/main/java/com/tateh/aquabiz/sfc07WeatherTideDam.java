package com.tateh.aquabiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;

import java.util.Objects;
import java.util.StringTokenizer;

public class sfc07WeatherTideDam extends AppCompatActivity {

    TextView txtcity, txttemperature, txtupdate, txtwind, txtcloudiness, txtpressure, txthumidity, txtsunrise, txtsunset, txtSource;
    AppCompatButton btnWeather, btnTide, btnDam;
    LinearLayout LLweather, LLsearch;
    WebView webview;
    FusedLocationProviderClient client;
    String currentLat="0", currentLong="0", saan="weather", dam_url1, dam_url2, dam_source;
    Spinner spnLocation;
    String[] arrayWeatherLoc, arrayWeatherLat, arrayWeatherLong, arrayTideLoc, arrayTideURL, arrayTideSource;
    int last_bilang = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sfc07_weather_tide_dam);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Weather Forecast");

        btnWeather = findViewById(R.id.btnWeather);
        btnTide = findViewById(R.id.btnTide);
        btnDam = findViewById(R.id.btnDam);

        LLweather = findViewById(R.id.LLweather);
        LLsearch = findViewById(R.id.LLsearch);
        webview = findViewById(R.id.webview);

        txtcity = findViewById(R.id.txtcity);
        txttemperature = findViewById(R.id.txttemperature);
        txtupdate = findViewById(R.id.txtupdate);
        txtwind = findViewById(R.id.txtwind);
        txtcloudiness = findViewById(R.id.txtcloudiness);
        txtpressure = findViewById(R.id.txtpressure);
        txthumidity = findViewById(R.id.txthumidity);
        txtsunrise = findViewById(R.id.txtsunrise);
        txtsunset = findViewById(R.id.txtsunset);
        txtSource = findViewById(R.id.txtSource);
        spnLocation = findViewById(R.id.spnLocation);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }

        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        client = LocationServices.getFusedLocationProviderClient(this);
        btnWeather.setEnabled(false);

        String kabuuan = setting_global.getpreferences(this, R.string.sfc07_weather_tide_dam);
        StringTokenizer tokens = new StringTokenizer(kabuuan, "♦");
        String weather = tokens.nextToken();
        String tide = tokens.nextToken();
        String dam = tokens.nextToken();

        if(!weather.equals("wala")) {
            StringTokenizer tokensWeather = new StringTokenizer(weather, "♣");
            String weather_location = "Current Location|" + tokensWeather.nextToken();
            String weather_latitude = tokensWeather.nextToken();
            String weather_longitude = tokensWeather.nextToken();
            arrayWeatherLoc = weather_location.split("\\|");
            arrayWeatherLat = weather_latitude.split("\\|");
            arrayWeatherLong = weather_longitude.split("\\|");
        }

        if(!tide.equals("wala")) {
            StringTokenizer tokensTide = new StringTokenizer(tide, "♣");
            String tide_location = tokensTide.nextToken();
            String tide_url = tokensTide.nextToken();
            String tide_source = tokensTide.nextToken();
            arrayTideLoc = tide_location.split("\\|");
            arrayTideURL = tide_url.split("\\|");
            arrayTideSource = tide_source.split("\\|");
        }

        if(!dam.equals("wala")){
            StringTokenizer tokensDam = new StringTokenizer(dam, "♠");
            StringTokenizer tokensDamHiwalay = new StringTokenizer(tokensDam.nextToken(), "♣");
            dam_url1 = tokensDamHiwalay.nextToken();
            dam_source = tokensDamHiwalay.nextToken();
            StringTokenizer tokensDamHiwalay2 = new StringTokenizer(tokensDam.nextToken(), "♣");
            dam_url2 = tokensDamHiwalay2.nextToken();
        }


        getCurrentLocation();
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(location -> {
            if(location != null){
                currentLat = String.valueOf(location.getLatitude());
                currentLong = String.valueOf(location.getLongitude());
                setting_global.ShowProgressDialog(this);
                getWeather(currentLat, currentLong);
                setSpinner();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setting_global.OKdisplay("Permission granted.", "You can now view the weather in your current location.", sfc07WeatherTideDam.this, sfc07WeatherTideDam.class);
            } else {
                setting_global.OKdisplay("Permission denied.", "Permission needed to get the current weather in your location.", sfc07WeatherTideDam.this, sfc02Home.class);
            }
        }
    }

    private void setSpinner() {
        String[] arrayLoc;
        if(saan.equals("weather")) arrayLoc = arrayWeatherLoc;
        else arrayLoc = arrayTideLoc;
        ArrayAdapter<String> spinnerLocationAdapter = new ArrayAdapter<>(
                this, R.layout.support_simple_spinner_dropdown_item, arrayLoc);
        spinnerLocationAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spnLocation.setAdapter(spinnerLocationAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        setting_global.gointent(this,sfc02Home.class);
    }

    public void btn_home(View view) {
        onBackPressed();
    }

    public void btn_notification(View view) {

    }

    public void btn_announcement(View view) {
    }

    public void btn_setting(View view) {
    }

    private void getWeather(String latitude, String longitude) {
        @SuppressLint("SetTextI18n") sfc07WeatherTideDam_Function.placeIdTask asyncTask =new sfc07WeatherTideDam_Function.placeIdTask((weather_city, weather_temperature, weather_update, weather_wind, weather_cloudiness, weather_pressure, weather_humidity, weather_sunrise, weather_sunset) -> {
            if(spnLocation.getSelectedItemPosition() == 0) txtcity.setText(weather_city);
            else txtcity.setText(spnLocation.getSelectedItem().toString().toUpperCase() + ", PH");
            txttemperature.setText(weather_temperature);
            txtupdate.setText(weather_update);
            txtwind.setText(weather_wind);
            txtcloudiness.setText(weather_cloudiness);
            txtpressure.setText(weather_pressure);
            txthumidity.setText(weather_humidity);
            txtsunrise.setText(weather_sunrise);
            txtsunset.setText(weather_sunset);
        });
        asyncTask.execute(latitude,longitude);
        setting_global.dismissAlertDialog();
    }

    @SuppressLint("SetTextI18n")
    public void btnWeather(View view) {
        btnWeather.setBackgroundColor(ContextCompat.getColor(this, R.color.green_500));
        btnTide.setBackgroundColor(ContextCompat.getColor(this, R.color.green_200));
        btnDam.setBackgroundColor(ContextCompat.getColor(this, R.color.green_200));
        btnWeather.setEnabled(false);
        btnTide.setEnabled(true);
        btnDam.setEnabled(true);
        LLweather.setVisibility(View.VISIBLE);
        LLsearch.setVisibility(View.VISIBLE);
        webview.setVisibility(View.GONE);
        setTitle("Weather Forecast");
        saan = "weather";
        setSpinner();
        txtSource.setText("openweathermap.org");
    }

    public void btnTide(View view) {
        btnTide.setBackgroundColor(ContextCompat.getColor(this, R.color.green_500));
        btnWeather.setBackgroundColor(ContextCompat.getColor(this, R.color.green_200));
        btnDam.setBackgroundColor(ContextCompat.getColor(this, R.color.green_200));
        btnTide.setEnabled(false);
        btnWeather.setEnabled(true);
        btnDam.setEnabled(true);
        webview.setVisibility(View.VISIBLE);
        LLsearch.setVisibility(View.VISIBLE);
        LLweather.setVisibility(View.GONE);
        setTitle("Tide Chart");
        saan = "tide";
        setSpinner();
        webview.loadUrl(arrayTideURL[0]);
        txtSource.setText(arrayTideSource[0]);
    }

    public void btnDam(View view) {
        btnDam.setBackgroundColor(ContextCompat.getColor(this, R.color.green_500));
        btnWeather.setBackgroundColor(ContextCompat.getColor(this, R.color.green_200));
        btnTide.setBackgroundColor(ContextCompat.getColor(this, R.color.green_200));
        btnDam.setEnabled(false);
        btnWeather.setEnabled(true);
        btnTide.setEnabled(true);
        webview.setVisibility(View.VISIBLE);
        LLsearch.setVisibility(View.GONE);
        LLweather.setVisibility(View.GONE);
        setTitle("Dam Level");
        webview.loadUrl(dam_url1);
        webview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                webview.loadUrl(dam_url2);
            }
        });
        txtSource.setText(dam_source);
    }

    public void btn_search(View view) {
        int bilang = spnLocation.getSelectedItemPosition();
        if(last_bilang == bilang) setting_global.display("Same location.", "Please select different location.", this);
        else {
            last_bilang = bilang;
            if (saan.equals("weather")) {
                if (bilang == 0) getWeather(currentLat, currentLong);
                else getWeather(arrayWeatherLat[bilang - 1], arrayWeatherLong[bilang - 1]);
            } else {
                webview.loadUrl(arrayTideURL[bilang]);
            }
        }
    }
}
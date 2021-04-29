package com.tateh.aquabiz;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

class sfc07WeatherTideDam_Function {
    private static final String OPEN_WEATHER_MAP_URL = "http://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&units=metric";
    private static final String OPEN_WEATHER_MAP_API = "55c4007e78bcc940c22c7e55a6d23857";

    public interface AsyncResponse {
        void processFinish(String output1, String output2, String output3, String output4, String output5, String output6, String output7, String output8, String output9);
    }

    public static class placeIdTask extends AsyncTask<String, Void, JSONObject> {
        AsyncResponse delegate;
        placeIdTask(AsyncResponse asyncResponse) {
            delegate = asyncResponse;
        }
        @Override
        protected JSONObject doInBackground(String... params) {
            JSONObject jsonWeather = null;
            try {
                jsonWeather = getWeatherJSON(params[0], params[1]);
            } catch (Exception e) {
                Log.d("Error", "Cannot process JSON results", e);
            }
            return jsonWeather;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            try {
                if(json != null){
                    JSONObject details = json.getJSONArray("weather").getJSONObject(0);
                    JSONObject main = json.getJSONObject("main");
                    DateFormat df = DateFormat.getDateTimeInstance();

                    String city = json.getString("name").toUpperCase(Locale.US) + ", " + json.getJSONObject("sys").getString("country");
                    @SuppressLint("DefaultLocale") String temperature = String.format("%.1f", main.getDouble("temp"))+ "°C";
                    String updated = "As of " + df.format(new Date(json.getLong("dt")*1000));
                    String wind = json.getJSONObject("wind").getString("speed")+ " m/s";
                    String cloudiness = details.getString("description").toUpperCase(Locale.US);
                    String pressure = main.getString("pressure") + " hpa";
                    String humidity = main.getString("humidity") + "%";
                    Date time_sunrise = new Date(json.getJSONObject("sys").getLong("sunrise") * 1000);
                    String sunrise = String.valueOf(formatTime(time_sunrise));
                    Date time_sunset = new Date(json.getJSONObject("sys").getLong("sunset") * 1000);
                    String sunset = String.valueOf(formatTime(time_sunset));

                    delegate.processFinish(city, temperature, updated, wind, cloudiness, pressure, humidity, sunrise, sunset);

                }
            } catch (JSONException ignored) { }
        }
    }

    private static String formatTime(Date dateObject) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }


    private static JSONObject getWeatherJSON(String lat, String lon){
        try {
            URL url = new URL(String.format(OPEN_WEATHER_MAP_URL, lat, lon));
            HttpURLConnection connection =
                    (HttpURLConnection)url.openConnection();

            connection.addRequestProperty("x-api-key", OPEN_WEATHER_MAP_API);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(1024);
            String tmp;
            while((tmp=reader.readLine())!=null)
                json.append(tmp).append("\n");
            reader.close();

            JSONObject data = new JSONObject(json.toString());

            if(data.getInt("cod") != 200){
                return null;
            }

            return data;
        }catch(Exception e){
            return null;
        }
    }
}

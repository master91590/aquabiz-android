package com.tateh.aquabiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

public class setting_connection extends AsyncTask<String, Integer, String> {
    @SuppressLint("StaticFieldLeak")
    private final Context context;
    private String ano="", ano2="";

    setting_connection(Context ctxt){ this.context = ctxt; }

    @Override
    protected String doInBackground(String... params) {
        String task = params[0];
        switch (task) {
            case "Species": {
                String wala_lang = params[1];

                try {
                    URL url = new URL("http://agri-foodhub.com/aquabiz/android01-species.php");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);

                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                    String myData = URLEncoder.encode("wala_lang", "UTF-8") + "=" + URLEncoder.encode(wala_lang, "UTF-8");
                    bufferedWriter.write(myData);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder dataResponse = new StringBuilder();
                    String inputLine;
                    while ((inputLine = bufferedReader.readLine()) != null) {
                        dataResponse.append(inputLine);
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();

                    ano = "species";
                    return dataResponse.toString();

                } catch (IOException ignored) {
                }
                break;
            }
            case "Products": {
                String wala_lang = params[1];

                try {
                    URL url = new URL("http://agri-foodhub.com/aquabiz/android02-products.php");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);

                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                    String myData = URLEncoder.encode("wala_lang", "UTF-8") + "=" + URLEncoder.encode(wala_lang, "UTF-8");
                    bufferedWriter.write(myData);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder dataResponse = new StringBuilder();
                    String inputLine;
                    while ((inputLine = bufferedReader.readLine()) != null) {
                        dataResponse.append(inputLine);
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();

                    ano = "products";
                    return dataResponse.toString();

                } catch (IOException ignored) {
                }
                break;
            }
            case "TSR": {
                String blog_wala = params[1];
                String blog_ano = params[2];

                try {
                    URL url = new URL("http://agri-foodhub.com/aquabiz/android03-tsr-information.php");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);

                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                    String myData = URLEncoder.encode("blog_wala", "UTF-8") + "=" + URLEncoder.encode(blog_wala, "UTF-8") + "&"
                            + URLEncoder.encode("blog_ano", "UTF-8") + "=" + URLEncoder.encode(blog_ano, "UTF-8");
                    bufferedWriter.write(myData);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder dataResponse = new StringBuilder();
                    String inputLine;
                    while ((inputLine = bufferedReader.readLine()) != null) {
                        dataResponse.append(inputLine);
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();

                    ano = "tsr";
                    return dataResponse.toString();

                } catch (IOException ignored) {
                }
                break;
            }
            case "LOCATION": {
                String blog_wala = params[1];

                try {
                    URL url = new URL("http://agri-foodhub.com/aquabiz/android03-tsr-information.php");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);

                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                    String myData = URLEncoder.encode("blog_wala", "UTF-8") + "=" + URLEncoder.encode(blog_wala, "UTF-8");
                    bufferedWriter.write(myData);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder dataResponse = new StringBuilder();
                    String inputLine;
                    while ((inputLine = bufferedReader.readLine()) != null) {
                        dataResponse.append(inputLine);
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();

                    ano="location";
                    return dataResponse.toString();

                } catch (IOException ignored) {
                }
                break;
            }
            case "Branch": {
                String blog_wala = params[1];

                try {
                    URL url = new URL("http://agri-foodhub.com/aquabiz/android04-branch-locator.php");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);

                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                    String myData = URLEncoder.encode("blog_wala", "UTF-8") + "=" + URLEncoder.encode(blog_wala, "UTF-8");
                    bufferedWriter.write(myData);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder dataResponse = new StringBuilder();
                    String inputLine;
                    while ((inputLine = bufferedReader.readLine()) != null) {
                        dataResponse.append(inputLine);
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();

                    ano="branch";
                    return dataResponse.toString();

                } catch (IOException ignored) {
                }
                break;
            }
            case "WeatherTideDam": {
                String blog_wala = params[1];

                try {
                    URL url = new URL("http://agri-foodhub.com/aquabiz/android05-weather-tide-dam.php");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);

                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                    String myData = URLEncoder.encode("blog_wala", "UTF-8") + "=" + URLEncoder.encode(blog_wala, "UTF-8");
                    bufferedWriter.write(myData);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder dataResponse = new StringBuilder();
                    String inputLine;
                    while ((inputLine = bufferedReader.readLine()) != null) {
                        dataResponse.append(inputLine);
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();

                    ano="weathertidedam";
                    return dataResponse.toString();

                } catch (IOException ignored) {
                }
                break;
            }
            case "PriceWatch": {
                String blog_wala = params[1];

                try {
                    URL url = new URL("http://agri-foodhub.com/aquabiz/android06-price-watch.php");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);

                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                    String myData = URLEncoder.encode("blog_wala", "UTF-8") + "=" + URLEncoder.encode(blog_wala, "UTF-8") + "&"
                            + URLEncoder.encode("id_number", "UTF-8") + "=" + URLEncoder.encode(setting_global.getpreferences(context, R.string.sfc08_price_watch_last_id), "UTF-8");
                    bufferedWriter.write(myData);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder dataResponse = new StringBuilder();
                    String inputLine;
                    while ((inputLine = bufferedReader.readLine()) != null) {
                        dataResponse.append(inputLine);
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();

                    ano="pricewatch";
                    return dataResponse.toString();

                } catch (IOException ignored) {
                }
                break;
            }
            case "AquaRX": {
                String wala_lang = params[1];

                try {
                    URL url = new URL("http://agri-foodhub.com/aquabiz/android07-aqua-rx.php");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);

                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                    String myData = URLEncoder.encode("wala_lang", "UTF-8") + "=" + URLEncoder.encode(wala_lang, "UTF-8");
                    bufferedWriter.write(myData);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder dataResponse = new StringBuilder();
                    String inputLine;
                    while ((inputLine = bufferedReader.readLine()) != null) {
                        dataResponse.append(inputLine);
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();

                    ano = "aquarx";
                    return dataResponse.toString();

                } catch (IOException ignored) {
                }
                break;
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(final String anong_response) {
        switch (ano) {
            case "species": {
                setting_global.editpreferences(context, R.string.sfc03_species01, anong_response);
                setting_global.dismissAlertDialog();
                setting_global.gointent(context, sfc03Species01.class);
                break;
            }
            case "products": {
                StringTokenizer tokens = new StringTokenizer(anong_response, "♦");
                setting_global.editpreferences(context, R.string.sfc04_products01, tokens.nextToken());
                setting_global.editpreferences(context, R.string.sfc04_products02, tokens.nextToken());
                setting_global.dismissAlertDialog();
                String saan = setting_global.getpreferences(context, R.string.sfc03_saan);
                if(saan.equals("home")) setting_global.gointent(context, sfc04Products01.class);
                else if(saan.equals("branch")) {
                    sfc05Locator02.products = anong_response;
                    sfc05Locator02.anongProdukto(context);
                }
                else setting_global.gointent(context, sfc04Products02.class);
                break;
            }
            case "tsr":{
                setting_global.dismissAlertDialog();
                sfc04Products04.numero = anong_response;
                sfc04Products04.LLcalltext.setVisibility(View.VISIBLE);
                break;
            }
            case "location":{
                setting_global.dismissAlertDialog();
                setting_global.editpreferences(context, R.string.sfc04_products04_province, anong_response);
                setting_global.gointent(context, sfc04Products04.class);
                break;
            }
            case "branch": {
                setting_global.editpreferences(context, R.string.sfc05_branch, anong_response);
                setting_global.dismissAlertDialog();
                setting_global.gointent(context, sfc05Locator01.class);
                break;
            }
            case "weathertidedam": {
                setting_global.editpreferences(context, R.string.sfc07_weather_tide_dam, anong_response);
                setting_global.dismissAlertDialog();
                setting_global.gointent(context, sfc07WeatherTideDam.class);
                break;
            }
            case "pricewatch": {
                StringTokenizer tokens = new StringTokenizer(anong_response, "♦");
                setting_global.editpreferences(context, R.string.sfc08_price_watch, tokens.nextToken());
                setting_global.editpreferences(context, R.string.sfc08_price_watch_last_id, tokens.nextToken());
                setting_global.dismissAlertDialog();
                setting_global.gointent(context, sfc08PriceWatch.class);
                break;
            }
            case "aquarx": {
                StringTokenizer tokens = new StringTokenizer(anong_response, "♦");
                setting_global.editpreferences(context, R.string.sfc09_aquarx01, tokens.nextToken());
                setting_global.editpreferences(context, R.string.sfc09_aquarx02, tokens.nextToken());
                setting_global.dismissAlertDialog();
                setting_global.gointent(context, sfc09AquaRX01.class);
                break;
            }
        }
    }
}

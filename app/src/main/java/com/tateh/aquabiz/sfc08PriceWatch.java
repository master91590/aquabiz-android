package com.tateh.aquabiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;

public class sfc08PriceWatch extends AppCompatActivity {
    String valid, pw, lastValue="wala";
    Spinner spnMonth, spnDay, spnYear;
    int bilang = 0, una = 0;
    ArrayList<sfc03Species01_Item> view_adapters = new ArrayList<>();
    TextView txtPriceValidity;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sfc08_price_watch);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Price Watch");

        txtPriceValidity = findViewById(R.id.txtPriceValidity);
        listView = findViewById(R.id.listView);
        spnMonth = findViewById(R.id.spnMonth);
        spnDay = findViewById(R.id.spnDay);
        spnYear = findViewById(R.id.spnYear);

        pw = setting_global.getpreferences(this, R.string.sfc08_price_watch);
        final ArrayList<String> listValidity = new ArrayList<>();
        final ArrayList<String> listYear = new ArrayList<>();
        final ArrayList<String> listMonth = new ArrayList<>();
        final ArrayList<String> listDay = new ArrayList<>();
        StringTokenizer tokenslist = new StringTokenizer(pw, "♠");

        while (tokenslist.hasMoreTokens()) {
            String ano = tokenslist.nextToken().trim();
            StringTokenizer tokensValidity = new StringTokenizer(ano, "♥");
            String validity = tokensValidity.nextToken();
            if(!listValidity.contains(validity)) listValidity.add(validity);
        }

        while (bilang < listValidity.size()) {
            String ano = listValidity.get(bilang);
            if(!listYear.contains(ano.substring(0,4))) listYear.add(ano.substring(0,4));
            bilang++;
        }

        ArrayAdapter<String> adapterYear = new ArrayAdapter<>(sfc08PriceWatch.this, R.layout.support_simple_spinner_dropdown_item, listYear);
        adapterYear.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spnYear.setAdapter(adapterYear);
        spnYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bilang = 0;
                listMonth.clear();
                while (bilang < listValidity.size()) {
                    String ano = listValidity.get(bilang);
                    if(!listMonth.contains(convertMonth(ano.substring(5,7))) && ano.substring(0,4).equals(spnYear.getSelectedItem().toString())) listMonth.add(convertMonth(ano.substring(5,7)));
                    bilang++;
                }
                ArrayAdapter<String> adapterMonth = new ArrayAdapter<>(sfc08PriceWatch.this, R.layout.support_simple_spinner_dropdown_item, listMonth);
                adapterMonth.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spnMonth.setAdapter(adapterMonth);
                spnMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        bilang = 0;
                        listDay.clear();
                        while (bilang < listValidity.size()) {
                            String ano = listValidity.get(bilang);
                            if(ano.substring(0,4).equals(spnYear.getSelectedItem().toString()) && convertMonth(ano.substring(5,7)).equals(spnMonth.getSelectedItem().toString())) listDay.add(ano.substring(8));
                            bilang++;
                        }

                        ArrayAdapter<String> adapterDay = new ArrayAdapter<>(sfc08PriceWatch.this, R.layout.support_simple_spinner_dropdown_item, listDay);
                        adapterDay.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        spnDay.setAdapter(adapterDay);
                        spnDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                valid = spnYear.getSelectedItem().toString() + "-" + balikMonth(spnMonth.getSelectedItem().toString()) + "-" + spnDay.getSelectedItem().toString();
                                if(una == 0){
                                    loadPriceWatch();
                                    una = 1;
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    boolean nahanap = false;
    @SuppressLint("SetTextI18n")
    private void loadPriceWatch() {
        nahanap = false;
        StringTokenizer stringTokenizerValidity = new StringTokenizer(valid, "-");
        String taon = stringTokenizerValidity.nextToken().trim();
        String buwan = stringTokenizerValidity.nextToken().trim();
        String araw = stringTokenizerValidity.nextToken().trim();
        txtPriceValidity.setText("As of "+ convertMonth(buwan) + " " + araw + ", " + taon);
        StringTokenizer tokenslist = new StringTokenizer(pw, "♠");
        while (tokenslist.hasMoreTokens()) {
            String kabuuan = tokenslist.nextToken().trim();
            StringTokenizer tokensHiwalay = new StringTokenizer(kabuuan, "♥");
            String anongValidity = tokensHiwalay.nextToken();
            if (valid.equals(anongValidity)){
                nahanap = true;
                StringTokenizer tokensAno = new StringTokenizer(tokensHiwalay.nextToken(), "♣");
                int ilan = tokensAno.countTokens();
                view_adapters.clear();
                for (int i = 0; i<ilan; i++){
                    sfc03Species01_Item adapter = new sfc03Species01_Item(tokensAno.nextToken());
                    view_adapters.add(adapter);
                }
            }
            if(!valid.equals(anongValidity) && nahanap){
                break;
            }
        }
        sfc08PriceWatch_Adapter lAdapter = new sfc08PriceWatch_Adapter(this, R.layout.activity_sfc08_price_watch_adapter, view_adapters);
        listView.setAdapter(lAdapter);

    }

    public static String convertMonth(String buwan){
        String ano = null;
        switch (buwan) {
            case "01":
                ano = "January";
                break;
            case "02":
                ano = "February";
                break;
            case "03":
                ano = "March";
                break;
            case "04":
                ano = "April";
                break;
            case "05":
                ano = "May";
                break;
            case "06":
                ano = "June";
                break;
            case "07":
                ano = "July";
                break;
            case "08":
                ano = "August";
                break;
            case "09":
                ano = "September";
                break;
            case "10":
                ano = "October";
                break;
            case "11":
                ano = "November";
                break;
            case "12":
                ano = "December";
                break;
        }

        return ano;
    }

    public static String balikMonth(String buwan){
        String ano = null;
        switch (buwan) {
            case "January":
                ano = "01";
                break;
            case "February":
                ano = "02";
                break;
            case "March":
                ano = "03";
                break;
            case "April":
                ano = "04";
                break;
            case "May":
                ano = "05";
                break;
            case "June":
                ano = "06";
                break;
            case "July":
                ano = "07";
                break;
            case "August":
                ano = "08";
                break;
            case "September":
                ano = "09";
                break;
            case "October":
                ano = "10";
                break;
            case "November":
                ano = "11";
                break;
            case "December":
                ano = "12";
                break;
        }

        return ano;
    }
    public void btn_view_price(View view) {
        if(lastValue.equals(valid)) setting_global.display("Same field.","Please select other date.", this);
        else{
            lastValue = valid;
            loadPriceWatch();
        }
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
}
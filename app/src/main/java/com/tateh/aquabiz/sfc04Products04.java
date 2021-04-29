package com.tateh.aquabiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Objects;

public class sfc04Products04 extends AppCompatActivity {
    static String numero;
    @SuppressLint("StaticFieldLeak")
    static LinearLayout LLcalltext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sfc04_products04);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Tateh Inquiry");

        Spinner spnLocation = findViewById(R.id.spnLocation);
        String ihihiwalay = "Select province|"+setting_global.getpreferences(this, R.string.sfc04_products04_province);
        String[] arrayLoc = ihihiwalay.split("\\|");
        LLcalltext = findViewById(R.id.LLcalltext);
        ArrayAdapter<String> spinnerLocationAdapter = new ArrayAdapter<String>(
                this, R.layout.support_simple_spinner_dropdown_item, arrayLoc) {
            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerLocationAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spnLocation.setAdapter(spinnerLocationAdapter);
        spnLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0) {
                    if(setting_global.isNetworkAvailable(sfc04Products04.this)){
                        setting_global.ShowProgressDialog(sfc04Products04.this);
                        setting_connection BackgroundTask = new setting_connection(sfc04Products04.this);
                        BackgroundTask.execute("TSR", "aquabiz", parent.getItemAtPosition(position).toString());
                    }else setting_global.noInternetDisplay(sfc04Products04.this);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void btn_call(View view) {
        Intent i = new Intent(Intent.ACTION_DIAL);
        String p = "tel:" + numero;
        i.setData(Uri.parse(p));
        startActivity(i);
    }

    public void btn_text(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("smsto:" + numero));
        i.putExtra("sms_body","AquaBiz inquiry\n\nMessage:\n");
        startActivity(i);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        setting_global.gointent(this,sfc04Products03.class);
    }

    public void btn_home(View view) {
        setting_global.gointent(this,sfc02Home.class);
    }

    public void btn_notification(View view) {
    }

    public void btn_announcement(View view) {
    }

    public void btn_setting(View view) {
    }
}
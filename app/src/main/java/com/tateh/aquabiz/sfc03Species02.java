package com.tateh.aquabiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Objects;
import java.util.StringTokenizer;

public class sfc03Species02 extends AppCompatActivity {
    String species, produkto, paano, gastos, faq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sfc03_species02);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String kabuuan = setting_global.getpreferences(this, R.string.sfc03_species02);
        StringTokenizer tokens = new StringTokenizer(kabuuan, "♣");
        species = tokens.nextToken();
        String english_name = tokens.nextToken();
        String local_name = tokens.nextToken();
        String scientific_name = tokens.nextToken();
        String description = tokens.nextToken();
        tokens.nextToken();
        String img = tokens.nextToken();
        paano = tokens.nextToken();
        gastos = tokens.nextToken();
        faq = tokens.nextToken();
        produkto = tokens.nextToken();
        setting_global.editpreferences(this, R.string.sfc03_howtogrow, paano);
        setting_global.editpreferences(this, R.string.sfc03_costandreturn, gastos);
        setting_global.editpreferences(this, R.string.sfc03_faq, faq);
        setting_global.editpreferences(this, R.string.sfc03_products, produkto);
        setTitle(species + " Description");
        setting_global.editpreferences(this, R.string.sfc03_species_name, species);

        ImageView btnHow = findViewById(R.id.btn_species);
        ImageView imgSpecies = findViewById(R.id.imgSpecies);
        TextView txtDescription1 = findViewById(R.id.txtDescription1);
        TextView txtDescription2 = findViewById(R.id.txtDescription2);
        TextView txtDescription3 = findViewById(R.id.txtDescription3);
        TextView txtDescription4 = findViewById(R.id.txtDescription4);

        if(species.equals("Crab")) btnHow.setBackgroundResource(R.drawable.sfc03_species02_01howtogrow02);
        else if(species.equals("Vannamei")) btnHow.setBackgroundResource(R.drawable.sfc03_species02_01howtogrow03);

        txtDescription1.setText(english_name.replace("|", "\n"));
        txtDescription2.setText(local_name);
        txtDescription3.setText(scientific_name.replace("|", "\n"));
        txtDescription4.setText(description);
        imgSpecies.setImageBitmap(setting_global.StringToBitMap(img));

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public void onBackPressed() {
        setting_global.gointent(this,sfc03Species01.class);
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

    public void btn_howtogrow(View view) {
        if(paano.equals("wala")) setting_global.displayNoDataAvailable(this);
        else {
            setting_global.editpreferences(this, R.string.sfc03_saan, "species");
            setting_global.gointent(this, sfc03Species03.class);
        }
    }

    public void btn_costreturn(View view) {
        if(gastos.equals("wala")) setting_global.displayNoDataAvailable(this);
        else {
            setting_global.editpreferences(this, R.string.sfc03_saan, "species");
            setting_global.gointent(this, sfc03Species04.class);
        }
    }

    public void btn_products(View view) {
        if(produkto.equals("wala")) setting_global.displayNoDataAvailable(this);
        else {
            setting_global.editpreferences(this, R.string.sfc03_saan, "species");
            setting_global.editpreferences(this, R.string.sfc04_products02_selected, species + "♣" + produkto);
            if(setting_global.getpreferences(this, R.string.sfc04_products01).equals("0")){
                if(setting_global.isNetworkAvailable(this)){
                    setting_global.ShowProgressDialog(this);
                    setting_connection productsBackgroundTask = new setting_connection(this);
                    productsBackgroundTask.execute("Products", "aquabiz");
                }else setting_global.noInternetDisplay(this);
            }else setting_global.gointent(this,sfc04Products02.class);
        }
    }

    public void btn_faq(View view) {
        if(gastos.equals("faq")) setting_global.displayNoDataAvailable(this);
        else {
            setting_global.gointent(this, sfc03Species05.class);
        }
    }
}
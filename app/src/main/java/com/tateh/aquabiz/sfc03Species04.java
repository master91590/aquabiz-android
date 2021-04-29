package com.tateh.aquabiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;

public class sfc03Species04 extends AppCompatActivity {
    Button btn_pond, btn_cage;
    String title_pond, title_cage1, title_cage2, pond, cage1, cage2, species;
    LinearLayout llCage;
    int bilang;
    ListView listView;
    ArrayList<sfc03Species01_Item> view_adapters = new ArrayList<>();
    TextView txtTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sfc03_species04);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Cost & Return");

        btn_pond = findViewById(R.id.btn_pond);
        btn_cage = findViewById(R.id.btn_cage);
        listView = findViewById(R.id.listview);
        txtTitle = findViewById(R.id.txtTitle);

        btn_cage.setEnabled(true);
        btn_pond.setEnabled(false);
        btn_pond.setBackgroundResource(R.drawable.sfc03_species03_pond);
        btn_cage.setBackgroundResource(R.drawable.sfc03_species03_cage_inactive);

        species = setting_global.getpreferences(this, R.string.sfc03_species_name);

        String kabuuan = setting_global.getpreferences(this, R.string.sfc03_costandreturn);

        StringTokenizer tokens = new StringTokenizer(kabuuan, "♥");
        bilang = tokens.countTokens();
        StringTokenizer tokens2 = new StringTokenizer(tokens.nextToken(), "$");
        title_pond = tokens2.nextToken();
        pond = tokens2.nextToken();
        txtTitle.setText(title_pond);
        loadData(pond);
        if(bilang == 2){
            StringTokenizer tokens3 = new StringTokenizer(tokens.nextToken(), "$");
            title_cage1 = tokens3.nextToken();
            cage1 = tokens3.nextToken();
        }
        if(bilang == 3){
            StringTokenizer tokens4 = new StringTokenizer(tokens.nextToken(), "$");
            title_cage2 = tokens4.nextToken();
            cage2 = tokens4.nextToken();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public void onBackPressed() {
        String saan = setting_global.getpreferences(this, R.string.sfc03_saan);
        if(saan.equals("faq")) setting_global.gointent(this, sfc03Species05.class);
        else setting_global.gointent(this, sfc03Species02.class);
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

    private void loadData(String ano) {
        StringTokenizer tokens = new StringTokenizer(ano, "^");
        int ilan = tokens.countTokens();
        view_adapters.clear();
        for (int i = 0; i<ilan; i++){
            sfc03Species01_Item adapter = new sfc03Species01_Item(tokens.nextToken());
            view_adapters.add(adapter);
        }
        sfc03Species04_Adapter lAdapter = new sfc03Species04_Adapter(this, R.layout.activity_sfc03_species04_adapter, view_adapters);
        listView.setAdapter(lAdapter);
    }

    public void btn_pond(View view) {
        if(bilang == 1) btn_pond.setEnabled(false);
        else{
            txtTitle.setText(title_pond);
            btn_cage.setEnabled(true);
            btn_pond.setEnabled(false);
            btn_pond.setBackgroundResource(R.drawable.sfc03_species03_pond);
            btn_cage.setBackgroundResource(R.drawable.sfc03_species03_cage_inactive);
            loadData(pond);
        }
    }

    public void btn_cage(View view) {
        if(bilang == 1) setting_global.displayNoDataAvailable(this);
        else{
            txtTitle.setText(title_cage1);
            btn_cage.setEnabled(false);
            btn_pond.setEnabled(true);
            btn_pond.setBackgroundResource(R.drawable.sfc03_species03_pond_inactive);
            btn_cage.setBackgroundResource(R.drawable.sfc03_species03_cage);
            loadData(cage1);
            if(bilang == 3) llCage.setVisibility(View.VISIBLE);
        }
    }

    public void btn_nodataavailable(View view) {
        setting_global.displayNoDataAvailable(this);
    }

    public void btn_SHALLOW_WATER(View view) {
        enabledisable(1);
        loadData(cage1);
    }

    public void btn_deep_water(View view) {
        enabledisable(2);
        loadData(cage2);
    }

    private void enabledisable(int i) {
        AppCompatButton btn_part1 = findViewById(R.id.btn_SHALLOW_WATER);
        AppCompatButton btn_part2 = findViewById(R.id.btn_deep_water);
        if(i==1){
            btn_part1.setBackgroundColor(ContextCompat.getColor(this, R.color.green_200));
            btn_part2.setBackgroundColor(ContextCompat.getColor(this, R.color.green_500));
            btn_part1.setEnabled(false);
            btn_part2.setEnabled(true);
        }else{
            btn_part2.setBackgroundColor(ContextCompat.getColor(this, R.color.green_200));
            btn_part1.setBackgroundColor(ContextCompat.getColor(this, R.color.green_500));
            btn_part2.setEnabled(false);
            btn_part1.setEnabled(true);
        }
    }
}